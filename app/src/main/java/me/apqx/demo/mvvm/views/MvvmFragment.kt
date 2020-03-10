package me.apqx.demo.mvvm.views

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frag_mvvm.*
import kotlinx.android.synthetic.main.item_student.*
import kotlinx.coroutines.GlobalScope
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.mvvm.adapter.MvvmRecyclerAdapter
import me.apqx.demo.mvvm.data.AppDatabase
import me.apqx.demo.mvvm.data.Student
import me.apqx.demo.mvvm.viewmodels.DemoViewModel
import me.apqx.demo.old.tools.LogUtil
import me.apqx.demo.old.tools.ToastUtil

class MvvmFragment : BaseFragment<BasePresenter<IBaseView>>() {

    private val model: DemoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_mvvm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_mvvm_change.setOnClickListener(this)
        btn_mvvm_query.setOnClickListener(this)
        btn_mvvm_add.setOnClickListener(this)
        btn_mvvm_delete.setOnClickListener(this)

        srl_mvvm.setOnRefreshListener {
            model.loadMoreStudent()
        }
        val adapter = MvvmRecyclerAdapter()
        rv_mvvm.adapter = adapter
        rv_mvvm.layoutManager = LinearLayoutManager(requireContext())
        model.studentList.observe(viewLifecycleOwner, Observer {
            LogUtil.d("MvvmFragment notifyData studentList $it")
            adapter.submitList(it)
        })
        // 监听刷新状态
        model.loadingState.observe(viewLifecycleOwner, Observer {
            LogUtil.d("MvvmFragment notifyData loadingState $it")
            srl_mvvm.isRefreshing = it
        })
        // 监听UI要展示的错误信息
        model.errorInfo.observe(viewLifecycleOwner, Observer {
            LogUtil.d("MvvmFragment notifyData errorInfo $it")
            if (TextUtils.isEmpty(it)) return@Observer
            ToastUtil.showToast(it)
            model.errorInfo.value = ""
        })

//        adapter.submitList(generateList())

    }

    private fun generateList(): MutableList<Student>? {
        val list = ArrayList<Student>()
        for (i in 0 until 30) {
            list.add(Student(i, "Tom_$i"))
        }
        return list
    }


    override fun onClick(v: View?) {
        val dao = AppDatabase.getInstance().studentDao()
        Thread {
            when (v?.id) {
                R.id.btn_mvvm_change -> {
                    model.loadMoreStudent()
                }
                R.id.btn_mvvm_add -> {
                    val allStudent = dao.loadAllStudents()
                    LogUtil.d("loadAllStudents = $allStudent")
                    val id = (allStudent.lastOrNull()?.id?.plus(1)) ?: 0
                    dao.insert(Student(
                            id = id, name = "Tom_$id", teacherName = "Teacher_$id")
                    ))
                }
                R.id.btn_mvvm_query -> {
                    val ids = listOf<Int>(1, 2)
                    LogUtil.d("loadStudentsById = ${dao.loadStudentsByIds(ids)}")
                    LogUtil.d("loadSingle = ${dao.loadSingle()}")
                }
                R.id.btn_mvvm_delete -> {
                    val allStudent = dao.loadAllStudents()
                    dao.deleteAllStudents()
                    LogUtil.d("loadAllStudents = $allStudent")
                    if (allStudent.isEmpty()) {
                        return@Thread
                    }
                    val delId = allStudent[allStudent.size - 1].id
                    LogUtil.d("delete id = $delId")
                    dao.delete(Student(delId))
                }
            }

        }.start()
    }


}