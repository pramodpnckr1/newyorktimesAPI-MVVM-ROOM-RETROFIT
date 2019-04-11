package articles.news.com.mostviewedarticles.dependencyinjection.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import articles.news.com.mostviewedarticles.viewmodel.MostViewedArticleDetailsViewModel;
import articles.news.com.mostviewedarticles.viewmodel.MostViewedArticleListViewModel;
import articles.news.com.mostviewedarticles.viewmodel.ViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by USER on 4/4/2019.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MostViewedArticleListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsArticleListViewModel(MostViewedArticleListViewModel articleListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MostViewedArticleDetailsViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsArticleDetailsiewModel(MostViewedArticleDetailsViewModel articleDetailsViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
