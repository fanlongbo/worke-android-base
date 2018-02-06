package ipc.base.worke.android.com.ipc_server;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 通过Messenger进行进程间通信的客户端
 */

public class MessengerClientDemo extends AppCompatActivity {
    private static final String TAG = MessengerClientDemo.class.getSimpleName()+"客户端--->";
    private Messenger messenger;
    //提供给服务端的Messenger 用于接收服务端发来的信息
    private Messenger clientMessenger = new Messenger(new ClientMessengerHandler());

    private class ClientMessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x0002:
                    String m = msg.getData().getString("msg");
                    Log.i(TAG,"接收到来自服务端消息:"+m);
                    break;
                default:

                    super.handleMessage(msg);
                    break;
            }
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message msg = Message.obtain(null, 0x0001);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hellow ");
            msg.setData(bundle);
            msg.replyTo = clientMessenger;
            try {
                messenger.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i(TAG,"链接成功 向服务端发送消息 hellow");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MessengerServiceDemo.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
