package ipc.base.worke.android.com.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ipc.base.worke.android.com.ipc_sockect_service.R;

public class MainActivity extends Activity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,SocketService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null){
            stopService(intent);
        }
    }
}
