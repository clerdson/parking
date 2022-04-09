package com.clerdsonjuca.drive

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clerdsonjuca.drive.ui.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.setTitle("")
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.CYAN))
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        supportActionBar!!.setLogo(R.drawable.logo_parking)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        Tab.setupWithViewPager(viewPager)
    }
}