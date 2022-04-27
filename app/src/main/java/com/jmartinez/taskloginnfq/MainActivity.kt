package com.jmartinez.taskloginnfq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.jmartinez.taskloginnfq.response.UserPreferences
import com.jmartinez.taskloginnfq.ui.home.HomeActivity
import com.jmartinez.taskloginnfq.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.accessToken.asLiveData().observe(this, Observer {
            //Toast.makeText(this, it?:"Token is null", Toast.LENGTH_SHORT).show()
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })

    }
}