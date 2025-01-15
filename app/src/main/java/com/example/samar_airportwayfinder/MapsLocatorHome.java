package com.example.samar_airportwayfinder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MapsLocatorHome extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    ImageView prayer, rest, bank, waiting,toilet, duty, airoff;
    Button prayer_b, rest_b, bank_b, waiting_b,toilet_b, duty_b, airoff_b;

    public static final String SHARED_PREFS= "sharedPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_locator_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth= FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        prayer_b= (Button) findViewById(R.id.prayer);
        rest_b =(Button) findViewById(R.id.rest);
        bank_b =(Button) findViewById(R.id.bank);
        waiting_b=(Button) findViewById(R.id.waiting);
        toilet_b =(Button) findViewById(R.id.toilet);
        duty_b =(Button) findViewById(R.id.duty);
        airoff_b=(Button) findViewById(R.id.office);

        prayer= (ImageView) findViewById(R.id.prayer_map);
        rest =(ImageView) findViewById(R.id.rest_map);
        bank =(ImageView) findViewById(R.id.bank_map);
        waiting=(ImageView) findViewById(R.id.waiting_map);
        toilet =(ImageView) findViewById(R.id.toilet_map);
        duty =(ImageView) findViewById(R.id.duty_map);
        airoff=(ImageView) findViewById(R.id.office_map);

        //Images call
        prayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapPrayer.class));
                finish();
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapRest.class));
                finish();
            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapBank.class));
                finish();
            }
        });
        waiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapWaiting.class));
                finish();
            }
        });
        toilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapToilet.class));
                finish();
            }
        });
        duty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapDuty.class));
                finish();
            }
        });
        airoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapOffice.class));
                finish();
            }
        });

        //Buttons call
        prayer_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapPrayer.class));
                finish();
            }
        });
        rest_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapRest.class));
                finish();
            }
        });
        bank_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapBank.class));
                finish();
            }
        });
        waiting_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapWaiting.class));
                finish();
            }
        });
        toilet_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapToilet.class));
                finish();
            }
        });
        duty_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapDuty.class));
                finish();
            }
        });
        airoff_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsLocatorHome.this, MapOffice.class));
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout_menu) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.clear();
            editor.commit();
            firebaseAuth.signOut();
            finish();
            Toast.makeText(MapsLocatorHome.this, "Account Logout", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MapsLocatorHome.this, Login.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MapsLocatorHome.this, HomePage.class));
        finish();


    }
}