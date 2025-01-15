package com.example.samar_airportwayfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScanQR extends AppCompatActivity {
    private static final String TAG = "ScanQR";
    public static TextView u_lname,u_fname, u_phone, u_add, u_gender, u_age, u_password, u_mail;
    private Button scan, contSave;
    String user_lname, user_fname, user_uphone, user_add, user_age, user_gender, user_password, user_mail;
    NetworkInfo nInfo;
    private FirebaseDatabase firebaseDatabase;
    TextView logToScan;
    private FirebaseAuth firebaseAuth;
    String[] gend= {"Select Gender","Male","Female"};
    String s, value;
    Spinner spinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);


        u_lname= (TextView) findViewById(R.id.s_lname);
        u_fname= (TextView) findViewById(R.id.s_fname);
        u_phone= (TextView) findViewById(R.id.s_phone);
        u_add= (TextView) findViewById(R.id.s_add);
        u_gender= (TextView) findViewById(R.id.s_gender);
        u_age= (TextView) findViewById(R.id.s_age);
        u_password= (TextView) findViewById(R.id.password);
        u_mail= (TextView) findViewById(R.id.mail);
        logToScan= (TextView)findViewById(R.id.logToScan);


        scan= (Button)findViewById(R.id.scan);
        contSave= (Button)findViewById(R.id.contSave);

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });


        contSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    if (nInfo != null && nInfo.isConnected()) {
                        String email_user = u_mail.getText().toString();
                        String pass_user = u_password.getText().toString().trim();
                        final String mFName = u_fname.getText().toString().trim();
                        final String mLName = u_lname.getText().toString().trim();
                        final String mAddress = u_add.getText().toString().trim();
                        final String mGender= u_gender.getText().toString().trim();
                        final String mAge = u_age.getText().toString().trim();
                        final String mphone = u_phone.getText().toString().trim();


                        firebaseAuth.createUserWithEmailAndPassword(email_user, pass_user).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ScanQR.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ScanQR.this, Login.class));

                                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                    if (firebaseUser != null) {
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                                        UserProfile userInfo = new UserProfile(mFName, mLName, mAddress, mAge, mGender, mphone);
                                        ref.child(firebaseUser.getUid()).child("Profile").setValue(userInfo);
                                    }

                                    finish();
                                } //task successful
                                else {
                                    Toast.makeText(ScanQR.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }//void complete
                        });//addOnComplete

                    } else {
                        Toast.makeText(ScanQR.this, "Network is not available", Toast.LENGTH_LONG).show();
                    }
                }//validate
            }
        });
        logToScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScanQR.this, Login.class));
                finish();
            }
        });

    }
    private Boolean validate(){
        boolean result= false;

        user_lname = u_lname.getText().toString();
        user_fname = u_fname.getText().toString();
        user_uphone=u_phone.getText().toString();
        user_add= u_add.getText().toString();
        user_age= u_age.getText().toString();
        user_gender= u_gender.getText().toString();
        user_password= u_password.getText().toString();
        user_mail= u_mail.getText().toString();
        if(user_lname.isEmpty() ||  user_fname.isEmpty() || user_gender.isEmpty()
                || user_uphone.isEmpty() || user_add.isEmpty() || user_age.isEmpty()
                || user_password.isEmpty() || user_mail.isEmpty()){
            Toast.makeText(this, "Scan QR code to Continue", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}