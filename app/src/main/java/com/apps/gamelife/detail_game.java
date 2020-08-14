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

public class detail_game extends AppCompatActivity {

    private ImageView imageView;
    TextView textView_titlee, textView_dessc;

    DatabaseReference references;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_game);

        imageView = findViewById(R.id.image_game);
        textView_titlee = findViewById(R.id.title_game);
        textView_dessc = findViewById(R.id.desc_game);

        references = FirebaseDatabase.getInstance().getReference().child("global");

        String Desckey = getIntent().getStringExtra("Desckey");

        references.child(Desckey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {

                    String title=dataSnapshot.child("title").getValue().toString();
                    String dess=dataSnapshot.child("desc").getValue().toString();
                    String image=dataSnapshot.child("image").getValue().toString();

                    Picasso.get().load(image).into(imageView);
                    textView_titlee.setText(title);
                    textView_dessc.setText(dess);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
