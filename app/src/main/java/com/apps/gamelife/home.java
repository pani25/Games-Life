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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class home extends AppCompatActivity {

    private RecyclerView mBlogList, mGameList, mGaleriList;
    private DatabaseReference mDataBase, mDatabase2;
    Animation topAnim, bottomAnim;
    RelativeLayout relatif, bottom1;
    FirebaseRecyclerOptions<Blog> options;
    FirebaseRecyclerOptions<Blog2> options2;
    FirebaseRecyclerOptions<Blog3> options3;
    FirebaseRecyclerAdapter<Blog, BlogViewHolder1> adapters;
    FirebaseRecyclerAdapter<Blog2, BlogViewHolder2> adapters2;
    FirebaseRecyclerAdapter<Blog3, BlogViewHolder3> adapters3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        relatif = findViewById(R.id.mid);
        bottom1 = findViewById(R.id.game);
        relatif.setAnimation(topAnim);
        bottom1.setAnimation(bottomAnim);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home1);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.categori:
                        startActivity(new Intent(getApplicationContext()
                                ,categori.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home1:
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


        mBlogList= findViewById(R.id.myrecycleview0);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Blog>().setQuery(mDataBase, Blog.class).build();

        adapters = new FirebaseRecyclerAdapter<Blog, BlogViewHolder1>(options) {
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
                        Intent intent = new Intent(home.this, detail_game.class);
                        intent.putExtra("Desckey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public BlogViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row, parent, false);

                return new BlogViewHolder1(view);
            }
        };
        adapters.startListening();
        mBlogList.setAdapter(adapters);



        mDataBase= FirebaseDatabase.getInstance().getReference().child("global");


        mGameList=findViewById(R.id.myrecycleview2);
        mGameList.setHasFixedSize(true);
        mGameList.setLayoutManager(new LinearLayoutManager(this));

        options2 = new FirebaseRecyclerOptions.Builder<Blog2>().setQuery(mDataBase, Blog2.class).build();

        adapters2 = new FirebaseRecyclerAdapter<Blog2, BlogViewHolder2>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull BlogViewHolder2 holder2,final int position, @NonNull Blog2 model2) {
                Picasso.get().load(model2.getImage()).into(holder2.i2, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                holder2.t1.setText(model2.getTitle());

                holder2.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(home.this, detail_game.class);
                        intent.putExtra("Desckey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public BlogViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list, parent, false);
                return new BlogViewHolder2(view);
            }
        };

        adapters2.startListening();
        mGameList.setAdapter(adapters2);

        mDatabase2= FirebaseDatabase.getInstance().getReference().child("screen");
        //mDataBase.keepSynced(true);

        mGaleriList=findViewById(R.id.myrecycleview4);
        mGaleriList.setHasFixedSize(true);
        mGaleriList.setLayoutManager(new LinearLayoutManager(this));
        //mBlogList.setLayoutManager(new LinearLayoutManager(this));

        options3 = new FirebaseRecyclerOptions.Builder<Blog3>().setQuery(mDatabase2, Blog3.class).build();
        adapters3 = new FirebaseRecyclerAdapter<Blog3, BlogViewHolder3>(options3) {
            @Override
            protected void onBindViewHolder(@NonNull BlogViewHolder3 holder3,final int position, @NonNull Blog3 model) {
                Picasso.get().load(model.getImage()).into(holder3.i2, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
                holder3.t1.setText(model.getTitle());

                holder3.v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                Intent intent = new Intent(home.this, detail_galeri.class);
                intent.putExtra("galerikey",getRef(position).getKey());
                startActivity(intent);
                     }
                });

            }

            @NonNull
            @Override
            public BlogViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.galeri_list, parent, false);

                return new BlogViewHolder3(view);
            }
        };


        adapters3.startListening();
        mGaleriList.setAdapter(adapters3);
    }


    @Override
    public void onStart() {
        super.onStart();


        if (adapters!=null)
            adapters.startListening();
    }

    @Override
    public void onStop() {


        if (adapters!=null)
            adapters.stopListening();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();


        if (adapters!=null)
            adapters.startListening();
    }


    public void menu(View view) {
        Intent intent = new Intent(home.this, categori.class);
        startActivity(intent);
    }

   @Override
   public void onBackPressed() {

        Toast.makeText(home.this, "Please Click the home Button",Toast.LENGTH_LONG).show();
    }

    public void galeri(View view) {
        Intent intent = new Intent(home.this, galeri.class);
        startActivity(intent);
    }
}
