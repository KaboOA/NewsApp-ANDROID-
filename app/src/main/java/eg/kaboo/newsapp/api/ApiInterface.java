package eg.kaboo.newsapp.api;

import eg.kaboo.newsapp.pojo.Response;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//for apikey visit https://newsapi.org
    @GET("top-headlines?country=eg&apiKey=YOUR_API_KEY")
    Single<Response> getNews();

}
