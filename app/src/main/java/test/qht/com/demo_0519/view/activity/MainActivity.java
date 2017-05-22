package test.qht.com.demo_0519.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import test.qht.com.demo_0519.R;
import test.qht.com.demo_0519.model.main.MainBean;
import test.qht.com.demo_0519.presenter.Main_Presenter;
import test.qht.com.demo_0519.presenter.MyDecoration;
import test.qht.com.demo_0519.view.adapter.MainAda;
import test.qht.com.demo_0519.view.iview.IMain_view;
import test.qht.com.demo_0519.view.iview.RecyclerOnclick;

public class MainActivity extends AppCompatActivity implements IMain_view<MainBean> {

    private RecyclerView recyclerview;
    private SwipeRefreshLayout swiperefreshlayout;
    private Main_Presenter main_presenter;
    private MainAda ada;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    swiperefreshlayout.setRefreshing(false);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        main_presenter = new Main_Presenter();
        main_presenter.attachView(MainActivity.this);
        ada = new MainAda(this);
        recyclerview.setAdapter(ada);
        ada.attahPresenter(main_presenter);
        ada.setRecyclerOnClick(new RecyclerOnclick() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,Second_Activity.class);
                startActivity(intent);
            }
        });
        main_presenter.getMainData(MainBean.class);
    }

    private void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.addItemDecoration(new MyDecoration());

        swiperefreshlayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swiperefreshlayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();

                int childCount = manager.findLastVisibleItemPosition();
                int itemCount = manager.getItemCount();
                if (itemCount == childCount+1){
                    Toast.makeText(MainActivity.this, "上拉，上啦！", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public void callBack(final MainBean mainBean) {
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ada.setdata(mainBean.getResult().getData());
                Log.d("qqqqqqqqq",mainBean.getResult().getData().size()+"=========");
                ada.notifyDataSetChanged();
                swiperefreshlayout.setRefreshing(true);
                Toast.makeText(MainActivity.this, "下拉，上啦！", Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(1,3000);
            }
        });

    }
}
