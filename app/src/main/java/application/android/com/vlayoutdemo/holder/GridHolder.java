package application.android.com.vlayoutdemo.holder;

import android.view.View;
import application.android.com.vlayoutdemo.R;
import application.android.com.vlayoutdemo.base.VBaseHolder;
import application.android.com.vlayoutdemo.bean.CommonBean;

/**
 * Created by alphabet on 2018/6/7.
 */

public class GridHolder extends VBaseHolder<CommonBean>{
  public GridHolder(View itemView) {
    super(itemView);
  }

  @Override public void setData(int position, CommonBean mData) {
    super.setData(position, mData);
    loadImage(R.id.iv_icon,mData.picUrl);
    setText(R.id.tv_name,mData.name);
  }

}
