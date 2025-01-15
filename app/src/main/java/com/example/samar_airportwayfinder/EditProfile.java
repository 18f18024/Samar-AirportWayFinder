package com.example.samar_airportwayfinder;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {
    private EditText u_lname, u_fname, u_phone, u_add, u_gender, u_age;
    private Button update;
    String user_lname, user_fname, user_uphone, user_add, user_gender, user_age;
    NetworkInfo nInfo;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        u_lname= (EditText) findViewById(R.id.e_lname);
        u_fname= (EditText) findViewById(R.id.e_fname);
        u_phone= (EditText) findViewById(R.id.e_phone);
        u_add= (EditText) findViewById(R.id.e_add);
        u_gender= (EditText) findViewById(R.id.e_gender);
        u_age= (EditText) findViewById(R.id.e_age);
        update= (Button)findViewById(R.id.e_update) ;

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase= FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile= dataSnapshot.getValue(UserProfile.class);
                u_lname.setText(userProfile.getLastName());
                u_fname.setText(userProfile.getFirstName());
                u_phone.setText(userProfile.getUserPhone());
                u_add.setText(userProfile.getUserAddress());
                u_gender.setText(userProfile.getUserGender());
                u_age.setText(userProfile.getUserAge());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EditProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null) {
                    if (validate()) {
                        if (nInfo != null && nInfo.isConnected()) {
                            UserProfile userProfile = new UserProfile(user_fname, user_lname, user_add, user_age, user_gender, user_uphone);
                            ref.setValue(userProfile);
                            Toast.makeText(EditProfile.this, "Profile is Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditProfile.this, HomePage.class));
                            finish();
                        }
                        else {
                            Toast.makeText(EditProfile.this, "Network is not available", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }

    private Boolean validate(){
        boolean result= false;

        user_lname = u_lname.getText().toString();
        user_fname = u_fname.getText().toString();
        user_uphone=u_phone.getText().toString();
        user_add= u_add.getText().toString();
        user_gender= u_gender.getText().toString();
        user_age= u_age.getText().toString();
        if(user_lname.isEmpty() ||  user_fname.isEmpty() || user_gender.isEmpty()
                || user_uphone.isEmpty() || user_add.isEmpty() || user_age.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditProfile.this, HomePage.class));
        finish();

    }
}