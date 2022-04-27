package com.jmartinez.taskloginnfq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jmartinez.taskloginnfq.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        finish()
        startActivity(Intent(this, AuthActivity::class.java))
    }
}