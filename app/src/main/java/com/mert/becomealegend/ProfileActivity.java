package com.mert.becomealegend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.mert.becomealegend.Profiles.calisan;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;

    ImageView edit;

    TextView usersNameSurname;
    TextView usersEmail;
    TextView usersPhone;
    TextView usersProfession;
    TextView usersLocation;
    TextView usersDesignation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String uid = mUser.getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        usersNameSurname = (TextView)findViewById(R.id.name);
        usersEmail = (TextView)findViewById(R.id.textViewShowEmail);
        usersPhone = (TextView)findViewById(R.id.textViewShowPhone) ;
        usersProfession = (TextView)findViewById(R.id.textViewShowProfession);
        usersLocation = (TextView)findViewById(R.id.location);
        usersDesignation = (TextView)findViewById(R.id.designation);

        edit = (ImageView)findViewById(R.id.edit);

        edit.setOnClickListener(this);

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

                usersNameSurname.setText(calisanName+" "+calisanSurname);
                usersEmail.setText(calisanEmail);
                usersPhone.setText(calisanPhone);
                usersProfession.setText(calisanProfession);
                usersLocation.setText(calisanLocation);
                usersDesignation.setText(calisanDesignation);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == edit.getId()){
            Intent i = new Intent(ProfileActivity.this, EditActivity.class);
            startActivity(i);
        }
    }
}
