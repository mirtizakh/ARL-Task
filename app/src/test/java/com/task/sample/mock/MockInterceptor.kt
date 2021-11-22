package com.task.sample.mock

import com.task.sample.util.empty
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.io.InputStream

class MockInterceptor : Interceptor {
    companion object{
        var fileName = String.empty
    }
    override fun intercept(chain: Interceptor.Chain): Response {
            val responseString = readJsonFile()
            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString!!)
                .body(responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
                .addHeader("content-type", "application/json")
                .build()
        }

        private fun readJsonFile(): String? {
        return try {
            val inputStream: InputStream? = this.javaClass.getResourceAsStream(fileName)
            inputStream?.let {stream->
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                stream.read(buffer)
                stream.close()
                String(buffer)
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}

