package application.android.com.vlayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import application.android.com.vlayoutdemo.base.VBaseAdapter;
import application.android.com.vlayoutdemo.bean.CommonBean;
import application.android.com.vlayoutdemo.event.OnItemChildClickListener;
import application.android.com.vlayoutdemo.event.OnItemClickListener;
import application.android.com.vlayoutdemo.holder.GridHolder;
import application.android.com.vlayoutdemo.holder.LinearHolder;
import application.android.com.vlayoutdemo.holder.LocalDataHolder;
import application.android.com.vlayoutdemo.presenter.MainPresenter;
import application.android.com.vlayoutdemo.presenter.MainView;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

  private RecyclerView recyclerView;
  private VBaseAdapter headerAdapter, linearAdapter, gridAdapter, footerAdapter, footer1Adapter;
  private MainPresenter presenter;
  private DelegateAdapter delegateAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recyclerView);
    presenter = new MainPresenter(this);
    initRecyclerView();
    loadData();
  }

  private void loadData() {
    presenter.getGridData();
    presenter.getLinearData();
  }

  private void initRecyclerView() {
    VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    recyclerView.setRecycledViewPool(viewPool);
    //设置缓存view个数(当视图中view的个数很多时，设置合理的缓存大小，防止来回滚动时重新创建 View)
    viewPool.setMaxRecycledViews(1,1);
    viewPool.setMaxRecycledViews(2,10);
    viewPool.setMaxRecycledViews(3,10);
    viewPool.setMaxRecycledViews(4,1);
    delegateAdapter = new DelegateAdapter(layoutManager, true);

    headerAdapter = new VBaseAdapter<String>(this,1).setData(new ArrayList<String>())
        .setLayout(R.layout.item_header)
        .setLayoutHelper(new SingleLayoutHelper())
        .setHolder(LocalDataHolder.class)
        .setOnItemClickListener(new OnItemClickListener() {
          @Override public void onItemClick(View view, int position, Object mData) {
            Toast.makeText(MainActivity.this, "我是头,大头的头!", Toast.LENGTH_SHORT).show();
          }
        });

    gridAdapter = new VBaseAdapter<CommonBean>(this,2).setData(new ArrayList<CommonBean>())
        .setLayout(R.layout.item_grid)
        .setLayoutHelper(getGridLayoutHelper())
        .setHolder(GridHolder.class)
        .setOnItemClickListener(new OnItemClickListener<CommonBean>() {
          @Override public void onItemClick(View view, int position, CommonBean mData) {
            Toast.makeText(MainActivity.this, mData.name, Toast.LENGTH_SHORT).show();
          }
        });

    linearAdapter = new VBaseAdapter<CommonBean>(this,3).setData(new ArrayList<CommonBean>())
        .setLayout(R.layout.item_linear)
        .setLayoutHelper(new LinearLayoutHelper())
        .setHolder(LinearHolder.class)
        .setOnItemChildClickListener(new OnItemChildClickListener<CommonBean>() {
          @Override public void onItemChildClick(View view, int position, CommonBean mData) {
            switch (view.getId()) {
                case R.id.iv_icon:
                  Toast.makeText(MainActivity.this, "这是第" + position + "张图片!", Toast.LENGTH_SHORT).show();
                  break;
              default:
                Toast.makeText(MainActivity.this, "降龙十八掌!!!!", Toast.LENGTH_SHORT).show();
                  break;
            }
          }
        });

    footerAdapter = new VBaseAdapter<String>(this,4).setData(new ArrayList<String>())
        .setLayout(R.layout.item_footer)
        .setLayoutHelper(new SingleLayoutHelper())
        .setHolder(LocalDataHolder.class)
        .setOnItemClickListener(new OnItemClickListener() {
          @Override public void onItemClick(View view, int position, Object mData) {
            Toast.makeText(MainActivity.this, "我是尾巴尾巴!", Toast.LENGTH_SHORT).show();
          }
        });
    delegateAdapter.addAdapter(headerAdapter);
    delegateAdapter.addAdapter(gridAdapter);
    delegateAdapter.addAdapter(linearAdapter);
    delegateAdapter.addAdapter(footerAdapter);
    recyclerView.setAdapter(delegateAdapter);

  }

  private LayoutHelper getGridLayoutHelper() {
    GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
    //是否自动扩展
    gridLayoutHelper.setAutoExpand(false);
    gridLayoutHelper.setPadding(0, 20, 0, 10);
    gridLayoutHelper.setBgColor(Color.parseColor("#FFFFFF"));
    gridLayoutHelper.setVGap(20);
    return gridLayoutHelper;
  }

  @Override public void getGridDataSuccess(List<CommonBean> list) {
    gridAdapter.setData(list);
    for(int i=0;i<delegateAdapter.getItemCount();i++){
    }
  }

  @Override public void getLinearDataSuccess(List<CommonBean> list) {
    linearAdapter.setData(list);
    headerAdapter.setItem("");
    footerAdapter.setItem("");
  }
}
