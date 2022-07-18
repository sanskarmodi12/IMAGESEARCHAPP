package com.example.gridview;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private GridLayoutManager layoutManager;
    private  String searchedName;

    public PaginationScrollListener(GridLayoutManager layoutManager, String searchedName) {
        this.layoutManager = layoutManager;
//        this.searchedName=searchedName;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();


            if(!isLoading())
            if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }

    }

    protected abstract void loadMoreItems();



    public abstract boolean isLoading();




}