package test.qht.com.demo_0519.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.qht.com.demo_0519.R;
import test.qht.com.demo_0519.model.main.MainBean;
import test.qht.com.demo_0519.presenter.Main_Presenter;
import test.qht.com.demo_0519.view.iview.RecyclerOnclick;

/**
 * Created by ${仇海涛} on 2017/5/19.
 * 类的用途 ：
 */

public class MainAda extends RecyclerView.Adapter<MainAda.MyViewHolder> {
    private Context context;
    private RecyclerOnclick onclick ;
    public void setRecyclerOnClick(RecyclerOnclick recyclerOnClick){
        this.onclick = recyclerOnClick;
    }

    public MainAda(Context context) {
        this.context = context;
    }
    private Main_Presenter mMain_presenter;
    private List<MainBean.ResultBean.DataBean> list = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.main_item,null);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                onclick.onItemClick(v,position);
            }
        });

        return holder;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getTitle());
        mMain_presenter.setImg(holder.img,list.get(position).getThumbnail_pic_s());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void attahPresenter(Main_Presenter presenter){
        this.mMain_presenter = presenter;
    }

    public void setdata(List<MainBean.ResultBean.DataBean> data) {
        Log.d("qqqqqqqqq",data.size()+"     ada=========");
        if (data!=null){
            this.list = data;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView =  (TextView) itemView.findViewById(R.id.text1);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
