package ysn.com.demo.tableview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ysn.com.demo.tableview.adapter.ContentAdapter;
import ysn.com.demo.tableview.adapter.FirstColumnAdapter;
import ysn.com.demo.tableview.bean.Stock;
import ysn.com.view.TableView;

/**
 * @Author yangsanning
 * @ClassName MainActivity
 * @Description 一句话概括作用
 * @Date 2019/11/29
 * @History 2019/11/29 author: description:
 */
public class MainActivity extends AppCompatActivity  {

    private List<Stock> dataList = new ArrayList<>();

    private FirstColumnAdapter firstColumnAdapter;
    private ContentAdapter contentAdapter;

    private TableView tableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableView = findViewById(R.id.main_activity_table_view);

        firstColumnAdapter = new FirstColumnAdapter();
        contentAdapter = new ContentAdapter();

        tableView.setOnTableRefreshAndLoadMoreListener(new TableView.OnTableRefreshAndLoadMoreListener() {
            @Override
            public void onTableRefresh() {
                tableView.postDelayed(() -> {
                    setNewData();
                    tableView.refreshSuccess();
                }, 300);
            }

            @Override
            public void onTableLoadMore() {
                tableView.postDelayed(() -> {
                    List<Stock> data = contentAdapter.getData();
                    int size = data.size();
                    if (size < 60) {
                        for (int i = size; i < (size + 20); i++) {
                            data.add(new Stock());
                        }
                        tableView.loadMoreSuccess();
                        firstColumnAdapter.setNewData(data);
                        contentAdapter.setNewData(data);
                    } else {
                        tableView.loadMoreSuccessWithNoMoreData();
                    }
                }, 300);
            }
        })
            .setFirstColumnAdapter(firstColumnAdapter)
            .setContentAdapter(contentAdapter);

        // 禁止ScrollView的回弹
        tableView.getHeadScrollView().setOverScrollMode(View.OVER_SCROLL_NEVER);
        tableView.getContentScrollView().setOverScrollMode(View.OVER_SCROLL_NEVER);

        // 获取自定义左上角布局的TextView, 并设置值
        ((TextView) tableView.getLeftTopHeadView().findViewById(R.id.left_top_head_table_layout_text)).setText("名称");

        setNewData();
    }

    private void setNewData() {
        dataList.clear();
        for (int i = 0; i < 20; i++) {
            dataList.add(new Stock());
        }
        firstColumnAdapter.setNewData(dataList);
        contentAdapter.setNewData(dataList);
    }
}
