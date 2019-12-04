package ysn.com.demo.tableview.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import ysn.com.demo.tableview.R;
import ysn.com.demo.tableview.bean.Stock;

/**
 * @Author yangsanning
 * @ClassName ContentAdapter
 * @Description 一句话概括作用
 * @Date 2019/11/29
 * @History 2019/11/29 author: description:
 */
public class ContentAdapter extends BaseQuickAdapter<Stock, BaseViewHolder> {

    public ContentAdapter() {
        super(R.layout.item_content);
    }

    @Override
    protected void convert(BaseViewHolder helper, Stock item) {
        int position = helper.getPosition() + 1;
        helper.setText(R.id.content_item_code, item.code + position);
        helper.setText(R.id.content_item_price, item.price + position);
        helper.setText(R.id.content_item_zdf, item.zdf + position);
        helper.setText(R.id.content_item_lb, item.lb + position);
        helper.setText(R.id.content_item_cje, item.cje + position);
    }
}
