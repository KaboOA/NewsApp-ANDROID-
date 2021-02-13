package eg.kaboo.newsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import eg.kaboo.newsapp.R;
import eg.kaboo.newsapp.adapter.NewsAdapter;
import eg.kaboo.newsapp.databinding.ActivityMainBinding;
import eg.kaboo.newsapp.pojo.ArticlesItem;
import eg.kaboo.newsapp.viewmodel.NewsViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NewsAdapter.OnItemClickListener {

    @Inject
    NewsAdapter adapter;

    NewsViewModel viewModel;

    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        callRecycler();
        b.swipeRefreshLayout.setOnRefreshListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        callRecycler();
        b.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, NewsDetails.class);
        intent.putExtra("article", adapter.getArticlesItemByPosition(position).getDescription());
        intent.putExtra("imageUrl", adapter.getArticlesItemByPosition(position).getUrlToImage());
        intent.putExtra("url", adapter.getArticlesItemByPosition(position).getUrl());
        startActivity(intent);
    }

    private void callRecycler() {
        b.recyclerView.setAdapter(adapter);
        viewModel.getArticles(b.progressBar);
        viewModel.getmData().observe(MainActivity.this, o -> adapter.setList(o));
    }

}