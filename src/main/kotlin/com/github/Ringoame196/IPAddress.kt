package com.github.Ringoame196

import org.bukkit.plugin.Plugin
import java.net.URL
import java.util.Scanner

class IPAddress(private val plugin: Plugin) {
    private val config = plugin.config
    fun acquiredIPAddress(): String? {
        val apiURL = URL(config.getString("apiURL"))
        val scanner = Scanner(apiURL.openStream())
        val ipAddress = if (scanner.hasNext()) scanner.next() else null
        scanner.close()
        return ipAddress
    }
    fun checkIPAddress(ipAddress: String): Boolean {
        val saveIPAddress = config.getString("IP")
        return ipAddress == saveIPAddress
    }
    fun saveIPAddress(ipAddress: String) {
        config.set("IP", ipAddress)
        plugin.saveConfig()
        plugin.reloadConfig()
    }
}
