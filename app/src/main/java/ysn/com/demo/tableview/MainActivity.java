package ysn.com.demo.tableview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangsanning
 * @ClassName MainActivity
 * @Description 一句话概括作用
 * @Date 2019/11/29
 * @History 2019/11/29 author: description:
 */
public class MainActivity extends AppCompatActivity  {

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
                    firstColumnAdapter.setNewData(getNewData());
                    contentAdapter.setNewData(getNewData());
                    tableView.refreshSuccess();
                }, 300);
            }

            @Override
            public void onTableLoadMore() {
                tableView.postDelayed(() -> {
                    List<String> data = contentAdapter.getData();
                    int size = data.size();
                    if (size < 60) {
                        for (int i = size; i < (size + 20); i++) {
                            data.add("哈哈");
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

        firstColumnAdapter.setNewData(getNewData());
        contentAdapter.setNewData(getNewData());
    }

    private List<String> getNewData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("哈哈");
        }
        return dataList;
    }
}
