package a1824jj.jp.ac.aiit.tmdb_sampel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import a1824jj.jp.ac.aiit.tmdb_sampel.R;
import a1824jj.jp.ac.aiit.tmdb_sampel.databinding.MovieListItemBinding;
import a1824jj.jp.ac.aiit.tmdb_sampel.model.Movie;
import a1824jj.jp.ac.aiit.tmdb_sampel.view.MovieActivity;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private Context context;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemBinding movieListItemBinding
                = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.movie_list_item,
                        parent,
                false);

        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = getItem(position);

        holder.movieListItemBinding.setMovie(movie);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;
            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Movie selectedMovie = getItem(position);

                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("movie", selectedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
