package lfp.support.toastutils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import support.lfp.toast.ToastUtils
import lfp.support.toastutils.custom.ToastData
import lfp.support.toastutils.custom.ToastStyle

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            Kotlin_Bt1.id -> ToastUtils.showSimple("我是默认Toast显示出来的消息 ${++count}")
            Kotlin_Bt2.id -> Thread(Runnable { ToastUtils.showSimple("我是线程中显示的Toast ${++count}") }).start()
            Kotlin_Bt3.id -> ToastStyle.show(ToastData("王富贵 ${++count}", R.mipmap.ic_launcher, "我住隔壁我姓王"))
        }
    }

    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToastUtils.init(this)
        ToastStyle.init(this)

        setContentView(R.layout.activity_main)

        Kotlin_Bt1.setOnClickListener(this)
        Kotlin_Bt2.setOnClickListener(this)
        Kotlin_Bt3.setOnClickListener(this)


    }




}
