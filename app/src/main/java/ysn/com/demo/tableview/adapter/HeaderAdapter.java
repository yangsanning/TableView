package ysn.com.demo.tableview.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import ysn.com.demo.tableview.R;

/**
 * @Author yangsanning
 * @ClassName HeaderAdapter
 * @Description 一句话概括作用
 * @Date 2019/11/29
 * @History 2019/11/29 author: description:
 */
public class HeaderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HeaderAdapter() {
        super(R.layout.item_table_head);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getPosition() + 1;
        helper.setText(R.id.textView, item);
    }
}
