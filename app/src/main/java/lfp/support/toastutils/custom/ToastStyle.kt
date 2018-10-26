package lfp.support.toastutils.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import lfp.support.toast.ToastProxy
import lfp.support.toast.ToastUtils
import lfp.support.toastutils.R

/**
 * <pre>
 * Tip:
 *      自定义Toast
 *
 * Created by LiFuPing on 2018/10/25 09:33
 * </pre>
 */

object ToastStyle {
    //    var toast = ToastUtils.build()
    var toast: ToastUtils<ToastData>? = null

    fun init(context: Context) {
        toast = ToastUtils.build(context).setToastProxy(proxy).create() as ToastUtils<ToastData>
    }

    fun show(data: ToastData) = toast?.show(data)

    val proxy = object : ToastProxy<ToastData>() {

        override fun onToastCreated(toast: Toast?) {
            super.onToastCreated(toast)
            toast?.duration = Toast.LENGTH_SHORT
        }

        var IV_Head: ImageView? = null
        var TV_Name: TextView? = null
        var TV_Info: TextView? = null
        override fun onViewCreated(view: View?) {
            super.onViewCreated(view)
            IV_Head = view?.findViewById(R.id.IV_Head)
            TV_Name = view?.findViewById(R.id.TV_Name)
            TV_Info = view?.findViewById(R.id.TV_Info)
        }

        override fun onCreateView(context: Context?): View {
            return LayoutInflater.from(context).inflate(R.layout.custom_toast, null)
        }

        override fun onShow(obj: ToastData?) {
            IV_Head?.setImageResource(obj?.headres!!)
            TV_Name?.text = obj?.name
            TV_Info?.text = obj?.info
        }
    }


}