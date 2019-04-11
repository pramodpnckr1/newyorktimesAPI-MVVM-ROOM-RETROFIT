package articles.news.com.mostviewedarticles.view.fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import articles.news.com.mostviewedarticles.R;
import articles.news.com.mostviewedarticles.constants.Constants;
import articles.news.com.mostviewedarticles.databinding.FragmentMostViewedArticleDetailBinding;
import articles.news.com.mostviewedarticles.view.base.BaseFragment;
import articles.news.com.mostviewedarticles.viewmodel.MostViewedArticleDetailsViewModel;


public class MostViewedArticleDetailFragment extends BaseFragment<MostViewedArticleDetailsViewModel, FragmentMostViewedArticleDetailBinding> {
    @Override
    protected Class<MostViewedArticleDetailsViewModel> getViewModel() {
        return MostViewedArticleDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_most_viewed_article_detail;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            viewModel.setUrl(args.getString(Constants.BUNDLE_KEY_MOST_VIEWED_ARTICLE_URL));
            viewModel.loadArticleDetails();
        }
        viewModel.getArticleEntityMutableLiveData().observe(this, articleEntity -> {
            if (null != articleEntity && null != args) {
                dataBinding.textTitle.setText(articleEntity.getTitle());
                dataBinding.textContent.setText(articleEntity.getContent());
                dataBinding.textPublishedDate.setText(args.getString(Constants.BUNDLE_KEY_ARTICLE_PUBLISHED_DATE));
                dataBinding.loadingProgress.setVisibility(View.GONE);
            }
        });
        viewModel.getErrorMessageRecieved().observe(this, message -> {
            dataBinding.loadingProgress.setVisibility(View.GONE);
            dataBinding.textContent.setText(getActivity().getString(R.string.networkError));
        });
    }

}
