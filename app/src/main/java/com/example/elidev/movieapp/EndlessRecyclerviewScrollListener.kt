package com.example.elidev.movieapp

import android.widget.AbsListView

class EndlessRecyclerviewScrollListener(private val loadMoreListener: OnLoadMoreListener) : AbsListView.OnScrollListener {

    private val visibleThreshold = 3
    private var currentPage = 0
    private var currentTotalItems = 0
    private val firstItemPageIndex = 0
    private var loading = false

    override fun onScrollStateChanged(absListView: AbsListView, i: Int) {}

    override fun onScroll(absListView: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

        if (totalItemCount < currentTotalItems) {
            this.currentPage = this.firstItemPageIndex
            this.currentTotalItems = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > currentTotalItems) {
            loading = false
            currentTotalItems = totalItemCount
            currentPage++
        }

        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            loadMoreListener.onLoadMore(currentPage + 1, totalItemCount)
            loading = true
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore(page: Int, totalItemsCount: Int)
    }
}
