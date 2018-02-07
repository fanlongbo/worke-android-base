package com.android.work.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fanlongbo on 2018/2/1.
 * 进程通信通过 服务端,注意需要处理同步问题
 */

public class BookService extends Service {

    private static final String TAG = BookService.class.getSimpleName() + "服务端--->";

    //因为要考虑同步的问题所以选用此集合,扶持并发读写.
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    //所有注册监听的集合,因为每个监听都是通过反序列化而来,如果用List将不能解注成功,所以用RemoteallbackList.
    private RemoteCallbackList<IServiceCallBack> callBackListen = new RemoteCallbackList<>();

    //返回代表服务的Binder 注意不要忽略Stub
    IBookeManager.Stub bookManager = new IBookeManager.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            Log.i(TAG, "服务端加入图书:" + book.toString());
            mBookList.add(book);

            //每增加一本书通过回调给客户成功了
            int number = callBackListen.beginBroadcast();
            for (int i = 0;i< number;i++) {
                IServiceCallBack callBack = callBackListen.getBroadcastItem(i);
                if(callBack != null){
                    callBack.addNewBook(book);
                }
            }
            callBackListen.finishBroadcast();
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.i(TAG, "返回全部图书列表:list:" + mBookList);
            return mBookList;
        }

        @Override
        public void regionListion(IServiceCallBack callBack) throws RemoteException {
            if (callBackListen.register(callBack)) {
                Log.i(TAG, "注册方法执行");
            }
        }

        @Override
        public void unRegionListion(IServiceCallBack callBack) throws RemoteException {
                if(callBackListen.unregister(callBack)){
                    Log.i(TAG, "销毁注册方法执行");
                }

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "绑定成功");
        return bookManager;
    }
}
