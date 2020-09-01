package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PrivateCodeResult extends AppCompatActivity {
    private int GET_REPLY_SUCCESS = 101;
    private long N,m,P,p;
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(message.what==GET_REPLY_SUCCESS){
                String reply = message.getData().getString("result");
                if("ADD_FAILED".equals(reply)){
                    Toast.makeText(PrivateCodeResult.this, R.string.add_failed, Toast.LENGTH_LONG).show();
                }
                else if(reply == null){
                    Toast.makeText(PrivateCodeResult.this, R.string.wrong_input, Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(PrivateCodeResult.this, R.string.add_succeed, Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_code_result);
        Intent intent = getIntent();
        long N = Long.valueOf(intent.getStringExtra(MakePrivateCode.EXTRA_N)).longValue();
        long m = Long.valueOf(intent.getStringExtra(MakePrivateCode.EXTRA_m)).longValue();
        long p = Long.valueOf(intent.getStringExtra(MakePrivateCode.EXTRA_p)).longValue();
        long P = pow(N, m, p);
        this.N = N;
        this.m = m;
        this.P = P;
        this.p = p;
        String result_1 = getResources().getString(R.string.MPC_result_1);
        String result_2 = getResources().getString(R.string.MPC_result_2);
        String result_3 = getResources().getString(R.string.MPC_result_3);
        String result_4 = getResources().getString(R.string.MPC_result_4);
        String result = result_1 + ": ( " + N + " , " + m + " , " + P + " )\n" + result_2 + "\n" + result_3 + ": " + p + "\n" + result_4;
        TextView textView = findViewById(R.id.textView4);
        textView.setText(result);
    }
    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream in = conn.getInputStream();
            byte[] data = StreamTool.read(in);
            String html = new String(data, "UTF-8");
            return html;
        }
        return null;
    }
    public void signin(View view){
        EditText editText = (EditText)findViewById(R.id.editTextTextPersonName);
        String username = editText.getText().toString();
        final String URL = "http://39.96.48.80:8000/newuser/" + username + "/" + this.N + "/" + this.m + "/" + this.P + "/";
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String res = getHtml(URL);
                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("result", res);
                    message.setData(bundle);
                    message.what = GET_REPLY_SUCCESS;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private long pow(long N, long m, long p){
        if(p == 1){
            return N % m;
        }
        if(p % 2 == 0){
            long temp = pow(N, m, p / 2);
            return (temp * temp ) % m;
        }
        else{
            return (N * pow(N, m, p - 1)) % m;
        }
    }
}