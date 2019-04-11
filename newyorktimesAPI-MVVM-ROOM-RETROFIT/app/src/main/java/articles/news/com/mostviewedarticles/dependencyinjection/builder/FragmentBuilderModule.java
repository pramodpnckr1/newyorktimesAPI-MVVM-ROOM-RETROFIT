package articles.news.com.mostviewedarticles.dependencyinjection.builder;

import articles.news.com.mostviewedarticles.view.fragements.MostViewedArticleDetailFragment;
import articles.news.com.mostviewedarticles.view.fragements.MostViewedArticlesListFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by USER on 4/4/2019.
 */
@Module
public abstract class FragmentBuilderModule {
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MostViewedArticlesListFragment contributeArticleListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MostViewedArticleDetailFragment contributeArticleDetailFragment();
}
