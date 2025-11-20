package com.example.oving7.managers

import android.content.Context
import java.io.*

class FileManager(private val context: Context) {
    fun readRawFile(resourceId: Int): List<String> {
        val result = mutableListOf<String>()
        try {
            val inputStream = context.resources.openRawResource(resourceId)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line = reader.readLine()
            while (line != null) {
                result.add(line)
                line = reader.readLine()
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    fun writeToInternalFile(filename: String, content: String) {
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(content.toByteArray())
        }
    }
}