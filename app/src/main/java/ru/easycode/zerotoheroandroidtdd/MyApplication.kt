package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class MyApplication : Application() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        val liveDataWrapper = LiveDataWrapper.Base()
        val repository = Repository.Base()
        mainViewModel = MainViewModel(liveDataWrapper, repository)
    }
}