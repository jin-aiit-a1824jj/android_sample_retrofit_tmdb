package a1824jj.jp.ac.aiit.tmdb_sampel.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import a1824jj.jp.ac.aiit.tmdb_sampel.databinding.ActivityMovieBinding;
import a1824jj.jp.ac.aiit.tmdb_sampel.model.Movie;
import androidx.appcompat.app.AppCompatActivity;

import a1824jj.jp.ac.aiit.tmdb_sampel.R;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;

    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Intent intent = getIntent();
        if(intent.hasExtra("movie")){
            movie = intent.getParcelableExtra("movie");
            Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_LONG).show();
            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());

        }

    }

}
