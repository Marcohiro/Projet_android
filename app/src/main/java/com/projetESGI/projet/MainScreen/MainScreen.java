package com.projetESGI.projet.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.drive.DriveScopes;
import com.projetESGI.projet.LocalFiles.LocalFilesParser;
import com.projetESGI.projet.OnlineFiles.OnlineFilesParser;
import com.projetESGI.projet.R;

import java.util.Arrays;

public class MainScreen extends AppCompatActivity {
    static final int REQUEST_ACCOUNT_PICKER = 1;
    private GoogleAccountCredential mCredential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mCredential = GoogleAccountCredential.usingOAuth2(this, Arrays.asList(DriveScopes.DRIVE));
        startActivityForResult(mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
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
