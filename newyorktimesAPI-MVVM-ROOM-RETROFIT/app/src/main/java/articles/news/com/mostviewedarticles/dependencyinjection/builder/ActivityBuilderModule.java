package articles.news.com.mostviewedarticles.dependencyinjection.builder;

import articles.news.com.mostviewedarticles.view.activity.MostViewed;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by USER on 4/4/2019.
 */
@Module
public abstract class ActivityBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MostViewed mainActivity();
}
