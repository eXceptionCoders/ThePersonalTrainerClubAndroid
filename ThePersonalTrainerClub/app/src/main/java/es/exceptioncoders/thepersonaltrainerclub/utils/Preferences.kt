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

    var user: UserModel?
        get() {
            val gson = prefs.getString(SHARED_USER, "")
            if (gson.isEmpty()) {
                return null
            }

            return Gson().fromJson(gson, UserModel::class.java)
        }
        set(value) = prefs.edit().putString(SHARED_USER, Gson().toJson(value)).apply()
}