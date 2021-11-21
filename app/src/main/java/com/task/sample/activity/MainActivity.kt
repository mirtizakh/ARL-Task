package com.task.sample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task.sample.R

class MainActivity : AppCompatActivity() {
    // region VARIABLES

    // region LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // end region LIFECYCLE
}