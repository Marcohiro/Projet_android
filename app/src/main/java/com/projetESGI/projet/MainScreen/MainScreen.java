package com.projetESGI.projet.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.projetESGI.projet.LocalFiles.LocalFilesParser;
import com.projetESGI.projet.OnlineFiles.OnlineFilesParser;
import com.projetESGI.projet.R;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        findViewById(R.id.onlineFiles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOnlineFiles(v);
            }
        });

        findViewById(R.id.switchLocalView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLocalFiles(v);
            }
        });

        findViewById(R.id.leave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //leaveApplication(v);
            }
        });
    }

    private void leaveApplication(View v) {
        finish();
    }

    private void switchToLocalFiles(View v) {
        startActivity(new Intent(this, LocalFilesParser.class));
        finish();
    }

    private void switchToOnlineFiles(View v) {
        startActivity(new Intent(this, OnlineFilesParser.class));
        finish();
    }
}
