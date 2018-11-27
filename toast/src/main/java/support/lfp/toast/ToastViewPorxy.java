package support.lfp.toast;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * <pre>
 * Tip:
 *      代理Toast处理View相关操作
 *
 * Function:
 *
 *
 * Created by LiFuPing on 2018/11/2 10:37
 * </pre>
 */
public abstract class ToastViewPorxy<T> {
    protected View mView;
    T mData;


    /**
     * 初始化View
     *
     * @param context The context
     * @return The view
     */
    protected View initView(Context context) {
        View view = onCreateView(context);
        mView = view;
        return view;
    }

    /**
     * 当Proxy绑定Toast的时候回调这个方法
     *
     * @param toast The toast
     */
    protected abstract void onBindToast(Toast toast);

    /**
     * 创建View
     *
     * @param context The context
     * @return The view
     */
    protected abstract View onCreateView(Context context);

    /**
     * 获得Proxy中缓存的View
     *
     * @return 缓存在Proxy中的View
     */
    public View getChacheView() {
        return mView;
    }

    /**
     * 设置View的显示数据
     *
     * @param data The data
     */
    public void setData(T data) {
        mData = data;
    }

    /**
     * 当View被显示的时候，设置View的数据
     *
     * @param view The view
     * @param data The data
     */
    protected abstract void onShow(View view, T data);

}
