package lfp.support.toastutils.custom

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import support.lfp.toast.ToastUtils
import support.lfp.toast.ToastViewPorxy
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
    var toast: ToastUtils<ToastData>? = null

    fun init(context: Context) {
        Log.e("ToastStyle","初始化。。。")
        toast = ToastUtils.build(context, object : ToastViewPorxy<ToastData>() {

            var IV_Head: ImageView? = null
            var TV_Name: TextView? = null
            var TV_Info: TextView? = null
            override fun onBindToast(toast: Toast?) {
                toast?.duration = Toast.LENGTH_SHORT
            }

            override fun onShow(view: View?, data: ToastData?) {
                IV_Head?.setImageResource(data?.headres!!)
                TV_Name?.text = data?.name
                TV_Info?.text = data?.info
            }

            override fun onCreateView(context: Context?): View {
                var view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null)
                IV_Head = view?.findViewById(R.id.IV_Head)
                TV_Name = view?.findViewById(R.id.TV_Name)
                TV_Info = view?.findViewById(R.id.TV_Info)
                return view
            }
        })
    }

    fun show(data: ToastData) = toast?.show(data)


}