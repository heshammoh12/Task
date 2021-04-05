package com.raya.task.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raya.task.R
import com.raya.task.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_home_framelayout,
                HomeFragment.newInstance("", ""),
                HomeFragment.TAG
            )
            addToBackStack(null)
            commit()
        }
    }
}