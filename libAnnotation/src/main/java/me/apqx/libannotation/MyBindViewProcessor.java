package me.apqx.libannotation;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import me.apqx.libannotation.model.BindingSet;
import me.apqx.libannotation.model.ClassBinding;
import me.apqx.libannotation.model.PackageBinding;
import me.apqx.libannotation.model.ViewBinding;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * 创建注解处理器，因为javax.annotation没有包含在Android里，所以需要新建这个JavaLibrary
 */
public class MyBindViewProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 指定这个注解处理器能处理什么类型的注解
        Set<String> set = new HashSet<>();
        set.add(MyBindView.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        BindingSet bindingSet = new BindingSet();
        // 获取所有被MyBindView注解的元素，Element表示一种元素，可以是类，方法，成员
        // 这里获得的所有元素，一定是被注解的方法
        Set<? extends Element> elementsToBind = roundEnvironment.getElementsAnnotatedWith(MyBindView.class);
        for (Element element : elementsToBind) {
            // 获取这个元素使用的MyBindView注解
            MyBindView annotation = element.getAnnotation(MyBindView.class);
            // 注解时传入的resId
            int viewId = annotation.resId();
            // 使用注解的viewType
            TypeMirror viewType = element.asType();
            // 定义的viewName
            String viewName = element.getSimpleName().toString();
            ViewBinding viewBinding = new ViewBinding(viewType, viewName, viewId);

            // 上一级封闭元素，这里是定义这个成员的类元素
            TypeElement classElement = (TypeElement) element.getEnclosingElement();
            // 获取所在的包元素
            PackageElement packageElement = getPackage(classElement);

            bindingSet.addBinding(packageElement, classElement, viewBinding);
        }
        // 至此，使用自定义注解的成员、类、包信息都已经明确
        // 下面使用JavaPoet来在编译时生成Java类文件


        // 返回true表示注解已经被这里处理了
        return true;
    }

    /**
     * 获取指定元素最外层的包元素
     */
    private PackageElement getPackage(Element element) {
        while (element.getKind() != ElementKind.PACKAGE) {
            element = element.getEnclosingElement();
        }
        return (PackageElement) element;
    }

    /**
     * 在编译时动态生成绑定的类文件
     * @param bindingSet 使用注解的包、类、成员信息，根据这些信息来生成类
     */
    private Set<JavaFile> generateBinderClasses(BindingSet bindingSet) {
        Set<JavaFile> files = new HashSet<>();

        Collection<PackageBinding> packageBindings =
                bindingSet.getPackageBindings();
        // 遍历包
        for (PackageBinding packageBinding : packageBindings) {
            String packageName = packageBinding.getPackageName();
            // 生成类信息
            TypeSpec binderClass = generateBinderClass(packageBinding);

            // 根据包和类信息生成类文件
            JavaFile javaFile =
                    JavaFile.builder(packageName, binderClass).build();
            files.add(javaFile);
        }

        return files;
    }

    private TypeSpec generateBinderClass(PackageBinding packageBinding){
        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(PRIVATE)
                .build();

        // 生成一个名为ViewBinder的类信息
        TypeSpec.Builder classBuilder =
                TypeSpec.classBuilder("ViewBinder")
                        .addModifiers(FINAL)
                        .addMethod(constructor);

        packageBinding.getClassBindings()
                // java8的流式接口
                .stream()
                .map(this::generateBindMethod)
                .forEach(classBuilder::addMethod);
        return classBuilder.build();
    }

    private MethodSpec generateBindMethod(ClassBinding classBinding) {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
                .addModifiers(PUBLIC, STATIC)
                .addParameter(ClassName.get(classBinding.getClassElement()), "target");

        for (ViewBinding elementBinding : classBinding.getViewBindingSet()) {
            methodBuilder.addStatement("target.$N = ($T) target.findViewById($L)",
                    elementBinding.getViewName(),
                    ClassName.get(elementBinding.getViewType()),
                    elementBinding.getViewId());
        }

        return methodBuilder.build();
    }

    private void writeFiles(Collection<JavaFile> javaFiles) {
        javaFiles.forEach(this::writeFile);
    }

    private void writeFile(JavaFile javaFile) {
        Filer filer = processingEnv.getFiler();

        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            Messager messager = processingEnv.getMessager();
            String message = String.format("Unable to write file: %s", e.getMessage());
            messager.printMessage(Diagnostic.Kind.ERROR, message);
        }
    }
}
