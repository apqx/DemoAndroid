// IBookManager.aidl
package me.apqx.demo.ipc.aidl;
import me.apqx.demo.ipc.aidl.bean.Book;
import me.apqx.demo.ipc.aidl.ICallback;

// Declare any non-default types here with import statements

interface IBookManager {

    /**
    * get all books from server
    */
    List<Book> getBooks();

    /**
    * add books to server
    */
    void addBook(in List<Book> books);

    /**
    * clear server books
    */
    void clearBooks();

    /**
    * transport callback for remote invoke
    */
    void registerCallBack(in ICallback iCallback);
}
