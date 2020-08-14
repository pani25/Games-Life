package com.apps.gamelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class detail_galeri extends AppCompatActivity {

    private ImageView imageView, img1, img2, img3;
    TextView textView_titlee;

    DatabaseReference references1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_galeri);

        imageView = findViewById(R.id.image_game1);
        img1 = findViewById(R.id.image_game2);
        img2 = findViewById(R.id.image_game3);
        img3 = findViewById(R.id.image_game4);
        textView_titlee = findViewById(R.id.title_game1);

        references1 = FirebaseDatabase.getInstance().getReference().child("screen");

        String galerikey = getIntent().getStringExtra("galerikey");

        references1.child(galerikey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                if (dataSnapshot1.exists())
                {

                    String title=dataSnapshot1.child("title").getValue().toString();
                    String image=dataSnapshot1.child("image").getValue().toString();
                    String image2=dataSnapshot1.child("img1").getValue().toString();
                    String image3=dataSnapshot1.child("img2").getValue().toString();
                    String image4=dataSnapshot1.child("img3").getValue().toString();

                    Picasso.get().load(image).into(imageView);
                    Picasso.get().load(image2).into(img1);
                    Picasso.get().load(image3).into(img2);
                    Picasso.get().load(image4).into(img3);
                    textView_titlee.setText(title);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
