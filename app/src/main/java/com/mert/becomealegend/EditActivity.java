package com.mert.becomealegend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import com.mert.becomealegend.Profiles.calisan;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;

    EditText userName;
    EditText userSurname;
    EditText userEmail;
    EditText userPhone;
    EditText userProfession;
    EditText userLocation;
    EditText userDesignation;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String uid = mUser.getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        userName = (EditText)findViewById(R.id.editTextName);
        userSurname = (EditText)findViewById(R.id.editTextSurname);
        userEmail = (EditText)findViewById(R.id.editTextEmail);
        userPhone = (EditText)findViewById(R.id.editTextPhone);
        userProfession = (EditText)findViewById(R.id.editTextProfession);
        userLocation = (EditText)findViewById(R.id.editTextLocation);
        userDesignation = (EditText)findViewById(R.id.editTextDesignation);
        save = (Button)findViewById(R.id.buttonSave);

        save.setOnClickListener(this);

        getUserInformation();
    }

    public void getUserInformation(){

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                calisan calisan = dataSnapshot.getValue(calisan.class);
                String calisanName = calisan.getName();
                String calisanSurname = calisan.getSurname();
                String calisanEmail = calisan.getEmail();
                String calisanPhone = calisan.getPhone();
                String calisanProfession = calisan.getProfession();
                String calisanLocation = calisan.getLocation();
                String calisanDesignation = calisan.getDesignation();

                userName.setText(calisanName);
                userSurname.setText(calisanSurname);
                userEmail.setText(calisanEmail);
                userPhone.setText(calisanPhone);
                userProfession.setText(calisanProfession);
                userLocation.setText(calisanLocation);
                userDesignation.setText(calisanDesignation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void userEdit(){
        final String usersName = userName.getText().toString().trim();
        final String usersSurname = userSurname.getText().toString().trim();
        final String usersEmail = userEmail.getText().toString().trim();
        final String usersPhone = userPhone.getText().toString().trim();
        final String usersProfession = userProfession.getText().toString().trim();
        final String usersLocation = userLocation.getText().toString().trim();
        final String usersDesignation = userDesignation.getText().toString().trim();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                calisan calisan = dataSnapshot.getValue(calisan.class);

                userName.setText(usersName);
                userSurname.setText(usersSurname);
                userEmail.setText(usersEmail);
                userPhone.setText(usersPhone);
                userProfession.setText(usersProfession);
                userLocation.setText(usersLocation);
                userDesignation.setText(usersDesignation);

                mUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = mUser.getUid();

                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("email", usersEmail);
                userMap.put("name", usersName);
                userMap.put("surname",usersSurname);
                userMap.put("phone",usersPhone);
                userMap.put("profession",usersProfession);
                userMap.put("location",usersLocation);
                userMap.put("designation",usersDesignation);

                mDatabaseReference.setValue(userMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == save.getId()){
            userEdit();
            Toast.makeText(getApplicationContext(),"User informations saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(EditActivity.this, ProfileActivity.class);
            startActivity(i);
        }
    }
}
