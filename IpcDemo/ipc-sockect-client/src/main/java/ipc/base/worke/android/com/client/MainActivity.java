package ipc.base.worke.android.com.client;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;

import ipc.base.worke.android.com.ipc_sockect_client.R;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName() + "客户端--->";
    private TextView tvShowContent;
    private EditText inputContent;
    private PrintWriter mPrintWrite;
    private ContentHandle handler;

    private Socket socket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler = new ContentHandle(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                conectSocketServer();
            }
        }).start();
    }


    private void initView() {
        tvShowContent = findViewById(R.id.tv_show);
        inputContent = findViewById(R.id.et_input);
        findViewById(R.id.bt_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputContent.getText().toString();
                mPrintWrite.println(content);
                mPrintWrite.flush();
                inputContent.setText("");
            }
        });
    }

    public void setShowContent(String msg) {
        String s = tvShowContent.getText().toString();
        String stringBuilder = s+"\n"+msg;
        tvShowContent.setText(stringBuilder);
    }

    /**
     * 连接服务端
     */

    private void conectSocketServer() {
        try {
            socket = new Socket("localhost", 8688);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Log.i(TAG, "客户端连接成功,准备读取服务端信息");

            mPrintWrite = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            mPrintWrite.println("服务你好!!!");
            mPrintWrite.flush();
            String msg;
            while (!MainActivity.this.isFinishing() && (msg = reader.readLine()) != null) {
                Log.i(TAG, "收到服务端消息:" + msg);
                handler.sendMessage(handler.obtainMessage(ContentHandle.MSG, msg));
            }
            Log.i(TAG, "没有消息了,将要退出");
            socket.close();
            mPrintWrite.close();
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (IOException e) {
                    Log.i(TAG, e.getMessage());
                }
            }
        }
    }

    private static class ContentHandle extends Handler {
        private static final int MSG = 0x0001;
        private WeakReference<MainActivity> weakReference;

        private ContentHandle(MainActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = weakReference.get();
            if (activity != null && msg.what == MSG) {
                String content = (String) msg.obj;
                activity.setShowContent(content);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "将要关闭流");
        if (socket != null) {
            try {
                socket.close();
                Log.i(TAG, "流关闭");
            } catch (IOException e) {
                Log.i(TAG, e.getMessage());
            }
        }
    }
}
