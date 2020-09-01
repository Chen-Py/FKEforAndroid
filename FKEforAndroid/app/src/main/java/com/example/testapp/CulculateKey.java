package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CulculateKey extends AppCompatActivity {
    public static final String EXTRA_Q = "com.example.testapp.Q";
    public static final String EXTRA_m = "com.example.testapp.m";
    public static final String EXTRA_p = "com.example.testapp.p";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culculate_key);
    }
    public void culculate(View view){
        Intent intent = new Intent(this, KeyResult.class);
        EditText editText1 = (EditText) findViewById(R.id.CKinput1);
        String Q = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.CKinput2);
        String m = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.CKinput3);
        String p = editText3.getText().toString();
        if(judge(Q) && judge(m) && judge(p)) {
            intent.putExtra(EXTRA_Q, Q);
            intent.putExtra(EXTRA_m, m);
            intent.putExtra(EXTRA_p, p);
            startActivity(intent);
        }
        else{
            Toast.makeText(CulculateKey.this, R.string.wrong_input, Toast.LENGTH_LONG).show();
        }
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