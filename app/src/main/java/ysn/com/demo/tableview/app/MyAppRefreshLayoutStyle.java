package ysn.com.demo.tableview.app;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import ysn.com.demo.tableview.utils.DynamicTimeFormatUtil;
import ysn.com.demo.tableview.R;

/**
 * @Author yangsanning
 * @ClassName MyAppRefreshLayoutStyle
 * @Description 用于刷新布局的操作工具类, 如果多个地方使用，请改成单例
 * @Date 2019/12/2
 * @History 2019/12/2 author: description:
 */
public class MyAppRefreshLayoutStyle {
    /**
     * 初始化刷新的风格
     */
    public static void init() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.color_refresh_bg, R.color.color_refresh_text);
            //指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormatUtil("更新于 %s"));
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter classicsFooter = new ClassicsFooter(context).setDrawableSize(20);
            classicsFooter.setBackgroundColor(context.getResources().getColor(R.color.color_refresh_bg));
            classicsFooter.setAccentColorId(R.color.color_refresh_text);
            return classicsFooter;
        });
    }
}
