// IBookManager.aidl
package me.apqx.demo.ipc.aidl;
import me.apqx.demo.ipc.aidl.bean.Book;
import me.apqx.demo.ipc.aidl.ICallback;

// Declare any non-default types here with import statements

interface IBookManager {

    /**
    * 获取远程书库中所有书本列表
    */
    List<Book> getBooks();

    /**
    * 向远程书库中添加书本
    */
    void addBook(in List<Book> books);

    /**
    * 清除远程书库的所有书本
    */
    void clearBooks();

    /**
    * 传入Callback接口，用于远程回调
    */
    void registerCallBack(in ICallback iCallback);
}
