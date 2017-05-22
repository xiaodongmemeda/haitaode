package test.qht.com.demo_0519.presenter;

import java.util.HashMap;

import test.qht.com.demo_0519.model.utils.HttpUtils;
import test.qht.com.demo_0519.model.utils.UriUtils;
import test.qht.com.demo_0519.view.iview.ISecond_view;

/**
 * Created by ${仇海涛} on 2017/5/19.
 * 类的用途 ：
 */

public class Second_Presenter extends BasePresenter<ISecond_view>{
    HashMap<String,String> hashMap = new HashMap<>();
   public <T>void getMainData(Class<T> cla){
       HttpUtils.getJson(UriUtils.uri, hashMap, new HttpUtils.callBackData<T>() {
           @Override
           public void callBackData(T t) {
                getIview().callBack(t);
           }
       },cla);
   }
}
