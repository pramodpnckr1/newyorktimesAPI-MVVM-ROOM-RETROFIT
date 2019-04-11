package articles.news.com.mostviewedarticles.view.callback;

import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;

/**
 * Created by USER on 4/4/2019.
 */

public interface MostviewedArticleListCallback {
    void onArticleClicked(MostviewedArticlesEntity mostviewedArticlesEntity);
}
