package com.clerdsonjuca.drive

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.CYAN))
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        supportActionBar!!.setLogo(R.drawable.logo_parking)
        supportActionBar!!.setDisplayUseLogoEnabled(true)

        val pay = intent.getStringExtra("pay")
        val time = intent.getStringExtra("time")
        val plate = intent.getStringExtra("plate")
        time2D.text = time
        time2D2.text = plate
        time3D.text = pay
        val back = findViewById<ImageButton>(R.id.imageButton3)
        back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java,)
            startActivity(intent)
        }
    }
}