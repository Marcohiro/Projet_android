package com.projetESGI.projet.CreateAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.projetESGI.projet.Login.LoginScreen;
import com.projetESGI.projet.MainActivity;
import com.projetESGI.projet.R;
import com.projetESGI.projet.Users.UsersContent.UsersItem;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Click creation compte
        findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                final UsersItem user = new UsersItem();
                final EditText username = findViewById(R.id.userName);
                final EditText password = findViewById(R.id.password);
                final EditText confirmPassword = findViewById(R.id.confirmPassword);
                if(password.getText().toString().equals(confirmPassword.getText().toString())) {
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    final DocumentReference doc = db.collection("Users").document(username.getText().toString());
                    doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                            if(task.isSuccessful()){
                                                                DocumentSnapshot document = task.getResult();
                                                                if(document.exists()){
                                                                    Toast.makeText(v.getContext(),"Le nom d'utilisateur existe deja, veuillez en saisir un autre", Toast.LENGTH_SHORT).show();
                                                                } else {
                                                                    db.collection("Users").document(username.getText().toString())
                                                                            .set(user)
                                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Log.d("TAG", "DocumentSnapshot successfully written!");
                                                                                }
                                                                            })
                                                                            .addOnFailureListener(new OnFailureListener() {
                                                                                @Override
                                                                                public void onFailure(@NonNull Exception e) {
                                                                                    Log.w("TAG", "Error writing document", e);
                                                                                }
                                                                            });                                                                }
                                                            }
                                                        }
                                                    });
                } else {
                    //Todo empeche l'access
                    Toast.makeText(v.getContext(),"Les mots de passe ne correspondent pas, veuillez modifier les mots de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Click retour
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToLoginScreen(v);
            }
        });

    }

    private void returnToLoginScreen(View v) {
        startActivity(new Intent(this, LoginScreen.class));
        finish();
    }

}
