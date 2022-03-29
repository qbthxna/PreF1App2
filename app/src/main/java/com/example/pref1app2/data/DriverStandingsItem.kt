package com.example.pref1app2.data

data class DriverStandingsItem(
    val country: String,
    val driver: String,
    val driverNumber: Int,
    val points: Int,
    var color: String,
    val team: String
)