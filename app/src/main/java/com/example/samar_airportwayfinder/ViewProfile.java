package com.example.samar_airportwayfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.util.Locale;

public class ViewProfile extends AppCompatActivity {
    private TextView u_lname, u_fname, u_phone, u_add, u_gender, u_age;
    private Button update, r_cont;
    String user_lname, user_fname, user_uphone, user_add, user_gender, user_age;
    NetworkInfo nInfo;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    ImageView speaks, speaks1, speaks2;
    TextToSpeech textToSpeech, textToSpeech1, textToSpeech2;
    TextView speak_text;
    String a,b,c,d,e,f;
    ImageView animatedBorder, animatedBorder1, animatedBorder2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        u_lname= (TextView) findViewById(R.id.e_laname);
        u_fname= (TextView) findViewById(R.id.e_finame);
        u_phone= (TextView) findViewById(R.id.ei_phone);
        u_add= (TextView) findViewById(R.id.ei_add);
        u_gender= (TextView) findViewById(R.id.ei_gender);
        u_age= (TextView) findViewById(R.id.ei_age);
        r_cont= (Button)findViewById(R.id.r_cont);
        speaks= (ImageView) findViewById(R.id.speaks);
        speaks1= (ImageView) findViewById(R.id.speaks1);
        speaks2= (ImageView) findViewById(R.id.speaks2);
        speak_text= (TextView) findViewById(R.id.speak_text);
        animatedBorder = findViewById(R.id.animatedBorder);
        animatedBorder1 = findViewById(R.id.animatedBorder1);
        animatedBorder2 = findViewById(R.id.animatedBorder2);


        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, 360f, // Start and end angle
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X (center)
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot Y (center)
        );
        rotateAnimation.setDuration(1500); // Rotation duration in milliseconds
        rotateAnimation.setRepeatCount(Animation.INFINITE); // Infinite loop
        rotateAnimation.setInterpolator(new LinearInterpolator()); // Smooth rotation

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);

                    // Set up listener to detect TTS progress
                    textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            // Show and start animation
                            runOnUiThread(() -> {
                                animatedBorder.setVisibility(View.VISIBLE);
                                animatedBorder.startAnimation(rotateAnimation);
                            });
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            // Stop animation
                            runOnUiThread(() -> {
                                animatedBorder.clearAnimation();
                                animatedBorder.setVisibility(View.GONE);
                            });
                        }

                        @Override
                        public void onError(String utteranceId) {
                            // Stop animation on error
                            runOnUiThread(() -> {
                                animatedBorder.clearAnimation();
                                animatedBorder.setVisibility(View.GONE);
                            });
                        }
                    });
                }
            }
        });


        textToSpeech1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech1.setLanguage(Locale.FRENCH);

                    // Set up listener to detect TTS progress
                    textToSpeech1.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            // Show and start animation
                            runOnUiThread(() -> {
                                animatedBorder1.setVisibility(View.VISIBLE);
                                animatedBorder1.startAnimation(rotateAnimation);
                            });
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            // Stop animation
                            runOnUiThread(() -> {
                                animatedBorder1.clearAnimation();
                                animatedBorder1.setVisibility(View.GONE);
                            });
                        }

                        @Override
                        public void onError(String utteranceId) {
                            // Stop animation on error
                            runOnUiThread(() -> {
                                animatedBorder1.clearAnimation();
                                animatedBorder1.setVisibility(View.GONE);
                            });
                        }
                    });
                }
            }
        });

        textToSpeech2 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech2.setLanguage(Locale.GERMAN);

                    // Set up listener to detect TTS progress
                    textToSpeech2.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                            // Show and start animation
                            runOnUiThread(() -> {
                                animatedBorder2.setVisibility(View.VISIBLE);
                                animatedBorder2.startAnimation(rotateAnimation);
                            });
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            // Stop animation
                            runOnUiThread(() -> {
                                animatedBorder2.clearAnimation();
                                animatedBorder2.setVisibility(View.GONE);
                            });
                        }

                        @Override
                        public void onError(String utteranceId) {
                            // Stop animation on error
                            runOnUiThread(() -> {
                                animatedBorder2.clearAnimation();
                                animatedBorder2.setVisibility(View.GONE);
                            });
                        }
                    });
                }
            }
        });


        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase= FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        // final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(val);
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
                Toast.makeText(ViewProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        speaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a= u_fname.getText().toString();
                b=u_lname.getText().toString();
                c= u_phone.getText().toString();
                d= u_add.getText().toString();
                e= u_gender.getText().toString();
                f= u_age.getText().toString();

                c=String.join(" ",c.split(""));
                String m= c;
                speak_text.setText("User First Name is "+a+ " Last Name is "+b+" Contact Number is "+m+" Address is "+d+ " Gender is "+ e+ " Age is "+f);
                String s=speak_text.getText().toString();
                int speach=textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, "utteranceId");


            }
        });

        speaks1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a= u_fname.getText().toString();
                b=u_lname.getText().toString();
                c= u_phone.getText().toString();
                d= u_add.getText().toString();
                e= u_gender.getText().toString();
                f= u_age.getText().toString();

                c=String.join(" ",c.split(""));
                String m= c;
                speak_text.setText("User First Name is "+a+ " Last Name is "+b+" Contact Number is "+m+" Address is "+d+ " Gender is "+ e+ " Age is "+f);
                String s=speak_text.getText().toString();
                int speach=textToSpeech1.speak(s, TextToSpeech.QUEUE_FLUSH, null, "utteranceId");

            }
        });

        speaks2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a= u_fname.getText().toString();
                b=u_lname.getText().toString();
                c= u_phone.getText().toString();
                d= u_add.getText().toString();
                e= u_gender.getText().toString();
                f= u_age.getText().toString();

                c=String.join(" ",c.split(""));
                String m= c;
                speak_text.setText("User First Name is "+a+ " Last Name is "+b+" Contact Number is "+m+" Address is "+d+ " Gender is "+ e+ " Age is "+f);
                String s=speak_text.getText().toString();
                int speach=textToSpeech2.speak(s, TextToSpeech.QUEUE_FLUSH, null, "utteranceId");

            }
        });

        r_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfile.this, HomePage.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ViewProfile.this, HomePage.class));
        finish();

    }
}