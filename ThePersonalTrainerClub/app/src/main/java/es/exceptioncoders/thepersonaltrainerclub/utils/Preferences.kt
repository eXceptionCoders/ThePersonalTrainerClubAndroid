package es.exceptioncoders.thepersonaltrainerclub.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences (context: Context) {
    val PREFS_NAME = "es.exceptioncoders.thepersonaltrainerclub"
    val SHARED_JWT = "shared_jwt"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var jwtToken: String
        get() = prefs.getString(SHARED_JWT, "")
        set(value) = prefs.edit().putString(SHARED_JWT, value).apply()
}