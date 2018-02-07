package ipc.base.worke.android.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fanlongbo on 2018/2/6.
 * 通过Socket进行进程间通信 服务端
 */

public class SocketService extends Service {
    private static final String TAG = SocketService.class.getSimpleName() + "socket服务端--->";
    private boolean isServiceDestroyed;
    private ServerSocket serverSocket;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(socketRunnable).start();
    }

    private Runnable socketRunnable = new Runnable() {

        private void responseClient(Socket socket) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            Log.i(TAG, "向客户发送欢迎消息");
            writer.println("欢迎来到聊天室");
            writer.flush();
            String msg;
            while (!isServiceDestroyed && (msg = in.readLine()) != null) {
                Log.i(TAG, "接收到客户端消息:" + msg);
                writer.println("已经接收到你发来的信息:" + msg);
                writer.flush();
            }
            Log.i(TAG, "流中没有消息认为客户退出");
            socket.shutdownInput();
            in.close();
            writer.close();
            socket.close();
        }

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(8688);
                while (!isServiceDestroyed) {
                    Log.i(TAG, "等待客户端连接");
                    final Socket socket = serverSocket.accept();
                    Log.i(TAG, "客户已成功连接");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.i(TAG,"操作流");
                                responseClient(socket);
                            } catch (IOException e) {
                                Log.i(TAG, e.getMessage());
                            }finally {
                                    Log.i(TAG,"关闭流");
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    Log.i(TAG,e.getLocalizedMessage());
                                }
                            }
                        }
                    }).start();
                }
            } catch (Exception e) {
                Log.i(TAG, e.getLocalizedMessage());
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceDestroyed = true;
        if(serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                Log.i(TAG,e.getMessage());
            }finally {
                serverSocket = null;
            }
        }
    }
}
