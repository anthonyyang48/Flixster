package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.Target;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.modules.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    int radius = 10;
    int margin = 10;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("MovieAdapter", "onCreateViewHolder");

        // Returns a view
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("MovieAdapter", "onBindViewHolder" + position);

        // Get the movie at the position
        Movie movie = movies.get(position);

        // Bind the movie data into the View Holder
        holder.bind(movie);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // ViewHolder is a representation of the row in the Recycler View
    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(radius, margin)).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(ivPoster);

            // 1) Register the click listener on the whole container
            container.setOnClickListener(new View.OnClickListener() {

                @Override
                // 2) Navigate to a new activity when clicked on
                public void onClick(View v) {

                    Intent intent = new Intent(context, DetailActivity.class);

                    // Android Studio knows how to break the movie object down into a parcelable and reconstruct it
                    intent.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(intent);

                }

            });

        }

    }

}