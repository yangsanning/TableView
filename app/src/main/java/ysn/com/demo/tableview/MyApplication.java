package ysn.com.demo.tableview;

import android.app.Application;

/**
 * @Author yangsanning
 * @ClassName MyApplication
 * @Description 一句话概括作用
 * @Date 2019/12/2
 * @History 2019/12/2 author: description:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyAppRefreshLayoutStyle.init();
    }
}
