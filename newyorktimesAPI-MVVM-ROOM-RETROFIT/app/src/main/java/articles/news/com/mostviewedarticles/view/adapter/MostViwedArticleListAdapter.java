package articles.news.com.mostviewedarticles.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import articles.news.com.mostviewedarticles.data.localdata.entity.MostviewedArticlesEntity;
import articles.news.com.mostviewedarticles.databinding.ItemArticleListBinding;
import articles.news.com.mostviewedarticles.view.base.BaseAdapter;
import articles.news.com.mostviewedarticles.view.callback.MostviewedArticleListCallback;
import io.reactivex.annotations.NonNull;

/**
 * Created by USER on 4/4/2019.
 */

public class MostViwedArticleListAdapter extends BaseAdapter<MostViwedArticleListAdapter.ArticleViewHolder, MostviewedArticlesEntity>
        implements Filterable {

    private List<MostviewedArticlesEntity> articlesEntityList;

    private List<MostviewedArticlesEntity> filterdArticlesEntityList;

    private final MostviewedArticleListCallback listCallback;

    public MostViwedArticleListAdapter(@NonNull MostviewedArticleListCallback listCallback) {
        this.listCallback = listCallback;
        articlesEntityList = new ArrayList<>();
        filterdArticlesEntityList = new ArrayList<>();

    }

    @Override
    public void setData(List<MostviewedArticlesEntity> entityList) {
        this.articlesEntityList = entityList;
        this.filterdArticlesEntityList = entityList;
        notifyDataSetChanged();
    }

    @android.support.annotation.NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@android.support.annotation.NonNull ViewGroup viewGroup, int i) {
        return ArticleViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, listCallback);
    }

    @Override
    public void onBindViewHolder(@android.support.annotation.NonNull ArticleViewHolder viewHolder, int i) {
        viewHolder.onBind(filterdArticlesEntityList.get(i));
    }

    @Override
    public int getItemCount() {
        return filterdArticlesEntityList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterdArticlesEntityList = articlesEntityList;
                } else {
                    List<MostviewedArticlesEntity> filteredList = new ArrayList<>();
                    for (MostviewedArticlesEntity row : articlesEntityList) {

                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())
                                || row.getAuthorsName().toLowerCase().contains(charString.toLowerCase())
                                || row.getPublishedDate().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filterdArticlesEntityList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterdArticlesEntityList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterdArticlesEntityList = (ArrayList<MostviewedArticlesEntity>) filterResults.values;
                notifyDataSetChanged();
            }

        };

    }
    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private static ArticleViewHolder create(LayoutInflater inflater, ViewGroup parent, MostviewedArticleListCallback callback) {
            ItemArticleListBinding itemMovieListBinding = ItemArticleListBinding.inflate(inflater, parent, false);
            return new ArticleViewHolder(itemMovieListBinding, callback);
        }

        final ItemArticleListBinding binding;

        private ArticleViewHolder(ItemArticleListBinding binding, MostviewedArticleListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onArticleClicked(binding.getMostviewedarticle()));
        }

        private void onBind(MostviewedArticlesEntity articleEntity) {
            binding.setMostviewedarticle(articleEntity);
            binding.executePendingBindings();
        }
    }

}
