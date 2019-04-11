package articles.news.com.mostviewedarticles.view.fragements;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import articles.news.com.mostviewedarticles.R;
import articles.news.com.mostviewedarticles.constants.Constants;
import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;
import articles.news.com.mostviewedarticles.data.remotedata.Status;
import articles.news.com.mostviewedarticles.databinding.FragmentMostViewedArticlesListBinding;
import articles.news.com.mostviewedarticles.utils.FragmentUtils;
import articles.news.com.mostviewedarticles.view.adapter.MostViwedArticleListAdapter;
import articles.news.com.mostviewedarticles.view.base.BaseFragment;
import articles.news.com.mostviewedarticles.view.callback.MostviewedArticleListCallback;
import articles.news.com.mostviewedarticles.viewmodel.MostViewedArticleListViewModel;


public class MostViewedArticlesListFragment extends BaseFragment<MostViewedArticleListViewModel,
        FragmentMostViewedArticlesListBinding> implements MostviewedArticleListCallback {

    public static MostViewedArticlesListFragment newInstance() {
        Bundle args = new Bundle();
        MostViewedArticlesListFragment fragment = new MostViewedArticlesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onArticleClicked(MostviewedArticlesEntity articleEntity) {
        if (null != getActivity()) {
            Bundle args = new Bundle();
            args.putString(Constants.BUNDLE_KEY_MOST_VIEWED_ARTICLE_URL, articleEntity.getUrl());
            args.putString(Constants.BUNDLE_KEY_ARTICLE_PUBLISHED_DATE, articleEntity.getPublishedDate());
            MostViewedArticleDetailFragment detailFragment = new MostViewedArticleDetailFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }


    }

    @Override
    public Class<MostViewedArticleListViewModel> getViewModel() {
        return MostViewedArticleListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_most_viewed_articles_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new MostViwedArticleListAdapter(this));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getMostViewedArticles().observe(this, listResource -> {
            if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                dataBinding.loadingProgress.setVisibility(View.GONE);
            }
            dataBinding.setResource(listResource);

            // If the cached data is already showing then no need to show the error
            if (null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0) {
                dataBinding.errorText.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (null == getActivity())
            return;
        SearchView searchView;
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
                SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (dataBinding.recyclerView.getAdapter() != null)
                    ((MostViwedArticleListAdapter) dataBinding.recyclerView.getAdapter()).getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (dataBinding.recyclerView.getAdapter() != null)
                    ((MostViwedArticleListAdapter) dataBinding.recyclerView.getAdapter()).getFilter().filter(s);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


