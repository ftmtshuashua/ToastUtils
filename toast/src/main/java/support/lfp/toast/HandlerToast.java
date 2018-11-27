package support.lfp.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <pre>
 * Tip:
 *      内置Hanlder实现在线程中调用
 *      弹出Toast需要通知栏权限
 *
 * Function:
 *
 * Created by LiFuPing on 2018/11/2 10:46
 * </pre>
 */
class HandlerToast extends Toast {
    private static final int ID_CANCEL = 1;
    private static final int ID_SHOW = 2;
    private static HandlerToast mInstance;
    private ToastViewPorxy mProxy;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ID_SHOW:
                    showToast();
                    break;
                case ID_CANCEL:
                    HandlerToast.super.cancel();
                    break;
            }
        }
    };

    private HandlerToast(Context context) {
        super(context);

    }

    @Override
    public void show() {
        mHandler.removeMessages(ID_SHOW);
//        mHandler.sendEmptyMessage(ID_CANCEL);
        mHandler.sendEmptyMessage(ID_SHOW);
//        mHandler.sendEmptyMessageDelayed(100, ID_SHOW);
    }

    @Override
    public void cancel() {
        mHandler.sendEmptyMessage(ID_CANCEL);
    }

    private void showToast() {
        if (mProxy == null) throw new NullPointerException("Proxy can not be null");
        View view = mProxy.getChacheView();
        if (view == null) view = mProxy.initView(getContext());
        if (getView() == null) {
            setView(view);
        } else if (getView() == view) { /*使用同一个View重复显示*/
            //在显示新的Toast需要保证旧Toast被正常清理
            try {
                Field tn = getClass().getSuperclass().getDeclaredField("mTN");
                tn.setAccessible(true);
                Object obj_TN = tn.get(this);
                Method hide_toast = obj_TN.getClass().getMethod("handleHide");
                hide_toast.invoke(obj_TN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { /*使用一个新的View*/
            setView(view);
        }
        super.show();
        mProxy.onShow(view, mProxy.mData);
    }

    /**
     * 设置View代理
     *
     * @param proxy View代理
     */
    public void setViewProxy(ToastViewPorxy proxy) {
        mProxy = proxy;
        mProxy.onBindToast(this);
    }

    /**
     * 获得HandlerToast单例
     *
     * @param context The context
     * @return The HandlerToast
     */
    public static final HandlerToast getInstance(Context context) {
        if (mInstance == null) {
            synchronized (HandlerToast.class) {
                if (mInstance == null)
                    mInstance = new HandlerToast(context.getApplicationContext());
            }
        }
        return mInstance;
    }

    protected Context getContext() {
        Context context = null;
        try {
            Field context_field = getClass().getSuperclass().getDeclaredField("mContext");
            context_field.setAccessible(true);
            context = (Context) context_field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }
}
