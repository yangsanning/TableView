package ysn.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * VerticalSyncLinearLayout
 * 让 SmartRefreshLayout 认为这是唯一可垂直滚动的控件，
 * 并把 scrollBy/scrollTo 的动作分发给两个 RecyclerView
 * @author : yangsn
 * @date : 2025/11/20
 */
public class VerticalSyncLinearLayout extends LinearLayout implements NestedScrollingChild2 {

    private RecyclerView leftRv;
    private RecyclerView rightRv;

    private int lastScrollY = 0;
    private final NestedScrollingChildHelper childHelper;

    public VerticalSyncLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public VerticalSyncLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalSyncLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        childHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public void setRecyclerViews(RecyclerView left, RecyclerView right) {
        this.leftRv = left;
        this.rightRv = right;

        // 禁用 RecyclerView 的 nested scrolling，避免 SmartRefreshLayout 识别到它们
        if (leftRv != null) leftRv.setNestedScrollingEnabled(false);
        if (rightRv != null) rightRv.setNestedScrollingEnabled(false);
    }

    /* ---------------- 关键：SmartRefreshLayout 会调用 scrollBy/scrollTo ---------------- */
    @Override
    public void scrollBy(int x, int y) {
        if (leftRv != null) {
            leftRv.scrollBy(0, y);
        }
        if (rightRv != null) {
            rightRv.scrollBy(0, y);
        }
        lastScrollY += y;
    }

    @Override
    public void scrollTo(int x, int y) {
        int dy = y - lastScrollY;
        if (leftRv != null) {
            leftRv.scrollBy(0, dy);
        }
        if (rightRv != null) {
            rightRv.scrollBy(0, dy);
        }
        lastScrollY = y;
    }

    /* ---------------- 关键：告诉 SmartRefreshLayout 我可以垂直滚动 ---------------- */
    @Override
    public boolean canScrollVertically(int direction) {
        // 以右侧 RecyclerView 为参考
        return rightRv != null && rightRv.canScrollVertically(direction);
    }

    /* ---------------- 实现 NestedScrollingChild2 ---------------- */
    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        childHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return childHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return childHelper.startNestedScroll(axes, type);
    }

    @Override
    public void stopNestedScroll(int type) {
        childHelper.stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return childHelper.hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed,
                                        int[] offsetInWindow, int type) {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }
}
