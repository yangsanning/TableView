package ysn.com.demo.tableview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangsanning
 * @ClassName TableView
 * @Description 一句话概括作用
 * @Date 2019/12/2
 * @History 2019/12/2 author: description:
 */
public class TableView extends LinearLayout implements TableScrollView.OnScrollChangeListener {

    private Context context;

    private FirstColumnAdapter firstColumnAdapter;
    private ContentAdapter contentAdapter;
    private LinearLayoutManager contentLayoutManager;

    TableScrollView headScrollView, contentScrollView;
    private RecyclerView firstColumnRecyclerView, contentRecyclerView;

    public TableView(Context context) {
        this(context, null);
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LinearLayout.inflate(context, R.layout.item_table_view, this);

        initScrollView();
        initRefreshLayout();
        initFirstColumnRecyclerView();
        initContentRecyclerView();
    }

    private void initScrollView() {
        headScrollView = findViewById(R.id.main_activity_head_scroll_view);
        headScrollView.setOnScrollChangeListener(this);
        contentScrollView = findViewById(R.id.main_activity_content_scroll_view);
        contentScrollView.setOnScrollChangeListener(this);
    }

    private void initRefreshLayout() {
        SmartRefreshLayout smartRefreshLayout = findViewById(R.id.main_activity_refresh_layout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.postDelayed(() -> {
                    List<String> data = contentAdapter.getData();
                    int size = data.size();
                    if (size < 60) {
                        for (int i = size; i < (size + 20); i++) {
                            data.add("哈哈");
                        }
                        refreshLayout.finishLoadMore();
                        firstColumnAdapter.setNewData(data);
                        contentAdapter.setNewData(data);
                    } else {
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }, 300);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.postDelayed(() -> {
                    firstColumnAdapter.setNewData(getNewData());
                    contentAdapter.setNewData(getNewData());
                    refreshLayout.finishRefresh();
                }, 300);
            }
        });
    }

    private void initFirstColumnRecyclerView() {
        firstColumnRecyclerView = findViewById(R.id.main_activity_first_column);
        firstColumnRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        firstColumnAdapter = new FirstColumnAdapter();
        firstColumnAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("test", contentLayoutManager.findFirstVisibleItemPosition() + " - " +
                    contentLayoutManager.findLastVisibleItemPosition());
            }
        });
        firstColumnRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    contentRecyclerView.scrollBy(dx, dy);
                }
            }
        });
        firstColumnRecyclerView.setAdapter(firstColumnAdapter);
        firstColumnAdapter.setNewData(getNewData());
    }

    private void initContentRecyclerView() {
        contentRecyclerView = findViewById(R.id.main_activity_content);
        contentLayoutManager = new LinearLayoutManager(context);
        contentLayoutManager.setOrientation(RecyclerView.VERTICAL);
        contentRecyclerView.setLayoutManager(contentLayoutManager);
        contentAdapter = new ContentAdapter();
        contentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("test", contentLayoutManager.findFirstVisibleItemPosition() + " - " +
                    contentLayoutManager.findLastVisibleItemPosition());
            }
        });
        contentRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView,dx,dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    firstColumnRecyclerView.scrollBy(dx, dy);
                }
            }
        });
        contentRecyclerView.setAdapter(contentAdapter);
        contentAdapter.setNewData(getNewData());
    }

    private List<String> getNewData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("哈哈");
        }
        return dataList;
    }

    @Override
    public void onScrollChanged(TableScrollView scrollView, int x, int y) {
        if (scrollView.equals(headScrollView)) {
            contentScrollView.scrollTo(x, y);
        } else {
            headScrollView.scrollTo(x, y);
        }
    }

    @Override
    public void onScrollFarLeft(TableScrollView scrollView) {

    }

    @Override
    public void onScrollFarRight(TableScrollView scrollView) {

    }
}
