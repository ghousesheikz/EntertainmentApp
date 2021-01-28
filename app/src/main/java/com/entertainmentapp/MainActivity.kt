package com.entertainmentapp

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.WorkManager


class MainActivity : BaseClass() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request!!.id)
                .observe(this, Observer {

                    val status: String = it.state.name
                    Toast.makeText(this,status, Toast.LENGTH_SHORT).show()
                })


        WorkManager.getInstance(this).enqueue(request!!)
    }
}