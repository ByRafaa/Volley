package com.rafaelescudero.volley

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.item.view.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        if (intent.extras!= null) {

            Glide.with(applicationContext).load(intent.getStringExtra("url")).into(imageInfo)

            dataInfo.text = intent.getStringExtra("data")

        }

    }
}
