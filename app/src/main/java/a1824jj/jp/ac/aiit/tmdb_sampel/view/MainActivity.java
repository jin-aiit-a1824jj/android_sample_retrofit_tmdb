package a1824jj.jp.ac.aiit.tmdb_sampel.view;

import a1824jj.jp.ac.aiit.tmdb_sampel.R;
import a1824jj.jp.ac.aiit.tmdb_sampel.adapter.MovieAdapter;
import a1824jj.jp.ac.aiit.tmdb_sampel.databinding.ActivityMainBinding;
import a1824jj.jp.ac.aiit.tmdb_sampel.model.Movie;
import a1824jj.jp.ac.aiit.tmdb_sampel.viewmodel.MainActivityViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private PagedList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    public static final String API_KEY = "API_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("TMDB Popular Movies Today");


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel =  new ViewModelProvider(this).get(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    public void getPopularMovies(){
        mainActivityViewModel.getMoviesPagedList().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> moviesFromLiveData) {
                movies = moviesFromLiveData;
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView() {
        recyclerView = activityMainBinding.rvMovies;
        movieAdapter = new MovieAdapter(this);
        movieAdapter.submitList(movies);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);
    }
}
