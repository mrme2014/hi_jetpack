package org.devio.as.proj.hi_jetpack.paging;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;
import java.util.List;

public class PagingViewModel extends AbsViewModel<String> {

    @Override
    public DataSource createDataSource() {
        return new FeedDataSource();
    }

    class FeedDataSource extends PageKeyedDataSource<Integer, String> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, String> callback) {
            loadData(0, params.requestedLoadSize, callback);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, String> callback) {

        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, String> callback) {
            loadData(params.key, params.requestedLoadSize, callback);
        }
    }

    private void loadData(int pageIndex, int pageCount, Object callback) {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            datas.add(String.valueOf(i));
        }
        //将加载回来的数据 通知paging,去渲染列表
        if (callback instanceof PageKeyedDataSource.LoadCallback) {
            ((PageKeyedDataSource.LoadCallback) callback).onResult(datas, pageIndex++);
        } else if (callback instanceof PageKeyedDataSource.LoadInitialCallback) {
            ((PageKeyedDataSource.LoadInitialCallback) callback).onResult(datas, 0, pageIndex++);
        }


        if (pageIndex > 0) {
            //通过BoundaryPageData发送数据 告诉UI层 是否应该主动关闭上拉加载分页的动画
            ((MutableLiveData) getBoundaryPageData()).postValue(datas.size() > 0);
        }
    }

    @SuppressLint("RestrictedApi")
    public void loadAfter(int pageIndex,PageKeyedDataSource.LoadCallback<Integer, String> callback) {
        ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                loadData(pageIndex, config.pageSize, callback);
            }
        });
    }
}