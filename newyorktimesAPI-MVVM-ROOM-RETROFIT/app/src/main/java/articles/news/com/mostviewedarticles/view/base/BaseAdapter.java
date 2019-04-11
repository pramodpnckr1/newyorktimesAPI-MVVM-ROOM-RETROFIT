package articles.news.com.mostviewedarticles.view.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by USER on 4/4/2019.
 */

public abstract class BaseAdapter <T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T> {
    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull T t, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public abstract void setData(List<D> data);
}
