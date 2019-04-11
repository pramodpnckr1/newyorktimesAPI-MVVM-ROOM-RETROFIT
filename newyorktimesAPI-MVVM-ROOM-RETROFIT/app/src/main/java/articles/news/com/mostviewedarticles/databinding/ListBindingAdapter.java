package articles.news.com.mostviewedarticles.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import articles.news.com.mostviewedarticles.data.remotedata.Resource;
import articles.news.com.mostviewedarticles.view.base.BaseAdapter;

/**
 * Created by USER on 4/4/2019.
 */

public class ListBindingAdapter {
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }

}
