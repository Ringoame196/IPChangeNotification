package com.github.Ringoame196

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.bukkit.plugin.Plugin
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class DiscordWebhook(private val plugin: Plugin) {
    private val config = plugin.config
    private val webhookURL = config.getString("DiscordWebhookURL")
    fun checkRegistrationURL(): Boolean {
        return webhookURL != ""
    }
    fun sendMessage(message: String) {
        try {
            val gson = Gson()

            val jsonObject = JsonObject().apply {
                addProperty("content", message) // テキストメッセージを追加
            }

            val url = URL(webhookURL)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")

            val outputStreamWriter = OutputStreamWriter(connection.outputStream)
            outputStreamWriter.write(gson.toJson(jsonObject))
            outputStreamWriter.flush()

            println("Response Code: ${connection.responseCode}")
            println("Response Message: ${connection.responseMessage}")

            connection.disconnect()
        } catch (e: Exception) {
            println("エラーが発生しました: ${e.message}")
        }
    }
}
