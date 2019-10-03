package com.basil.popular_movies;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class ThemoviedbAPI {

    private static final String api_key = BuildConfig.API_KEY;
    private static final String url = "https://api.themoviedb.org/3/movie/";

    public static MovieService movieService = null;

    public static MovieService getService(){

        if (movieService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            movieService = retrofit.create(MovieService.class);

        }


        return movieService;
    }

    public interface MovieService {

        @GET("popular?api_key="+api_key)
        Call<ApiResponse<MovieDetielsModel>> getMoviePopular();

        @GET("top_rated?api_key="+api_key)
        Call<ApiResponse<MovieDetielsModel>> getMovieTopRated();

        @GET("{movie_id}?api_key="+api_key)
        Call<MovieDetielsModel> getMovieDetails(@Path("movie_id") Integer movieId);






    }
}
