package test.qht.com.demo_0519.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import test.qht.com.demo_0519.R;
import test.qht.com.demo_0519.model.main.SecondBean;
import test.qht.com.demo_0519.presenter.Second_Presenter;
import test.qht.com.demo_0519.view.adapter.SecondAdapter;
import test.qht.com.demo_0519.view.iview.ISecond_view;

public class Second_Activity extends AppCompatActivity implements ISecond_view<SecondBean> {

    private PullToRefreshListView pulltorefresh_listview;
    private ListView listView;
    private Second_Presenter presenter;
    private SecondAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
        initView();
        initData();
    }

    private void initData() {
        presenter = new Second_Presenter();
        presenter.attachView(this);
        adapter = new SecondAdapter(this);
        adapter.setSecondPresenter(presenter);
        listView.setAdapter(adapter);
        presenter.getMainData(SecondBean.class);
    }

    private void initView() {
        pulltorefresh_listview = (PullToRefreshListView) findViewById(R.id.pulltorefresh_listview);
        listView = pulltorefresh_listview.getRefreshableView();
        pulltorefresh_listview.setMode(PullToRefreshBase.Mode.BOTH);
        pulltorefresh_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pulltorefresh_listview.setRefreshing(true);
                Toast.makeText(Second_Activity.this, "上啦，下拉！", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      pulltorefresh_listview.onRefreshComplete();
                    }
                },3000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int i = listView.getLastVisiblePosition();
                int childCount = listView.getChildCount();
                if (childCount == i+1){
                    Toast.makeText(Second_Activity.this, "上啦，上拉！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void callBack(SecondBean secondBean) {
        adapter.setData(secondBean.getResult().getData());
        adapter.notifyDataSetChanged();
    }
}
