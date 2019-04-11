package articles.news.com.mostviewedarticles.data.localdata.dataaccessobjects;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;

/**
 * Created by USER on 4/3/2019.
 */
@Dao
public interface MostviewedArticlesDao {
    @Query("SELECT * FROM mostviewed_articles")
    LiveData<List<MostviewedArticlesEntity>> loadMostViewedArticle();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMostViewedArticles(List<MostviewedArticlesEntity> mostviewedArticlesEntities);
}
