package articles.news.com.mostviewedarticles.dependencyinjection.components;

import android.app.Application;

import javax.inject.Singleton;

import articles.news.com.mostviewedarticles.NYTimesApp;
import articles.news.com.mostviewedarticles.dependencyinjection.builder.ActivityBuilderModule;
import articles.news.com.mostviewedarticles.dependencyinjection.module.ApplicationModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Component.Builder;
import dagger.android.AndroidInjectionModule;

/**
 * Created by USER on 4/4/2019.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();

    }
    void inject (NYTimesApp inject);
}
