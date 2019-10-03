package com.basil.popular_movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basil.popular_movies.ThemoviedbAPI.*;

public class MainActivity extends AppCompatActivity {

    List<MovieDetielsModel> listMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isOnline()) {
            getData("Popular");

        }else {
            displayErrorMEssageWhenNoInternet();
        }
    }

    public void displayErrorMEssageWhenNoInternet(){
        Context context = getApplicationContext();
        CharSequence text = "Check your internet and try again!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (isOnline()) {
            switch (item.getItemId()) {
                case R.id.action_sortByPopular:

                    getData("Popular");

                    return true;

                case R.id.sortByRated:

                    getData("Rated");

                    return true;
            }
        }else{
            displayErrorMEssageWhenNoInternet();
        }

        return super.onOptionsItemSelected(item);
    }


    private void updateUI() {

        RecyclerView movieRecyclerView = (RecyclerView) findViewById(R.id.movie_recyclerviet_id);

        RecyclerViewMovieAdapter recyclerViewMovieAdapter = new RecyclerViewMovieAdapter(this, listMovie);

        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        movieRecyclerView.setAdapter(recyclerViewMovieAdapter);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    private void getData(String type){

        Call<ApiResponse<MovieDetielsModel>> movieList = null;

        if (type.equals("Popular")) {
            movieList = getService().getMoviePopular();
        }else if (type.equals("Rated")) {
            movieList = getService().getMovieTopRated();
        }

        movieList.enqueue(new Callback<ApiResponse<MovieDetielsModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<MovieDetielsModel>> call, Response<ApiResponse<MovieDetielsModel>> response) {
                List<MovieDetielsModel> list = response.body().results;

      listMovie = list;
    updateUI();

            }

            @Override
            public void onFailure(Call<ApiResponse<MovieDetielsModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail to get the data !",Toast.LENGTH_LONG).show();

            }
        });


    }
}
