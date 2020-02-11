package com.projetESGI.projet;

import androidx.appcompat.app.AppCompatActivity;

import com.projetESGI.projet.LocalFiles.LocalFile;
import com.projetESGI.projet.LocalFiles.LocalFilesParser;
import com.projetESGI.projet.Users.UsersContent.UsersItem;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/*
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;*/

public class MainActivity extends AppCompatActivity implements ProjectFragment.OnListFragmentInteractionListener {


    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lunch(v);
            }
        });



    }

    public void lunch(View view) {
        Intent intent = new Intent(this, LocalFile.class);
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(UsersItem item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
    }
}
