// IBookeManager.aidl
package com.android.work.ipc.aidl;
import com.android.work.ipc.aidl.Book;
import com.android.work.ipc.aidl.IServiceCallBack;


interface IBookeManager {
   void addBook(in Book book);
   List<Book> getBookList();
   void regionListion(IServiceCallBack callBack);
   void unRegionListion(IServiceCallBack callBack);
}
