package com.example.game.fragments.settings

import android.content.Context
import android.content.SharedPreferences

class SettingsPlaylistImpl(private val sharedPreferences: SharedPreferences)  {

    fun setComplexity(complexity:Complexity) {
        sharedPreferences.edit().putInt(PLAYLIST_KEY, complexity.i).apply()
    }

    fun getComplexity(): Complexity {
        return when(sharedPreferences.getInt(PLAYLIST_KEY, 1)){
            1->{
                Complexity.HARD
            }
            2->{
                Complexity.MIDLE

            }
            3->{
                Complexity.EASE
            }
            else->{
                Complexity.HARD
            }
        }
    }

    companion object {
        private const val PREFERENCES_KEY = "PLAYLIST_PREFERENCES_KEY"
        private const val PLAYLIST_KEY = "PLAYLIST_KEY"

        fun getSettingsPlaylist(context: Context): SettingsPlaylistImpl {
            return SettingsPlaylistImpl(context.getSharedPreferences(PREFERENCES_KEY,
                Context.MODE_PRIVATE
            ))
        }
    }
}