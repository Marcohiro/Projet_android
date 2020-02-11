package com.projetESGI.projet;

import androidx.appcompat.app.AppCompatActivity;

import com.projetESGI.projet.Users.UsersContent.UsersItem;

import android.os.Bundle;
import android.widget.Toast;

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
