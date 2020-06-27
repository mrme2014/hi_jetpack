package org.devio.as.proj.hi_jetpack.paging;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.devio.as.proj.hi_jetpack.R;
import org.devio.as.proj.hi_jetpack.databinding.ActivityPagingBinding;

import java.util.List;

public class PagingActivity extends AppCompatActivity {
    private ActivityPagingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_paging);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PagingAdapter pagingAdapter = new PagingAdapter();
        binding.recyclerView.setAdapter(pagingAdapter);

        PagingViewModel viewModel = new ViewModelProvider(this).get(PagingViewModel.class);
        //触发初始化数据加载
        viewModel.getPageData().observe(this, new Observer<PagedList<String>>() {
            @Override
            public void onChanged(PagedList<String> pagedList) {
                pagingAdapter.submitList(pagedList);
            }
        });

        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //invalidate 之后Paging会重新创建一个DataSource 重新调用它的loadInitial方法加载初始化数据
                //详情见：LivePagedListBuilder#compute方法
                viewModel.getDataSource().invalidate();
            }
        });

        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                final PagedList<String> currentList = pagingAdapter.getCurrentList();
                if (currentList == null || currentList.size() <= 0) {
                    finishRefresh(false);
                    return;
                }


                viewModel.loadAfter(1/*真实情况下这里应该是累加的*/, new PageKeyedDataSource.LoadCallback<Integer, String>() {
                    @Override
                    public void onResult(@NonNull List<String> data, @Nullable Integer adjacentPageKey) {
                        PagedList.Config config = currentList.getConfig();
                        if (data != null && data.size() > 0) {
                            //由于当且仅当 paging不再帮我们分页的时候，我们才会接管，才会走到这里。
                            //所以此时， 就不需要ViewModel中创建的DataSource继续工作了，我们自己触发加载分页的逻辑
                            //然后 使用 MutablePageKeyedDataSource 来拼接两次数据源
                            MutablePageKeyedDataSource dataSource = new MutablePageKeyedDataSource();

                            //这里要把列表上已经显示的先添加到dataSource.data中
                            //而后把本次分页回来的数据再添加到dataSource.data中
                            dataSource.data.addAll(currentList);
                            dataSource.data.addAll(data);
                            PagedList pagedList = dataSource.buildNewPagedList(config);
                            pagingAdapter.submitList(pagedList);
                        }
                    }
                });
            }
        });
    }

    private void finishRefresh(boolean success) {
        binding.refreshLayout.finishRefresh(success);
    }
}
