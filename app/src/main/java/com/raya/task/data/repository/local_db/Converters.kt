package com.raya.task.data.repository.local_db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.raya.task.data.model.UserData


class Converters {

    @TypeConverter
    fun fromUserDataToJson(userData: List<UserData>): String {
        return Gson().toJson(userData)
    }

    @TypeConverter
    fun fromJsonToUserData(serviceName: String): List<UserData> {
        return Gson().fromJson(serviceName, Array<UserData>::class.java).toList()
//        val listType = object : TypeToken<ArrayList<String?>?>() {}.getType()
//        return Gson().fromJson(serviceName, listType)

//        return Gson().fromJson(serviceName, List<ServiceLabel>::class.java)
    }

}

