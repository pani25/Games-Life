package com.apps.gamelife;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BlogViewHolder3 extends RecyclerView.ViewHolder {

    public TextView t1;
    public ImageView i2;
    public View v;

    public BlogViewHolder3(View itemView){
        super(itemView);
        t1 = (TextView)itemView.findViewById(R.id.post_title);
        i2 = (ImageView)itemView.findViewById(R.id.post_image);
        v=itemView;
    }
}
