package com.cattila.react.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cattila.react.network.AlbumApi
import com.cattila.react.network.AlbumProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class AlbumApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<AlbumApiStatus>()

    val status: LiveData<AlbumApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<AlbumProperty>>()

    val properties: LiveData<List<AlbumProperty>>
        get() = _properties

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getAlbumProperties()
    }

    private fun getAlbumProperties() {
        coroutineScope.launch {
            _status.value = AlbumApiStatus.LOADING
            var getPropertiesDeferred = AlbumApi.retrofitService.getProperties()
            try {
                _status.value = AlbumApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                _status.value = AlbumApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = AlbumApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
