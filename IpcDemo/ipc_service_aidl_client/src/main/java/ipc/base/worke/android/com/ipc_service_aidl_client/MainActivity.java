package ipc.base.worke.android.com.ipc_service_aidl_client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.work.ipc.aidl.Book;
import com.android.work.ipc.aidl.IBookeManager;
import com.android.work.ipc.aidl.IServiceCallBack;

/**
 * AIDL通信客户端
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName()+"客户端--->";
    private IBookeManager bookManager ;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = IBookeManager.Stub.asInterface(service);
            Log.i(TAG,"绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"断开连接");
        }
    };

    //此处要注意实例是.stub()而还是直接 new IServiceCallBack.
    private IServiceCallBack callBack = new IServiceCallBack.Stub() {
        @Override
        public void addNewBook(Book newBook) throws RemoteException {
            Log.i(TAG,"收到服务端增加图书通知book:"+newBook);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("android.base.api.aidl");
        bindService(intent,connection, Context.BIND_AUTO_CREATE);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_addbook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("book");
                book.setId(001);
                try {
                    bookManager.addBook(book);
                } catch (RemoteException e) {
                    Log.i(TAG,"加入图书失败");
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.bt_region).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bookManager.regionListion(callBack);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.bt_unregion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bookManager.unRegionListion(callBack);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
