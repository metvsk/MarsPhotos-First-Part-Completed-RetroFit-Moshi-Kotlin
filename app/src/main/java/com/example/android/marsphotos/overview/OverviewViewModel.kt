/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsApiService
import com.example.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    //todo 24 convert it to be a list

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    //todo 18 add a backing field to access the photos live data
    val photos: LiveData<List<MarsPhoto>> get() = _photos


    //todo 17 add a live mutable data to use the mars photo
    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {
        //delete this line // _status.value = "Set the Mars API status response here!"
        //TODO 8: call the coroutine from view model scope
        viewModelScope.launch {
//TODO 11 : adding a try and catch block to handle exception arising when there is no network connection
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                //TODO 9: assigning the result received from server to the variable in the view model
                //_status.value = listResult
                //todo 19 getting one photo from the service
//                _photos.value=MarsApi.retrofitService.getPhotos()[0]

                _photos.value=MarsApi.retrofitService.getPhotos()


                //_status.value = "Success :${listResult.size} photos retrieved"
                //todo 20 displaying the url of one image loaded
//                _status.value="First image url : ${_photos.value!!.imgSrcUrl}"
                //todo 26 display all images in the recycler view
                _status.value="All images fetched"

            } catch (e: Exception) {
                println("202020" + e.localizedMessage)
            }
        }
    }
}
