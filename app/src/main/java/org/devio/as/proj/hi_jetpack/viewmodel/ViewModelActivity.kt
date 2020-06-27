package org.devio.`as`.proj.hi_jetpack.viewmodel

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*

class ViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(HiViewModel::class.java)
        viewModel.loadInitData().observe(this, Observer {
            Log.e("AppCompatActivity", "data: " + it)
        })
        Log.e("AppCompatActivity", viewModel.toString() + "--")

    }
}

class HiViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val liveData = MutableLiveData<String>()
    fun loadInitData(): LiveData<String> {

        //.....
        var cache: String? = savedStateHandle.get("cache")
        if (cache == null) {
            //from remote
            cache = " new response from remote"
            savedStateHandle.set("cache", cache)
        }
        liveData.postValue("cache")
        return liveData;
    }
}


class HiAndroidViewModel(val appInstance: Application, val savedStateHandle: SavedStateHandle) :
    AndroidViewModel(appInstance) {

}