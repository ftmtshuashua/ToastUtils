package lfp.support.toast;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * <pre>
 * Tip:
 *      自定义Toast代理
 *
 * Created by LiFuPing on 2018/10/23 17:48
 * </pre>
 */
public abstract class ToastProxy<T> {

    public ToastProxy() {

    }

    private Toast mToast;
    private T mShowData;

    /**
     * 当Toast被创建的时候，在这里可以做一些关于Toast的初始化配置
     *
     * @param toast The toast
     */
    protected void onToastCreated(Toast toast) {
        mToast = toast;
    }

    /**
     * 如果Toast未设置View，将会通过这个方法来创建一个View并绑定到Toast中
     *
     * @param context The context
     * @return The View
     */
    protected abstract View onCreateView(Context context);

    /**
     * 当Toast的View创建完成，可以通过这个方法来获取View中的元素
     *
     * @param view The view
     */
    protected void onViewCreated(View view) {
    }

    /**
     * 传入数据并显示Toast
     *
     * @param obj 显示的数据
     */
    public void show(T obj) {
        mShowData = obj;
        mToast.show();
    }

    /**
     * 当Toast准备显示的时候调用此函数，我们应该在这个函数中将数据与View进行绑定。
     *
     * @param obj 显示的数据
     */
    protected abstract void onShow(T obj);

    public void cancel() {
        mToast.cancel();
    }

    public T getData() {
        return mShowData;
    }

    public Context getContext() {
        return mToast.getView().getContext();
    }
}
