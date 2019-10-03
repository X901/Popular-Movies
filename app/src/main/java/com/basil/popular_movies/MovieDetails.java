package com.basil.popular_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basil.popular_movies.RecyclerViewMovieAdapter.urlPostPathToUrl;
import static com.basil.popular_movies.ThemoviedbAPI.getService;

public class MovieDetails extends AppCompatActivity {

Integer movieId = null;
MovieDetielsModel movieDetielsObject = null;


    TextView tv_movie_title;
    TextView tv_movie_release_date;
    TextView tv_movie_vote_average;
    TextView tv_movie_overview;
    ImageView tv_movie_poster;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Context context = getApplicationContext();

        Intent movieIntent = getIntent();
        String movieIdKey = context.getString(R.string.movieId);

         movieId = movieIntent.getExtras().getInt(movieIdKey);

        getMovieDetailData();

    }

    private void updateUI(MovieDetielsModel movieDetielsObject) {

        tv_movie_title = (TextView) findViewById(R.id.movie_title_id);
        tv_movie_release_date = (TextView) findViewById(R.id.release_date_id);
        tv_movie_vote_average = (TextView) findViewById(R.id.vote_average_id);
        tv_movie_overview = (TextView) findViewById(R.id.overview_id);
        tv_movie_poster = (ImageView) findViewById(R.id.poster_image_id);

        tv_movie_title.setText(movieDetielsObject.getTitle());
        tv_movie_release_date.setText(movieDetielsObject.getReleaseDate());
        tv_movie_vote_average.setText(Double.toString(movieDetielsObject.getVoteAverage()));
        tv_movie_overview.setText(movieDetielsObject.getOverview());

        String urlImage = urlPostPathToUrl(movieDetielsObject.getPosterPath(),"w342");
        Picasso.get().load(urlImage).into(tv_movie_poster);

    }




    private void getMovieDetailData(){

        Call<MovieDetielsModel> movieDetiels = getService().getMovieDetails(movieId);


        movieDetiels.enqueue(new Callback<MovieDetielsModel>() {
            @Override
            public void onResponse(Call<MovieDetielsModel> call, Response<MovieDetielsModel> response) {
                MovieDetielsModel movieDetiels = response.body();

               movieDetielsObject = movieDetiels;
                updateUI(movieDetielsObject);
            }

            @Override
            public void onFailure(Call<MovieDetielsModel> call, Throwable t) {
                Toast.makeText(MovieDetails.this,"Fail to get the data !",Toast.LENGTH_LONG).show();

            }
        });


    }
}
