package com.example.pref1app2.fragments

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.pref1app2.data.TracksItem
import com.google.gson.Gson

class ScheduleViewModel(
    context: Context
) : ViewModel() {

    var jsonText: String = readText(context)
    var listOfTracks:MutableList<TracksItem> = mutableListOf()


    init {
        parseJson()
    }

    private fun readText(context: Context): String {
        return context.assets.open("f1_2022_tracks.json").bufferedReader().readText()
    }

    private fun parseJson(): String {
        var obj: TracksItem

        var data = jsonText.drop(1).dropLast(1)

        val listOfObjects = data.split("},")

        for (i in 0..listOfObjects.lastIndex) {
            data = if (i == listOfObjects.lastIndex) listOfObjects[i] else listOfObjects[i].plus("}")
            obj = Gson().fromJson(data, TracksItem::class.java)
            listOfTracks.add(obj)
        }
        return listOfTracks[0].country
    }


}