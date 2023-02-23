package com.fis.digitalpayments.sdk.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startZelle(View view) {
        Intent intent = new Intent(this, ZelleWebviewActivity.class);
        startActivity(intent);
    }
}
