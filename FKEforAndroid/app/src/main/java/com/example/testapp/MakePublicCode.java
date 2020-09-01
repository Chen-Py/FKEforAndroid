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

public class MakePublicCode extends AppCompatActivity {
    private int GET_REPLY_SUCCESS = 101;
    public static final String EXTRA_N = "com.example.testapp.N";
    public static final String EXTRA_m = "com.example.testapp.m";
    public static final String EXTRA_P = "com.example.testapp.P";
    public static final String EXTRA_q = "com.example.testapp.q";
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(message.what==GET_REPLY_SUCCESS){
                String reply = message.getData().getString("result");
                if("CHECK_FAILED".equals(reply)){
                    Toast.makeText(MakePublicCode.this, R.string.check_failed, Toast.LENGTH_LONG).show();
                }
                else if(reply == null){
                    Toast.makeText(MakePublicCode.this, R.string.wrong_input, Toast.LENGTH_LONG).show();
                }
                else{
                    int len = reply.length();
                    String res = reply.substring(1, len - 1);
                    String[] POC = res.split(", ");
                    EditText input_N = (EditText)findViewById(R.id.MakePublicCodeInput1);
                    input_N.setText(POC[0]);
                    EditText input_m = (EditText)findViewById(R.id.MakePublicCodeInput2);
                    input_m.setText(POC[1]);
                    EditText input_P = (EditText)findViewById(R.id.MakePublicCodeInput3);
                    input_P.setText(POC[2]);
                    Toast.makeText(MakePublicCode.this, R.string.check_succeed, Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_public_code);
    }
    public void culculate(View view){
        Intent intent = new Intent(this, PublicCodeResult.class);
        EditText editText1 = (EditText) findViewById(R.id.MakePublicCodeInput1);
        String N = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.MakePublicCodeInput2);
        String m = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.MakePublicCodeInput3);
        String P = editText3.getText().toString();
        EditText editText4 = (EditText) findViewById(R.id.makePublicCodeInput4);
        String q = editText4.getText().toString();
        if(judge(N) && judge(m) && judge(P) && judge(q)) {
            intent.putExtra(EXTRA_N, N);
            intent.putExtra(EXTRA_m, m);
            intent.putExtra(EXTRA_P, P);
            intent.putExtra(EXTRA_q, q);
            startActivity(intent);
        }
        else{
            Toast.makeText(MakePublicCode.this, R.string.wrong_input, Toast.LENGTH_LONG).show();
        }
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
    public void getPOC(View view){
        EditText editText5 = (EditText) findViewById(R.id.editTextTextPersonName2);
        String username = editText5.getText().toString();
        final String URL = "http://39.96.48.80:8000/getuser/" + username + "/";
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
    private boolean judge(String str){
        if(str.equals(""))return false;
        Long val;
        try{
            val = Long.valueOf(str).longValue();
        }catch(Exception e){
            return false;
        }
        if(val > 3e9)return false;
        else return true;
    }
}