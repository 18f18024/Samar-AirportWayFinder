package com.example.samar_airportwayfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateApp extends AppCompatActivity {
    TextView rateCount, showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        rateCount= (TextView)findViewById(R.id.rateCount);
        showRating= (TextView) findViewById(R.id.showRating);
        review= (EditText) findViewById(R.id.review);
        submit= (Button) findViewById(R.id.submitBtn);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue= ratingBar.getRating();
                if (rateValue<=1 && rateValue>0)
                    rateCount.setText("Bad "+rateValue + "/5");
                else if (rateValue<=2 && rateValue>1)
                    rateCount.setText("OK "+rateValue + "/5");
                else if (rateValue<=3 && rateValue>2)
                    rateCount.setText("Good "+rateValue + "/5");
                else if (rateValue<=4 && rateValue>3)
                    rateCount.setText("Very Good "+rateValue + "/5");
                else if (rateValue<=5 && rateValue>4)
                    rateCount.setText("Best "+rateValue + "/5");

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()){
                    temp= rateCount.getText().toString();
                    showRating.setText("Your Rating: \n" +temp + "\n" +review.getText());
                    review.setText("");
                    ratingBar.setRating(0);
                    rateCount.setText("");
                    Toast.makeText(RateApp.this, "Feedback submitted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean valid() {
        boolean result = false;
        String rev = review.getText().toString();
        String r_count= rateCount.getText().toString();

        if (rev.isEmpty() || r_count.isEmpty()) {
            Toast.makeText(this, "Complete rating ", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RateApp.this, HomePage.class));
        finish();


    }
}