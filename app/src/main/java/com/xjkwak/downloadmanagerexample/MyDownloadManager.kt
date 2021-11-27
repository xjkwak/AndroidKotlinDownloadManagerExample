package com.xjkwak.downloadmanagerexample

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import java.io.File

object MyDownloadManager {
    var downloadID: Long = 0
    fun beginDownload(context: Context, url: String): Long {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), File(url).name)

        Toast.makeText(context, "We will download to ${file.absolutePath}", Toast.LENGTH_SHORT).show()
        // DownloadManager.Request with all necessary parameters to start the download
        val request =
            DownloadManager.Request(Uri.parse(url))
                .setTitle("DownloadManager Demo") // Title of the Download Notification
                .setDescription("Downloading...") // Description of the Download Notification
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE) // Download Notification visibility
                .setDestinationUri(Uri.fromFile(file)) // Uri of the destination file
                .setAllowedOverMetered(true) //  Allow download on Mobile network
                .setAllowedOverRoaming(true) // Allow download on roaming network
        val dm = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadID = dm.enqueue(request) // enqueue the download request.
        return downloadID
    }

    val myBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //Get the download id
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            //Check if the broadcast belong to our download
            if (downloadID.toLong() === id) {
                Toast.makeText(context, "Download complete", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}