package articles.news.com.mostviewedarticles;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import articles.news.com.mostviewedarticles.dependencyinjection.components.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by USER on 4/4/2019.
 */

public class NYTimesApp extends Application implements HasActivityInjector {
    private static NYTimesApp sInstance;

    public static NYTimesApp getAppContext() {
        return sInstance;
    }

    private static synchronized void setInstance(NYTimesApp app) {
        sInstance = app;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
