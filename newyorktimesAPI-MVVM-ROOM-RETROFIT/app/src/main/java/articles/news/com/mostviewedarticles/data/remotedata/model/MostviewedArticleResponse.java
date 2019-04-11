package articles.news.com.mostviewedarticles.data.remotedata.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;

/**
 * Created by USER on 4/3/2019.
 */

public class MostviewedArticleResponse {

    @SerializedName("results")
    private List<MostviewedArticlesEntity> mostViewedArticlesEntityList;

    /**
     * This method return the list of most viewed article entities
     *
     * @return List of entities
     */
    public List<MostviewedArticlesEntity> getMostviewedArticlesList() {
        return mostViewedArticlesEntityList;
    }

    /**
     * This method sets the article entities
     * @param popularArticles - articleslist
     */
    @SuppressWarnings("unused")
    public void setPopularArticles(List<MostviewedArticlesEntity> popularArticles) {
        this.mostViewedArticlesEntityList = popularArticles;
    }

}
