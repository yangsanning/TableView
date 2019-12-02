package ysn.com.demo.tableview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangsanning
 * @ClassName MainActivity
 * @Description 一句话概括作用
 * @Date 2019/11/29
 * @History 2019/11/29 author: description:
 */
public class MainActivity extends AppCompatActivity {

    LinearLayoutManager contentLayoutManager;

    RecyclerView contentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initContentRecyclerView();
    }

    private void initContentRecyclerView() {
        contentRecyclerView = findViewById(R.id.main_activity_content);
        contentLayoutManager = new LinearLayoutManager(this);
        contentLayoutManager.setOrientation(RecyclerView.VERTICAL);
        contentRecyclerView.setLayoutManager(contentLayoutManager);
        ContentAdapter contentAdapter = new ContentAdapter();
        contentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("test", contentLayoutManager.findFirstVisibleItemPosition() + " - " +
                    contentLayoutManager.findLastVisibleItemPosition());
            }
        });
        contentRecyclerView.setAdapter(contentAdapter);
        contentAdapter.setNewData(getNewData());
    }

    private List<String> getNewData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            dataList.add("哈哈");
        }
        return dataList;
    }
}
