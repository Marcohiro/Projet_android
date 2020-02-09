package com.projetESGI.projet;

import androidx.appcompat.app.AppCompatActivity;

import com.projetESGI.projet.Users.UsersContent.UsersItem;

import android.os.Bundle;
import android.widget.Toast;
/*
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;*/

public class MainActivity extends AppCompatActivity implements ProjectFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(UsersItem item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
    }
}
