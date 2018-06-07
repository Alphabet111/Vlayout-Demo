package application.android.com.vlayoutdemo.base;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import application.android.com.vlayoutdemo.event.OnItemChildClickListener;
import application.android.com.vlayoutdemo.event.OnItemClickListener;
import com.bumptech.glide.Glide;

/**
 * Created by Moushao on 2017/8/23.
 */

public class VBaseHolder<W> extends RecyclerView.ViewHolder {
  public OnItemChildClickListener itemChildClickListener;
  public Context mContext;
  public View itemView;
  public W mData;
  public int position;
  private SparseArray<View> viewMap = new SparseArray();

  public VBaseHolder(View itemView) {
    super(itemView);
    this.itemView = itemView;
    itemView.setTag(this);
    init();
  }

  public void init() {

  }

  public void setContext(Context context) {
    mContext = context;
  }

  public void setOnItemClickListener(final OnItemClickListener listener) {
    if (listener != null){
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          listener.onItemClick(itemView,position,mData);
        }
      });
    }
  }

  public void setOnItemChildClickListener(OnItemChildClickListener itemChildClickListener) {
    this.itemChildClickListener = itemChildClickListener;
  }

  public void setData(int position, W mData) {
    this.mData = mData;
    this.position = position;
  }

  public void clear() {
    this.viewMap.clear();
    this.itemView = null;
  }

  public <T extends View> T get(@IdRes int id) {
    View childView = this.viewMap.get(id);
    if (childView == null && this.itemView != null) {
      childView = this.itemView.findViewById(id);
      this.viewMap.put(id, childView);
    }
    return (T) childView;
  }

  public VBaseHolder setText(@IdRes int id, CharSequence text) {
    TextView tv = this.get(id);
    if (tv != null) {
      tv.setText(text);
    }

    return this;
  }

  public VBaseHolder setText(@IdRes int id, @StringRes int strId) {
    TextView tv = this.get(id);
    if (tv != null) {
      tv.setText(strId);
    }

    return this;
  }

  public VBaseHolder setTextColor(@IdRes int id, int color) {
    TextView tv = this.get(id);
    if (tv != null) {
      tv.setTextColor(color);
    }

    return this;
  }

  public VBaseHolder setImageResource(@IdRes int id, @DrawableRes int drawId) {
    ImageView iv = this.get(id);
    if (iv != null) {
      iv.setImageResource(drawId);
    }

    return this;
  }

  public VBaseHolder setVisible(@IdRes int id, boolean visible) {
    View tv = this.get(id);
    if (tv != null) {
      tv.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    return this;
  }

  public VBaseHolder setEnable(@IdRes int id, boolean enable) {
    View tv = this.get(id);
    if (tv != null) {
      tv.setEnabled(enable);
    }

    return this;
  }

  public VBaseHolder setClickListener(View.OnClickListener listener) {
    if (this.itemView != null) {
      this.itemView.setOnClickListener(listener);
    }

    return this;
  }

  public VBaseHolder setClickListener(@IdRes int id, View.OnClickListener listener) {
    View tv = this.get(id);
    if (tv != null) {
      tv.setOnClickListener(listener);
    }

    return this;
  }

  public VBaseHolder loadImage(@IdRes int id, String imageUrl) {
    ImageView iv = this.get(id);
    if (iv != null && imageUrl != null) {
      Glide.with(mContext).load(imageUrl).into(iv);
    }

    return this;
  }

  public VBaseHolder loadImage(@IdRes int id, String imageUrl, @DrawableRes int df) {
    ImageView iv = this.get(id);
    if (iv != null && imageUrl != null) {
      Glide.with(mContext).load(imageUrl).into(iv);
    }

    return this;
  }

}
