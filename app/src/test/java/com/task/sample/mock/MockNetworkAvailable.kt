package com.task.sample.mock

import com.task.sample.data.network.api_call.network_handler.NetworkAvailableInterface

class MockNetworkAvailable : NetworkAvailableInterface {
     var isNetworkAvailable = false
    override fun isInternetIsAvailable(): Boolean {
        return isNetworkAvailable
    }

}