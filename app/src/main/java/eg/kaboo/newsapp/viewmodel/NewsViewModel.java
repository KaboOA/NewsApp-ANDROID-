package eg.kaboo.newsapp.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import eg.kaboo.newsapp.pojo.ArticlesItem;
import eg.kaboo.newsapp.pojo.Response;
import eg.kaboo.newsapp.repo.Repo;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<ArticlesItem>> mData = new MutableLiveData<>();
    private MutableLiveData<List<ArticlesItem>> mSearchedData = new MutableLiveData<>();
    private static final String TAG = "NewsViewModel";
    private Repo repo;

    @ViewModelInject
    public NewsViewModel(Repo repo) {
        this.repo = repo;
    }

    public MutableLiveData<List<ArticlesItem>> getmData() {
        return mData;
    }

    public void getArticles(ProgressBar bar) {
        repo.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    mData.setValue(o.getArticles());
                    bar.setVisibility(View.GONE);
                }, e -> {
                    Log.d(TAG, "Ahmed getArticles: " + e.getMessage());
                    bar.setVisibility(View.GONE);
                });
    }

}
