package articles.news.com.mostviewedarticles.data.remotedata;

import articles.news.com.mostviewedarticles.data.remotedata.model.MostviewedArticleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by USER on 4/3/2019.
 */

public interface ApiService {

    @GET("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/{period}")
    Call<MostviewedArticleResponse> loadPopularArticles(@Path("period") int period);
}
