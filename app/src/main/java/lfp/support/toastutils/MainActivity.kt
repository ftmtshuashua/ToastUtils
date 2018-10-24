package lfp.support.toastutils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import lfp.support.toast.ToastUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToastUtils.showSimple("初始化")
        setContentView(R.layout.activity_main)
        Kotlin_Bt1.setOnClickListener {
            Thread(Runnable { test() }).start()
        }
        Kotlin_Bt1.text = "Test 4"


        Kotlin_Bt2.setOnClickListener { ToastUtils.cancelSimple() }
    }


    var count = 1
    fun test() {
        ToastUtils.showSimple("Toast打印信息... ${count++} ")
    }

}
