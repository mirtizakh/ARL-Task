package com.task.sample

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

object JsonReader {

    fun readJson(obj: Any, filename: String): JsonObject?
    {
        // a method to read text file.
        val classLoader = obj.javaClass.classLoader
        val  file = classLoader?.getResource(filename)
        file?.let {
            val result = String(
                Files.readAllBytes(Paths.get(file.toURI())),
                Charset.forName("UTF-8")
            )
            return  Gson().fromJson(result, JsonObject::class.java)
        }
        return null
    }
}