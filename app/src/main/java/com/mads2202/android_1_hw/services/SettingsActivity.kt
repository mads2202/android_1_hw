package com.mads2202.android_1_hw.services

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.mads2202.android_1_hw.MainActivity
import com.mads2202.android_1_hw.R

class SettingsActivity : AppCompatActivity() {
    private lateinit var mThemeSwitcher: SwitchCompat

    companion object {
        const val THEME = "theme"
    }

    private var themeId = R.style.Theme_Android_1_hw_my_theme_dark
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        mThemeSwitcher = findViewById(R.id.theme_switcher)
        themeId= intent.extras!!.getInt(MainActivity.THEME)
        mThemeSwitcher.isChecked = themeId == R.style.Theme_Android_1_hw_my_theme_dark
        mThemeSwitcher.setOnClickListener {
            var intent = Intent()
            var resultCode: Int
            if (mThemeSwitcher.isChecked) {
                resultCode = Activity.RESULT_OK
                intent.putExtra(THEME, R.style.Theme_Android_1_hw_my_theme_dark)
            } else {
                resultCode = Activity.RESULT_CANCELED
                intent.putExtra(THEME, R.style.Theme_Android_1_hw_my_theme)

            }

            setResult(resultCode, intent)
        }


    }
}