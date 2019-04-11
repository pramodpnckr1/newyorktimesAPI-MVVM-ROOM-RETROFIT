package articles.news.com.mostviewedarticles.view.callback;

import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;

/**
 * Created by USER on 4/4/2019.
 */

public interface ResponseListener {
    void onSuccess(MostviewedArticlesEntity data);
    void onFailure(String message);
}
