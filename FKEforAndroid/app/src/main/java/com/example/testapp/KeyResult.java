package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class KeyResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_result);
        Intent intent = getIntent();
        long Q = Long.valueOf(intent.getStringExtra(CulculateKey.EXTRA_Q)).longValue();
        long m = Long.valueOf(intent.getStringExtra(CulculateKey.EXTRA_m)).longValue();
        long p = Long.valueOf(intent.getStringExtra(CulculateKey.EXTRA_p)).longValue();
        long K = pow(Q, m, p);
        String result_1 = getResources().getString(R.string.CK_result_1);
        String result_2 = getResources().getString(R.string.CK_result_2);
        String result = result_1 + ": " + K + "\n" + result_2;
        TextView textView = findViewById(R.id.textView6);
        textView.setText(result);
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