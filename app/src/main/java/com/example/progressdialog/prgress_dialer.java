package com.example.progressdialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class prgress_dialer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prgress_dialer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void openProgressDialer(View view){
        ProgressDialog progressDialog=new ProgressDialog(prgress_dialer.this);
        progressDialog.setTitle("New Titile");
        progressDialog.setMessage("Progress...");
        progressDialog.setIcon(R.drawable.warning);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setMax(100);

        Handler handle=new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                progressDialog.incrementProgressBy(1);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog.getMax() )
                    {
                        Thread.sleep(100);
                        handle.sendMessage(handle.obtainMessage());
                        if(progressDialog.getProgress() == progressDialog.getMax())
                        {
                            progressDialog.dismiss();
                        }
                    }
                }catch (Exception e){
                    System.err.println(e);
                }
            }
        }).start();
    }
}