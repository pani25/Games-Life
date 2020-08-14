package com.apps.gamelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class categori extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDataBase;
    Animation topAnim, bottomAnim;
    RecyclerView view;
    FirebaseRecyclerOptions<Blog> option;
    FirebaseRecyclerAdapter<Blog, BlogViewHolder1> adapterss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categori);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        view = findViewById(R.id.myrecycleview);
        view.setAnimation(topAnim);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.categori);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.categori:
                        return true;
                    case R.id.home1:
                        startActivity(new Intent(getApplicationContext()
                                ,home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                ,profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        mDataBase= FirebaseDatabase.getInstance().getReference().child("global");
        //mDataBase.keepSynced(true);

        mBlogList=findViewById(R.id.myrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        //mBlogList.setLayoutManager(new LinearLayoutManager(this));

        option = new FirebaseRecyclerOptions.Builder<Blog>().setQuery(mDataBase, Blog.class).build();
        adapterss = new FirebaseRecyclerAdapter<Blog, BlogViewHolder1>(option) {
            @Override
            protected void onBindViewHolder(@NonNull BlogViewHolder1 holder1,final int position, @NonNull Blog model) {
                Picasso.get().load(model.getImage()).into(holder1.i2, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                holder1.t1.setText(model.getTitle());

                holder1.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(categori.this, detail_game.class);
                        intent.putExtra("Desckey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public BlogViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row1, parent, false);

                return new BlogViewHolder1(view);
            }
        };


        adapterss.startListening();
        mBlogList.setAdapter(adapterss);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapterss!=null)
            adapterss.startListening();
    }

    @Override
    public void onStop() {
        if (adapterss!=null)
            adapterss.stopListening();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapterss!=null)
            adapterss.startListening();
    }

}
