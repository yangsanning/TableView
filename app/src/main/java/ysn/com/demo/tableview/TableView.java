package ysn.com.demo.tableview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * @Author yangsanning
 * @ClassName TableView
 * @Description 一句话概括作用
 * @Date 2019/12/2
 * @History 2019/12/2 author: description:
 */
public class TableView extends LinearLayout implements TableScrollView.OnScrollChangeListener {

    private Context context;

    private OnTableRefreshAndLoadMoreListener onTableRefreshAndLoadMoreListener;

    private TableScrollView headScrollView, contentScrollView;
    private SmartRefreshLayout smartRefreshLayout;
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
        headScrollView = findViewById(R.id.table_view_head_scroll_view);
        headScrollView.setOnScrollChangeListener(this);
        contentScrollView = findViewById(R.id.table_view_content_scroll_view);
        contentScrollView.setOnScrollChangeListener(this);
    }

    private void initRefreshLayout() {
        smartRefreshLayout = findViewById(R.id.table_view_refresh_layout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (onTableRefreshAndLoadMoreListener != null) {
                    onTableRefreshAndLoadMoreListener.onTableLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (onTableRefreshAndLoadMoreListener != null) {
                    onTableRefreshAndLoadMoreListener.onTableRefresh();
                }
            }
        });
    }

    private void initFirstColumnRecyclerView() {
        firstColumnRecyclerView = findViewById(R.id.table_view_first_column);
        firstColumnRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        firstColumnRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    contentRecyclerView.scrollBy(dx, dy);
                }
            }
        });
    }

    private void initContentRecyclerView() {
        contentRecyclerView = findViewById(R.id.table_view_content);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        contentRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView,dx,dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    firstColumnRecyclerView.scrollBy(dx, dy);
                }
            }
        });
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

    /**
     * 获取头部的横向 ScrollView
     */
    public TableScrollView getHeadScrollView() {
        return headScrollView;
    }

    /**
     * 获取内容的横向 ScrollView
     */
    public TableScrollView getContentScrollView() {
        return contentScrollView;
    }

    /**
     * 获取刷新控件(https://github.com/scwang90/SmartRefreshLayout)
     */
    public SmartRefreshLayout getSmartRefreshLayout() {
        return smartRefreshLayout;
    }

    /**
     * 获取首列 RecyclerView
     */
    public RecyclerView getFirstColumnRecyclerView() {
        return firstColumnRecyclerView;
    }

    /**
     * 获取内容 RecyclerView
     */
    public RecyclerView getContentRecyclerView() {
        return contentRecyclerView;
    }

    /**
     * 设置首列 RecyclerView.Adapter
     */
    public TableView setFirstColumnAdapter(RecyclerView.Adapter firstColumnAdapter) {
        firstColumnRecyclerView.setAdapter(firstColumnAdapter);
        return this;
    }

    /**
     * 设置内容 RecyclerView.Adapter
     */
    public TableView setContentAdapter(RecyclerView.Adapter contentAdapter) {
        contentRecyclerView.setAdapter(contentAdapter);
        return this;
    }

    /**
     * 刷新成功
     */
    public void refreshSuccess() {
        smartRefreshLayout.finishRefresh();
    }

    /**
     * 刷新失败
     */
    public void refreshFailure() {
        smartRefreshLayout.finishRefresh(false);
    }

    /**
     * 加载更多成功
     */
    public void loadMoreSuccess() {
        smartRefreshLayout.finishLoadMore();
    }

    /**
     * 加载更多成功且没有更多数据
     */
    public void loadMoreSuccessWithNoMoreData() {
        smartRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    /**
     * 加载更多失败
     */
    public void loadMoreFailure() {
        smartRefreshLayout.finishLoadMore(false);
    }

    /**
     * 下拉刷新以及上拉加载更多监听
     */
    public TableView setOnTableRefreshAndLoadMoreListener(OnTableRefreshAndLoadMoreListener onTableRefreshAndLoadMoreListener) {
        this.onTableRefreshAndLoadMoreListener = onTableRefreshAndLoadMoreListener;
        return this;
    }

    /**
     * 下拉刷新以及上拉加载更多监听器
     */
    public interface OnTableRefreshAndLoadMoreListener {

        /**
         * 下拉刷新
         */
        void onTableRefresh();

        /**
         * 上拉加载更多
         */
        void onTableLoadMore();
    }
}
