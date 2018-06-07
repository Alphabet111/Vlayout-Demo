package application.android.com.vlayoutdemo.presenter;

import application.android.com.vlayoutdemo.bean.CommonBean;
import java.util.List;

/**
 * Created by alphabet on 2018/6/7.
 */

public interface MainView {
  void getGridDataSuccess(List<CommonBean> list);

  void getLinearDataSuccess(List<CommonBean> list);
}
