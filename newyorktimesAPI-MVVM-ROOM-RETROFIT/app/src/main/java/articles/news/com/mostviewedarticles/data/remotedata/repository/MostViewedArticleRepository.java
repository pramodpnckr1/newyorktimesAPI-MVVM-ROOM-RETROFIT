package articles.news.com.mostviewedarticles.data.remotedata.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import javax.inject.Inject;

import articles.news.com.mostviewedarticles.data.localdata.dataaccessobjects.MostviewedArticlesDao;
import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;
import articles.news.com.mostviewedarticles.data.remotedata.ApiService;
import articles.news.com.mostviewedarticles.data.remotedata.NetworkBoundResource;
import articles.news.com.mostviewedarticles.data.remotedata.Resource;
import articles.news.com.mostviewedarticles.data.remotedata.model.MostviewedArticleResponse;
import articles.news.com.mostviewedarticles.view.callback.ResponseListener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/**
 * Created by USER on 4/3/2019.
 */

public class MostViewedArticleRepository {
    private final MostviewedArticlesDao mostviewedArticlesDao;
    private final ApiService apiService;

    @Inject
    public MostViewedArticleRepository(MostviewedArticlesDao mostviewedArticlesDao, ApiService apiService) {
        this.mostviewedArticlesDao = mostviewedArticlesDao;
        this.apiService = apiService;
    }

    public LiveData<Resource<List<MostviewedArticlesEntity>>> loadMostViewedArticles(int farBack) {
        return new NetworkBoundResource<List<MostviewedArticlesEntity>, MostviewedArticleResponse>() {

            @Override
            protected void saveCallResult(MostviewedArticleResponse item) {
                if(null != item)
                    mostviewedArticlesDao.saveMostViewedArticles(item.getMostviewedArticlesList());
            }

            @NonNull
            @Override
            protected LiveData<List<MostviewedArticlesEntity>> loadFromDataBase() {
                return mostviewedArticlesDao.loadMostViewedArticle();
            }

            @NonNull
            @Override
            protected Call<MostviewedArticleResponse> createCall() {
                return apiService.loadPopularArticles(farBack);
            }
        }.getAsLiveData();
    }


    /**
     * This method fetches the HTML comntent from the url and parses it and fills the model
     * @param url url to be fetched
     * @param responseListener callback
     */
    @SuppressLint("CheckResult")
    public void loadArticleDetails(String url, ResponseListener responseListener) {
        MostviewedArticlesEntity articleDetails = new MostviewedArticlesEntity();
        Observable.fromCallable(() -> {
            Document document = Jsoup.connect(url).get();
            articleDetails.setTitle(document.title());
            articleDetails.setContent(document.select("p").text());
            return false;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> responseListener.onSuccess(articleDetails),
                        (error -> responseListener.onFailure(error.getMessage())));

    }

}
