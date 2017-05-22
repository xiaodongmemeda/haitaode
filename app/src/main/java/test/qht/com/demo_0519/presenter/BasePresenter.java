package test.qht.com.demo_0519.presenter;

import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import test.qht.com.demo_0519.R;
import test.qht.com.demo_0519.view.iview.Iview;

/**
 * Created by ${仇海涛} on 2017/5/19.
 * 类的用途 ：
 */

public class BasePresenter<T extends Iview>{
    private  T mt;
    ImageOptions options = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.ic_launcher).build();
    public void attachView(T t){
        this.mt = t;
    }
    public T getIview(){
        return mt;
    }
    public void setImg(ImageView img , String uri){
        x.image().bind(img,uri,options);
    }

}
