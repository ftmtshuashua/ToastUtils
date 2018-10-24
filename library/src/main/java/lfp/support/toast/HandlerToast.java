package lfp.support.toast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

/**
 * <pre>
 * Tip:
 *      允许在线程种使用的Toast
 *
 * Created by LiFuPing on 2018/10/23 17:45
 * </pre>
 */
public class HandlerToast extends Toast implements Runnable {

    private Handler mHandler = new Handler(Looper.getMainLooper()); // 吐司处理消息线程
    private ToastProxy mProxy;
    private Context mContext;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public HandlerToast(Context context, ToastProxy proxy) {
        super(context);
        mContext = context;
        mProxy = proxy;
        mProxy.onToastCreated(this);

    }

    @Override
    public void setView(View view) {
        super.setView(view);
        mProxy.onViewCreated(view);
    }


    @Override
    public void show() {
        if (getView() == null) setView(mProxy.onCreateView(mContext));

        mHandler.removeCallbacks(this);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.show();
            mProxy.onShow(mProxy.getData() );
        } else {
            mHandler.postDelayed(this, 30);
        }
    }

    @Override
    public void run() {
        super.show();
        mProxy.onShow(mProxy.getData());
    }

    @Override
    public void cancel() {
        super.cancel();
        mHandler.removeCallbacks(this);
    }
}
