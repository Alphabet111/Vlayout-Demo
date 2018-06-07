package application.android.com.vlayoutdemo.presenter;

import application.android.com.vlayoutdemo.bean.CommonBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alphabet on 2018/6/7.
 */

public class MainPresenter {

  private MainView view;

  public MainPresenter(MainView view) {

    this.view = view;
  }

  public void getLinearData() {
    List<CommonBean> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) {
        list.add(new CommonBean(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528367732238&di=d0d6f04717cdc970a7af9fa51a13ab32&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F62%2F28%2F25758PICrXn_1024.jpg",
            "太极"));
      }else {
        list.add(new CommonBean(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1528357768&di=a582f0cd4ba7ee56bd2a7a7911058af0&src=http://s3.sinaimg.cn/mw690/003vCPR6gy6WsKBm3v472&690",
            "八卦"));
      }
    }
    view.getGridDataSuccess(list);
  }

  public void getGridData() {
    List<CommonBean> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new CommonBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528367655762&di=71f1dbd2a1d2728398e68dce57833f39&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201504%2F12%2F20150412H4917_Shzmx.thumb.700_0.jpeg","降龙十八掌"));
    }
    view.getLinearDataSuccess(list);
  }
}
