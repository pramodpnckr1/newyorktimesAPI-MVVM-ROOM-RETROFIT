package articles.news.com.mostviewedarticles.dependencyinjection.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import articles.news.com.mostviewedarticles.data.localdata.MostviewedArticleDB;
import articles.news.com.mostviewedarticles.data.localdata.dataaccessobjects.MostviewedArticlesDao;
import articles.news.com.mostviewedarticles.data.remotedata.ApiConstants;
import articles.news.com.mostviewedarticles.data.remotedata.ApiService;
import articles.news.com.mostviewedarticles.data.remotedata.RequestInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by USER on 4/4/2019.
 */
@Module(includes = ViewModelModule.class)
public class ApplicationModule {
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }
    @Provides
    @Singleton
    ApiService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }
    @Provides
    @Singleton
    MostviewedArticleDB provideArticleDatabase(Application application) {
        return Room.databaseBuilder(application, MostviewedArticleDB.class, "articles.db").build();
    }

    @Provides
    @Singleton
    MostviewedArticlesDao provideArticleDao(MostviewedArticleDB articleDatabase) {
        return articleDatabase.mostviewedArticlesDao();
    }

}
