package articles.news.com.mostviewedarticles.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;
import articles.news.com.mostviewedarticles.data.remotedata.repository.MostViewedArticleRepository;
import articles.news.com.mostviewedarticles.utils.SingleLiveEvent;
import articles.news.com.mostviewedarticles.view.callback.ResponseListener;

/**
 * Created by USER on 4/4/2019.
 */

public class MostViewedArticleDetailsViewModel extends ViewModel {
    private String url;

    private MostViewedArticleRepository articleRepository;

    private MutableLiveData<MostviewedArticlesEntity> articleEntityMutableLiveData = new MutableLiveData<>();

    private SingleLiveEvent<Void> errorMessageRecieved = new SingleLiveEvent<>();

    public MutableLiveData<MostviewedArticlesEntity> getArticleEntityMutableLiveData() {
        return articleEntityMutableLiveData;
    }

    public void setArticleEntityMutableLiveData(MutableLiveData<MostviewedArticlesEntity> articleEntityMutableLiveData) {
        this.articleEntityMutableLiveData = articleEntityMutableLiveData;
    }

    public SingleLiveEvent<Void> getErrorMessageRecieved() {
        return errorMessageRecieved;
    }

    public void setErrorMessageRecieved(SingleLiveEvent<Void> errorMessageRecieved) {
        this.errorMessageRecieved = errorMessageRecieved;
    }

    @Inject
    MostViewedArticleDetailsViewModel(MostViewedArticleRepository artRepository) {
        this.articleRepository = artRepository;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void loadArticleDetails(){

        if(null != articleRepository) {
            articleRepository.loadArticleDetails(url, new ResponseListener() {
                @Override
                public void onSuccess(MostviewedArticlesEntity data) {
                    articleEntityMutableLiveData.setValue(data);
                }

                @Override
                public void onFailure(String message) {
                    // Send event to UI to show thw error
                    errorMessageRecieved.call();
                }
            });
        }
    }

}
