package com.github.llmaximll.bashgid

import android.app.Application

class BashGidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BashRepository.initialize(this)
    }
}