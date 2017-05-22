package test.qht.com.demo_0519.model.utils;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ${仇海涛} on 2017/5/19.
 * 类的用途 ：
 */

public class HttpUtils {

    public static <T>void getJson(String uri, HashMap<String,String> hashMap, final callBackData<T> callBackData, final Class<T> cla){
        RequestParams params = new RequestParams(uri);
        if (hashMap!=null){
            Iterator<String> iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = hashMap.get(key);
                params.addQueryStringParameter(key,value);
            }
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                T t = gson.fromJson(result, cla);
                callBackData.callBackData(t);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public interface callBackData<T>{
        void callBackData(T t);
    }
}
