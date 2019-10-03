package com.basil.popular_movies;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMovieAdapter extends RecyclerView.Adapter<RecyclerViewMovieAdapter.MovieViewHolder> {

    private Context mContect;
    private List<MovieDetielsModel> mData;

    public RecyclerViewMovieAdapter(Context mContect, List<MovieDetielsModel> mData) {
        this.mContect = mContect;
        this.mData = mData;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup , int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContect);
        int layoutIdForListItem = R.layout.cardview_movie_item;


        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MovieViewHolder holder, final int position) {


        holder.tv_movie_title.setText(mData.get(position).getTitle());

       String urlImage = urlPostPathToUrl(mData.get(position).getPosterPath(),"w185");

       Picasso.get().load(urlImage).into(holder.tv_movie_poster);


        holder.posterCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Integer movieId = mData.get(position).getId();

                Intent movieDetilsIntent = new Intent(mContect,MovieDetails.class);

                String movieIdKey = mContect.getString(R.string.movieId);
                movieDetilsIntent.putExtra(movieIdKey,movieId);

                mContect.startActivity(movieDetilsIntent);

            }
        });

    }

    static public String urlPostPathToUrl(String posterPath,String imageSize){

        String url = "http://image.tmdb.org/t/p/"+imageSize+"/" + posterPath;

       return url;

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();

    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tv_movie_title;
        ImageView tv_movie_poster;
        CardView posterCardView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            tv_movie_title = (TextView) itemView.findViewById(R.id.movie_title_id);
            tv_movie_poster = (ImageView) itemView.findViewById(R.id.movie_image_id);
            posterCardView = (CardView) itemView.findViewById(R.id.poster_cardview_id);

        }
    }
}
