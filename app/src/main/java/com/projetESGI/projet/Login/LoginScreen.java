package com.projetESGI.projet.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.drive.DriveScopes;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.projetESGI.projet.CreateAccount.CreateAccount;
import com.projetESGI.projet.MainScreen.MainScreen;
import com.projetESGI.projet.R;

import java.util.Arrays;


public class LoginScreen extends AppCompatActivity {

    static final int REQUEST_ACCOUNT_PICKER = 1;
    private GoogleAccountCredential mCredential;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mCredential = GoogleAccountCredential.usingOAuth2(this, Arrays.asList(DriveScopes.DRIVE));
        startActivityForResult(mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);

        findViewById(R.id.creerCompte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateAccount(v);
            }
        });

        findViewById(R.id.connection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                final EditText username = findViewById(R.id.name);
                final EditText password = findViewById(R.id.pass);
                final DocumentReference doc = db.collection("Users").document(username.getText().toString());
                doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                if(password.getText().toString().equals(document.getData().get("password").toString())){
                                    Toast.makeText(v.getContext(),"Authentification reussie", Toast.LENGTH_SHORT).show();
                                    goToMainActivity(v);
                                } else {
                                    Toast.makeText(v.getContext(),"Le mot de passe est invalide, veuillez en saisir un autre", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(v.getContext(),"Le nom d'utilisateur n'existe pas, veuillez en saisir un autre ou creer un compte", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }

    private void goToMainActivity(View v) {
        startActivity(new Intent(this, MainScreen.class));
        finish();
    }

    private void goToCreateAccount(View v) {
        startActivity(new Intent(this, CreateAccount.class));
        finish();
    }

}
