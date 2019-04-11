package articles.news.com.mostviewedarticles.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import articles.news.com.mostviewedarticles.R;
import articles.news.com.mostviewedarticles.databinding.ActivityMostviewedBinding;
import articles.news.com.mostviewedarticles.utils.FragmentUtils;
import articles.news.com.mostviewedarticles.view.base.BaseActivity;
import articles.news.com.mostviewedarticles.view.fragements.MostViewedArticlesListFragment;

import static articles.news.com.mostviewedarticles.utils.FragmentUtils.TRANSITION_NONE;

public class MostViewed extends BaseActivity<ActivityMostviewedBinding> {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_mostviewed;

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, MostViewedArticlesListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
