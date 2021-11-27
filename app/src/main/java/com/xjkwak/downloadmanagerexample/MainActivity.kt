package com.xjkwak.downloadmanagerexample

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xjkwak.downloadmanagerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            MyDownloadManager.beginDownload(this, "https://github.com/xjkwak/curso-android-kotlin-2021/blob/main/Android2021Teknhe.pdf")
        }


        registerReceiver(MyDownloadManager.myBroadcastReceiver, IntentFilter(android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}