package ysn.com.demo.tableview;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @Author yangsanning
 * @ClassName ContentAdapter
 * @Description 一句话概括作用
 * @Date 2019/11/29
 * @History 2019/11/29 author: description:
 */
public class ContentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ContentAdapter() {
        super(R.layout.item_content);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
