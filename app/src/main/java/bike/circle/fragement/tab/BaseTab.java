package bike.circle.fragement.tab;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bike.circle.activities.StartActivity;

/**
 * Created by MrH on 2017/5/15.
 */

public abstract class BaseTab implements View.OnClickListener{

    protected View view;
    protected Context context;

    public BaseTab(Context context) {
        this.context = context;
        onCreate();
    }

    protected void onCreate(){
        view = LayoutInflater.from(context).inflate(getLayout(),null);
        init();
        initView();
        bindEvent();
        service();
    }

    public View findViewById(@IdRes int id){
        return view.findViewById(id);
    }

    public View getView(){
        return view;
    }

    public Context getContext(){
        return context;
    }

    public void startActivity(Intent intent){
        context.startActivity(intent);
    }

    protected abstract void init();

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void bindEvent();

    protected abstract void service();

}
