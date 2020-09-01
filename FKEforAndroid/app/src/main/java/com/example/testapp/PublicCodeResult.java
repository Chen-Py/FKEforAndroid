package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PublicCodeResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_code_result);
        Intent intent = getIntent();
        long N = Long.valueOf(intent.getStringExtra(MakePublicCode.EXTRA_N)).longValue();
        long m = Long.valueOf(intent.getStringExtra(MakePublicCode.EXTRA_m)).longValue();
        long P = Long.valueOf(intent.getStringExtra(MakePublicCode.EXTRA_P)).longValue();
        long q = Long.valueOf(intent.getStringExtra(MakePublicCode.EXTRA_q)).longValue();
        long Q = pow(N, m, q);
        long K = pow(P, m, q);
        String result_1 = getResources().getString(R.string.MCC_result_1);
        String result_2 = getResources().getString(R.string.MCC_result_2);
        String result_3 = getResources().getString(R.string.MCC_result_3);
        String result_4 = getResources().getString(R.string.MCC_result_4);
        String result_5 = getResources().getString(R.string.MCC_result_5);
        String result_6 = getResources().getString(R.string.MCC_result_6);
        String result = result_1 + ": " + Q + "\n" + result_2 + "\n" + result_3 + ": " + q + "\n" + result_4 + "\n" + result_5 + ": " + K + "\n" + result_6;
        TextView textView = findViewById(R.id.textView5);
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