package eg.kaboo.newsapp.repo;

import javax.inject.Inject;

import eg.kaboo.newsapp.api.ApiInterface;
import eg.kaboo.newsapp.pojo.Response;
import io.reactivex.rxjava3.core.Single;

public class Repo {

    ApiInterface apiInterface;

    @Inject
    public Repo(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<Response> getArticles() {
        return apiInterface.getNews();
    }

}
