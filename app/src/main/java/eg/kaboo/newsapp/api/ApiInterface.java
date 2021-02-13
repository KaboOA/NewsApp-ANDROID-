package eg.kaboo.newsapp.api;

import eg.kaboo.newsapp.pojo.Response;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines?country=eg&apiKey=8ed4a86ffcc24da2b91f2183831bc3fc")
    Single<Response> getNews();

}
