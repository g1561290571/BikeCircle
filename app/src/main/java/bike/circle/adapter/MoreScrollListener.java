package bike.circle.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by MrH on 2017/5/6.
 */

public abstract class MoreScrollListener extends  RecyclerView.OnScrollListener{
    private LinearLayoutManager mLinearLayoutManager;
    private int totalItemCount;
    private int previousTotal = 0;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean loading = true;

    public MoreScrollListener(LinearLayoutManager mLinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager;
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if(loading){
            if(totalItemCount > previousTotal){
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && totalItemCount-visibleItemCount <= firstVisibleItem){

            onLoadMore();
            loading = true;
        }
    }

    public abstract void onLoadMore();
}
