package com.clerdsonjuca.drive

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.NetworkRequest.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.clerdsonjuca.drive.ui.MyPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar!!.setTitle("")
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.CYAN))
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        supportActionBar!!.setLogo(R.drawable.logo_parking)
        supportActionBar!!.setDisplayUseLogoEnabled(true)
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        Tab.setupWithViewPager(viewPager)

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
            }

            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Alerta.")
                builder.setMessage("Este app precisa de internet")
                builder.setPositiveButton("Sim"){dialog, which ->
                    Toast.makeText(this@MainActivity,"ok.",Toast.LENGTH_SHORT).show()

                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}