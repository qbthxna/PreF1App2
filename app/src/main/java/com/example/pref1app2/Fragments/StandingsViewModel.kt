package com.example.pref1app2.Fragments

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.pref1app2.data.DriverStandingsItem
import com.example.pref1app2.data.TeamStandingItem
import com.google.gson.Gson

class StandingsViewModel(
    context: Context
) : ViewModel() {

    var jsonText: String = readText(context)
    var listOfDriver: MutableList<DriverStandingsItem> = mutableListOf()
    var listOfTeam: MutableList<TeamStandingItem> = mutableListOf()

    init {
        parseDriverJson()
        listOfDriver.sortByDescending { it.points }

        val brandSet = mutableSetOf<String>()
        listOfDriver.forEach { brandSet.add(it.team) }
        brandSet.forEach { listOfTeam.add(TeamStandingItem(it)) }

        listOfDriver.forEach { driver ->
            listOfTeam.forEach {
                if (it.teamName == driver.team) {
                    it.point += driver.points
                    it.teamColor = driver.color
                }
            }
        }
    }

    private fun readText(context: Context): String {
        return context.assets.open("f1_driver_standings.json").bufferedReader().readText()
    }

    private fun parseDriverJson() {
        var obj: DriverStandingsItem

        var data = jsonText.drop(1).dropLast(1)

        val listOfObjects = data.split("},")

        for (i in 0..listOfObjects.lastIndex) {
            data =
                if (i == listOfObjects.lastIndex) listOfObjects[i] else listOfObjects[i].plus("}")
            obj = Gson().fromJson(data, DriverStandingsItem::class.java)
            listOfDriver.add(obj)
        }
    }
}