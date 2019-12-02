package ysn.com.demo.tableview;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @Author yangsanning
 * @ClassName FirstColumnAdapter
 * @Description 一句话概括作用
 * @Date 2019/12/02
 * @History 2019/12/02 author: description:
 */
public class FirstColumnAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FirstColumnAdapter() {
        super(R.layout.item_first_column);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
