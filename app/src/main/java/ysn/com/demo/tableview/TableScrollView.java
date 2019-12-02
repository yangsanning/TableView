package ysn.com.demo.tableview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @Author yangsanning
 * @ClassName TableScrollView
 * @Description 说明 自定义水平滚动视图，解决ScrollView在API23以下没有滚动监听事件问题
 * @Date 2019/11/29
 * @History 2019/11/29 author: description:
 */
public class TableScrollView extends HorizontalScrollView {

    /**
     * 触摸前的点
     */
    private float downX;

    /**
     * 手势抬起之后的点
     */
    private float upX;

    private OnScrollChangeListener onScrollChangeListener;

    public TableScrollView(Context context) {
        super(context);
    }

    public TableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                upX = ev.getX();
                if (computeHorizontalScrollOffset() == 0 && downX - upX < 0) {
                    //滑动最左边
                    if (onScrollChangeListener != null) {
                        onScrollChangeListener.onScrollFarLeft(this);
                    }
                } else if (computeHorizontalScrollRange() - computeHorizontalScrollOffset()
                    <= computeHorizontalScrollExtent() && downX - upX > 0) {
                    //滑动最右边
                    if (onScrollChangeListener != null) {
                        onScrollChangeListener.onScrollFarRight(this);
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(this, l, t);
        }
    }

    /**
     * 设置监听
     */
    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public interface OnScrollChangeListener {

        /**
         * 滚动监听
         */
        void onScrollChanged(TableScrollView scrollView, int x, int y);

        /**
         * 滑动到最左侧
         */
        void onScrollFarLeft(TableScrollView scrollView);

        /**
         * 滑动到最右侧
         */
        void onScrollFarRight(TableScrollView scrollView);
    }
}
