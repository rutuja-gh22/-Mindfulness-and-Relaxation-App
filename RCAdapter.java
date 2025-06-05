package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.RCViewHolder>{

    Context context;

    public RCAdapter(Context context,ArrayList<RCModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    ArrayList<RCModel> modelArrayList;

    @NonNull
    @Override
    public RCAdapter.RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item,parent,false);
        return new RCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCAdapter.RCViewHolder holder, int position) {
        RCModel rcModel = modelArrayList.get(position);
        holder.rc_title.setText(modelArrayList.get(position).getTitle());
        holder.rc_image.setImageResource(modelArrayList.get(position).getImage());

//        holder.card.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context,DetailActivity.class);
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class RCViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        ImageView rc_image;
        TextView rc_title;
        public RCViewHolder(View itemView){
            super(itemView);

            rc_image = itemView.findViewById(R.id.beg);
            rc_title = itemView.findViewById(R.id.img);

            cardView = itemView.findViewById(R.id.card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        if(position == 0){
                            Intent i = new Intent(context, DetailActivity.class);
                            context.startActivity(i);
                        }else if(position == 1){
                            Intent i = new Intent(context, DetailActivity3.class);
                            context.startActivity(i);
                        }else if(position == 2){
                            Intent i = new Intent(context, DetailActivity2.class);
                            context.startActivity(i);
                        }else if(position == 3){
                            Intent i = new Intent(context, DetailActivity4.class);
                            context.startActivity(i);
                        }else if(position == 4){
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://www.gaiam.com/blogs/discover"));
                            context.startActivity(i);
                        }
                    }
                }
            });
        }
    }
}

