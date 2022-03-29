package com.example.pref1app2.data

import android.graphics.Color

data class TeamStandingItem(
    val teamName: String,
    var teamColor: String = "#000000",
    var point: Int = 0
)