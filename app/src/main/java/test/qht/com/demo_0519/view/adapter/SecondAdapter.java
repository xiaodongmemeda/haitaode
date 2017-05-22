package test.qht.com.demo_0519.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.qht.com.demo_0519.R;
import test.qht.com.demo_0519.model.main.SecondBean;
import test.qht.com.demo_0519.presenter.BasePresenter;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：
 */

public class SecondAdapter extends BaseAdapter{
    Context context;
    List<SecondBean.ResultBean.DataBean> list = new ArrayList<>();
    private BasePresenter mPresenter;


    public SecondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SecondBean.ResultBean.DataBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.second_item,null);
            holder.imageView = (ImageView) view.findViewById(R.id.item_img);
            holder.textView = (TextView) view.findViewById(R.id.item_text1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        mPresenter.setImg(holder.imageView,getItem(i).getThumbnail_pic_s());
        holder.textView.setText(getItem(i).getTitle());
        return view;
    }



    public void setData(List<SecondBean.ResultBean.DataBean> data) {
        if (data!=null){
            list.addAll(data);
        }

    }

    public void setSecondPresenter(BasePresenter Presenter) {
        this.mPresenter = Presenter;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
