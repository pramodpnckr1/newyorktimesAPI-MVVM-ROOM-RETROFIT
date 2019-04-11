package articles.news.com.mostviewedarticles.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;
import articles.news.com.mostviewedarticles.data.remotedata.Resource;
import articles.news.com.mostviewedarticles.data.remotedata.repository.MostViewedArticleRepository;

/**
 * Created by USER on 4/4/2019.
 */

public class MostViewedArticleListViewModel extends ViewModel {
    private final LiveData<Resource<List<MostviewedArticlesEntity>>> mostViewedArticle;

    @Inject
    public MostViewedArticleListViewModel(MostViewedArticleRepository mostViewedArticleRepository) {
        mostViewedArticle = mostViewedArticleRepository.loadMostViewedArticles(7); //seven is the far back
    }

    public LiveData<Resource<List<MostviewedArticlesEntity>>> getMostViewedArticles() {
        return mostViewedArticle;
    }
}
