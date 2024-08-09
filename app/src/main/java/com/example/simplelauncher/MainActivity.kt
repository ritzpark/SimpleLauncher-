package com.example.simplelauncher

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val pm = packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val apps = pm.queryIntentActivities(intent, 0)

        for (app in apps) {
            val button = Button(this).apply {
                text = app.loadLabel(pm)
                setOnClickListener {
                    val launchIntent = packageManager.getLaunchIntentForPackage(app.activityInfo.packageName)
                    launchIntent?.let { startActivity(it) }
                }
            }
            layout.addView(button)
        }

        setContentView(layout)
    }
}
