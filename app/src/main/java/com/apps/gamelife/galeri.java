package com.apps.gamelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class galeri extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDataBase;
    FirebaseRecyclerOptions<Blog3> optionsss;
    FirebaseRecyclerAdapter<Blog3, BlogViewHolder3> adaptersss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri);


        mDataBase= FirebaseDatabase.getInstance().getReference().child("screen");
        //mDataBase.keepSynced(true);

        mBlogList=findViewById(R.id.myrecycleview3);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        //mBlogList.setLayoutManager(new LinearLayoutManager(this));

        optionsss = new FirebaseRecyclerOptions.Builder<Blog3>().setQuery(mDataBase, Blog3.class).build();
        adaptersss = new FirebaseRecyclerAdapter<Blog3, BlogViewHolder3>(optionsss) {
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
                        Intent intent = new Intent(galeri.this, detail_galeri.class);
                        intent.putExtra("galerikey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public BlogViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row2, parent, false);

                return new BlogViewHolder3(view);
            }
        };


        adaptersss.startListening();
        mBlogList.setAdapter(adaptersss);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (adaptersss!=null)
            adaptersss.startListening();
    }

    @Override
    public void onStop() {
        if (adaptersss!=null)
            adaptersss.stopListening();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adaptersss!=null)
            adaptersss.startListening();
    }

}
