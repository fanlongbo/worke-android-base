package ipc.base.worke.android.com.ipc_server;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by fanlongbo on 2018/1/31.
 * 通过Messenger实现进程间通信服务端.
 */

public class MessengerServiceDemo extends Service {
    private static final String TAG = MessengerServiceDemo.class.getSimpleName();
    private static final int MES_RESULT = 0x0001;

    private static class MessengerHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MES_RESULT:
                    Log.i(TAG,"接受到消息 data:"+msg.getData());
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    private static final Messenger messenger = new Messenger(new MessengerHandle());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
