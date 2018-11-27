package support.lfp.toast;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * <pre>
 * Tip:
 *
 *
 * Function:
 *
 * Created by LiFuPing on 2018/11/2 11:06
 * </pre>
 */
public class ToastUtils<T> {
    private HandlerToast mToast;
    private ToastViewPorxy<T> mProxy;
    private static ToastUtils mInstance;

    private ToastUtils(Context context) {
        mToast = HandlerToast.getInstance(context);
    }

    public void setViewProxy(ToastViewPorxy<T> proxy) {
        this.mProxy = proxy;
    }

    /**
     * 显示Toast
     *
     * @param data The data
     */
    public void show(T data) {
        mProxy.setData(data);
        mToast.setViewProxy(mProxy);
        mToast.show();
    }

    /**
     * 取消Toast
     */
    public void cancel() {
        mToast.cancel();
    }

    /**
     * 初始化ToastUtils默认配置
     *
     * @param context The context
     */
    public static final void init(final Context context) {
        if (mInstance == null) {
            mInstance = build(context, new DefualtProxy());
        }
    }

    /**
     * 构建一个自定义的ToastUtils实例
     *
     * @param context The context
     * @param proxy   The view proxy
     * @param <K>     Proxy的数据类型
     * @return The ToastUtils
     */
    public static final <K> ToastUtils<K> build(final Context context, ToastViewPorxy<K> proxy) {
        if (context == null) throw new NullPointerException("context can not be null");
        if (proxy == null) throw new NullPointerException("porxy can not be null");
        ToastUtils util = new ToastUtils(context);
        util.setViewProxy(proxy);
        return util;
    }


    private static final class DefualtProxy extends ToastViewPorxy<CharSequence> {

        @Override
        protected void onBindToast(Toast toast) {
            toast.setDuration(Toast.LENGTH_LONG);
        }

        @Override
        protected View onCreateView(Context context) {
            return LayoutInflater.from(context).inflate(R.layout.layout_simple_tost, null);
        }

        @Override
        protected void onShow(View view, CharSequence data) {
            ((TextView) view).setText(data);
        }

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
            mInstance.show(mInstance.mToast.getContext().getResources().getString(resId));
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

    private static void checkInstance() {
        if (mInstance == null) throw new IllegalStateException("请使用init()初始化这个ToastUtils工具");
    }

}
