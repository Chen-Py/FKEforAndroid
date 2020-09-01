package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MakePrivateCode extends AppCompatActivity {
    public static final String EXTRA_N = "com.example.testapp.N";
    public static final String EXTRA_m = "com.example.testapp.m";
    public static final String EXTRA_p = "com.example.testapp.p";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_private_code);
    }

    public void culculate(View view){
        Intent intent = new Intent(this, PrivateCodeResult.class);
        EditText editText1 = (EditText) findViewById(R.id.PCinput1);
        String N = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.PCinput2);
        String m = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.PCinput3);
        String p = editText3.getText().toString();
        if(judge(N) && judge(m) && judge(p)) {
            intent.putExtra(EXTRA_N, N);
            intent.putExtra(EXTRA_m, m);
            intent.putExtra(EXTRA_p, p);
            startActivity(intent);
        }
        else{
            Toast.makeText(MakePrivateCode.this, R.string.wrong_input, Toast.LENGTH_LONG).show();
        }
    }
    public void makeRandom1(View view){
        Random rnd = new Random();
        int tmp = rnd.nextInt(2147483646) + 1;
        EditText editText = (EditText) findViewById(R.id.PCinput1);
        editText.setText((tmp + ""));
    }
    public void makeRandom2(View view){
        Random rnd = new Random();
        int tmp = rnd.nextInt(2147483646) + 1;
        EditText editText = (EditText) findViewById(R.id.PCinput2);
        editText.setText((tmp + ""));
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