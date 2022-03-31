package com.example.pref1app2.fragments

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.pref1app2.data.TeamsItem
import com.google.gson.Gson

class TeamsViewModel(
    context: Context
) : ViewModel() {

    var jsonText: String = readText(context)
    var listOfTeams:MutableList<TeamsItem> = mutableListOf()


    init {
        parseJson()
    }

    private fun readText(context: Context): String {
        return context.assets.open("f1_teams.json").bufferedReader().readText()
    }

    private fun parseJson(): String {
        var obj: TeamsItem

        var data = jsonText.drop(1).dropLast(1)

        val listOfObjects = data.split("},")

        for (i in 0..listOfObjects.lastIndex) {
            data = if (i == listOfObjects.lastIndex) listOfObjects[i] else listOfObjects[i].plus("}")
            obj = Gson().fromJson(data, TeamsItem::class.java)
            listOfTeams.add(obj)
        }
        return listOfTeams[0].teamName
    }
}