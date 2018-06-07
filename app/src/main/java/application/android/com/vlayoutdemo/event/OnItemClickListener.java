package application.android.com.vlayoutdemo.event;

import android.view.View;

/**
 * 条目点击
 */
public interface OnItemClickListener<T> {
    void onItemClick(View view, int position, T mData);
}
