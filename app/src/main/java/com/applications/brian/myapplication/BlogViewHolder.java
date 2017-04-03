package com.applications.brian.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class BlogViewHolder extends FirebaseRecyclerAdapter<Blog,BlogViewHolder.BlogView> {

   Context cxt;


    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public BlogViewHolder(Class<Blog> modelClass, int modelLayout, Query ref, Context context) {

        super(modelClass, modelLayout, BlogViewHolder.BlogView.class, ref);
        cxt=context;
    }

    @Override
    public BlogView onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view= (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row,parent,false);

        return new BlogView(view);
    }


    @Override
    protected void populateViewHolder(BlogView viewHolder, Blog model, int position) {
//        MainActivity.startUpDialog.dismiss();
           viewHolder.setImage(cxt,model.getImage());
        viewHolder.setContent(model.getContent());
        viewHolder.setTitle(model.getTitle());
   final int id= viewHolder.itemView.getId();
          viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Toast.makeText(cxt," fuck you man "+id, Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(cxt,MainDisplayActivity.class);
                  cxt.startActivity(intent);
              }
          });

    }


     class BlogView extends RecyclerView.ViewHolder {

        View mmView;
        TextView textView;
        ImageView imageView;
        TextView textView1;
        TextView textView2;
         BlogView(View itemView) {
            super(itemView);
             mmView=itemView;

        }
        public void setTitle(String title) {


            textView = (TextView) itemView.findViewById(R.id.post_title);
          if (textView != null) {

          textView.setText(title);
            }

        }

        public void setImage(Context context, String image) {

            imageView = (ImageView) itemView.findViewById(R.id.post_image);
            imageView.setAdjustViewBounds(false);
            imageView.setMaxHeight(300);
            imageView.setMinimumHeight(100);
            if (imageView != null) {

                Picasso.with(context).load(image).into(imageView);
            }


        }

        public void setUserName(String userName) {
           DatabaseReference databaseReference= FirebaseDatabase.getInstance().
                   getReference().child("Users").child("name");
            textView1 = (TextView) itemView.findViewById(R.id.username);
            if (textView1 != null) {
                textView1.setText(userName);
            }


        }

        public void setContent(String content) {

            textView2 = (TextView) itemView.findViewById(R.id.post_content);
            if (textView2 != null) {

                textView2.setText(content);
            }


        }}




    }


