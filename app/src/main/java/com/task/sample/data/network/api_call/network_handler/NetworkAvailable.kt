package nye.health.data.network.api_call.network_handler

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.task.sample.app.AppController

interface NetworkAvailableInterface {
    fun isInternetIsAvailable(): Boolean
}

class NetworkAvailable : NetworkAvailableInterface {
    override fun isInternetIsAvailable(): Boolean {
        val connectivityManager =
            AppController.applicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork.also {
                it?.let {
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(it)
                    if (networkCapabilities != null && (networkCapabilities.hasTransport(
                            NetworkCapabilities.TRANSPORT_WIFI
                        ) || networkCapabilities.hasTransport(
                            NetworkCapabilities.TRANSPORT_CELLULAR
                        ))
                    )
                        return true

                } ?: run { return false }
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            connectivityManager.activeNetworkInfo?.apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    return true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    return true
                }
            } ?: run { return false }
        }
        return false
    }
}