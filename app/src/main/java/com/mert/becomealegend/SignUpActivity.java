package com.mert.becomealegend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextName;
    EditText editTextSurname;
    EditText editTextEmail;
    EditText editTextPassword;
    TextView textViewLogin;
    Button buttonSignUp;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        textViewLogin = (TextView)findViewById(R.id.textViewSignUp);
        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextSurname = (EditText)findViewById(R.id.editTextSurname);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        textViewLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    public void RegisterUser(){
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String surname = editTextSurname.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(name.isEmpty()){
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if(surname.isEmpty()){
            editTextSurname.setError("Surname is required");
            editTextSurname.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Minimum length of password should be 6!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = currentUser.getUid();

                    mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("email", email);
                    userMap.put("name", name);
                    userMap.put("surname",surname);

                    mDatabaseReference.setValue(userMap);

                    Toast.makeText(getApplicationContext(), "User registered succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User is already registered!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == buttonSignUp.getId()){
            RegisterUser();
        }

        if(v.getId() == textViewLogin.getId()){
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
