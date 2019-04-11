package articles.news.com.mostviewedarticles.data.localdata;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import articles.news.com.mostviewedarticles.data.localdata.dataaccessobjects.MostviewedArticlesDao;
import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;

/**
 * Created by USER on 4/3/2019.
 */
@Database(entities = {MostviewedArticlesEntity.class}, version = 1)
public abstract class MostviewedArticleDB extends RoomDatabase {
    public  abstract MostviewedArticlesDao mostviewedArticlesDao();
}

