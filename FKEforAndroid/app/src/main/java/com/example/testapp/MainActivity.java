package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.testapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user taps the Send button */
    public void toPrivateMake(View view){
        Intent intent = new Intent(this, MakePublicCode.class);
        startActivity(intent);
    }
    public void toPrivateCodeMaker(View view){
        Intent intent = new Intent(this, MakePrivateCode.class);
        startActivity(intent);
    }
    public void toCommunicationCodeMaker(View view){
        Intent intent = new Intent(this, MakePublicCode.class);
        startActivity(intent);
    }
    public void toKeyMaker(View view){
        Intent intent = new Intent(this, CulculateKey.class);
        startActivity(intent);
    }
}