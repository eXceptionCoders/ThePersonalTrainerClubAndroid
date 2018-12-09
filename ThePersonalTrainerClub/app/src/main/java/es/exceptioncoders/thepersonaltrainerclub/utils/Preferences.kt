package es.exceptioncoders.thepersonaltrainerclub.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel

class Preferences (context: Context) {
    val PREFS_NAME = "es.exceptioncoders.thepersonaltrainerclub"
    val SHARED_JWT = "shared_jwt"
    val SHARED_USER = "shared_user"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var jwtToken: String
        get() = prefs.getString(SHARED_JWT, "")
        set(value) = prefs.edit().putString(SHARED_JWT, value).apply()

    var user: UserModel
        get() = Gson().fromJson(prefs.getString(SHARED_USER, ""), UserModel::class.java)
        set(value) = prefs.edit().putString(SHARED_USER, Gson().toJson(value)).apply()
}