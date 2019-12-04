package ysn.com.demo.tableview.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import ysn.com.demo.tableview.R;
import ysn.com.demo.tableview.bean.Stock;

/**
 * @Author yangsanning
 * @ClassName FirstColumnAdapter
 * @Description 一句话概括作用
 * @Date 2019/12/02
 * @History 2019/12/02 author: description:
 */
public class FirstColumnAdapter extends BaseQuickAdapter<Stock, BaseViewHolder> {

    public FirstColumnAdapter() {
        super(R.layout.item_first_column);
    }

    @Override
    protected void convert(BaseViewHolder helper, Stock item) {
        helper.setText(R.id.first_column_item_name, item.name + (helper.getPosition() + 1));
    }
}
