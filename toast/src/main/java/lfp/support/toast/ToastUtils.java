package lfp.support.toast;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <pre>
 * Tip:
 *      Toast工具类
 *
 *
 * Created by LiFuPing on 2018/10/23 17:14
 * </pre>
 */
public class ToastUtils<T> {

    private static ToastUtils<CharSequence> mInstance;
    private ToastProxy<T> mProxy;

    private ToastUtils(Context context, ToastProxy<T> proxy) {
        new HandlerToast(context.getApplicationContext(), proxy);
        mProxy = proxy;
    }

    /**
     * 大部分情况下不需要调用init()来初始化Toast，因为在首次调用show方法的时候会自动创建Toast
     * 但是如果首次使用是在线程中，则需要在主线程中进行初始化操作
     */
    public static final void init() {
        checkInstance();
    }

    /**
     * 取消显示的Toast
     */
    public void cancel() {
        mProxy.cancel();
    }

    /**
     * 显示Toast
     *
     * @param obj 显示的数据
     */
    public void show(T obj) {
        mProxy.show(obj);
    }

    /**
     * 取消简单实例
     */
    public static final void cancelSimple() {
        checkInstance();
        mInstance.cancel();
    }

    /**
     * 显示Toast
     *
     * @param resId 资源ID或者Int数值
     */
    public static final void showSimple(int resId) {
        checkInstance();
        try {
            mInstance.show(mInstance.mProxy.getContext().getResources().getString(resId));
        } catch (Resources.NotFoundException e) {
            mInstance.show(String.valueOf(resId));
        }
    }

    /**
     * 显示Toast
     *
     * @param text 显示内容
     */
    public static final void showSimple(CharSequence text) {
        checkInstance();
        mInstance.show(text);
    }

    //检查实例是否存在
    private static final void checkInstance() {
        if (mInstance == null)
            mInstance = build(Utils.getApp()).setToastProxy(new DefualtProxy()).create();
    }


    /**
     * Create a new toast instance
     *
     * @param context The context
     * @return The ToastUtils
     */
    public static Build build(Context context) {
        return new Build(context);
    }


    public static final class Build<T> {
        Context context;
        ToastProxy<T> mProxy;

        public Build(Context c) {
            context = c;
        }

        public Build setToastProxy(ToastProxy<T> proxy) {
            mProxy = proxy;
            return this;
        }

        public ToastUtils<T> create() {
            if (context == null) throw new NullPointerException("Context is not null !!!");
            if (mProxy == null) throw new NullPointerException("ToastProxy is not null !!!");
            return new ToastUtils(context, mProxy);
        }

    }

    //    CharSequence
    public static final class DefualtProxy extends ToastProxy<CharSequence> {

        TextView mTV_Info;

        @Override
        protected void onToastCreated(Toast toast) {
            super.onToastCreated(toast);
            toast.setDuration(Toast.LENGTH_LONG);
        }

        @Override
        protected View onCreateView(Context context) {
            return LayoutInflater.from(context).inflate(R.layout.layout_tost, null);
        }

        @Override
        protected void onViewCreated(View view) {
            super.onViewCreated(view);
            mTV_Info = (TextView) view;
        }

        @Override
        protected void onShow(CharSequence obj) {
            mTV_Info.setText(obj);
        }
    }
}
