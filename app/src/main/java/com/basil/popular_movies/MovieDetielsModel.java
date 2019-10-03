
package com.basil.popular_movies;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieDetielsModel implements Parcelable {


    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;


    //private String completePosterImage;

    private String buildFullPosterPath() {
        String FIRST_PART_OF_POSTER_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
        return FIRST_PART_OF_POSTER_URL + posterPath;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeString(this.releaseDate);
        dest.writeString(this.title);
        dest.writeValue(this.voteAverage);
        //   dest.writeString(this.completePosterImage);
    }

    public MovieDetielsModel() {
    }

    protected MovieDetielsModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.releaseDate = in.readString();
        this.title = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieDetielsModel> CREATOR = new Parcelable.Creator<MovieDetielsModel>() {
        @Override
        public MovieDetielsModel createFromParcel(Parcel source) {
            return new MovieDetielsModel(source);
        }

        @Override
        public MovieDetielsModel[] newArray(int size) {
            return new MovieDetielsModel[size];
        }
    };







}
