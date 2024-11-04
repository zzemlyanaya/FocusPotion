package dev.zzemlyanaya.focuspotion.app.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UserPresetsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val presets: Flow<List<PresetEntity>> = dataStore.data
        .catch {
            Log.e(TAG, "Error while fetching preferences", it)
            emit(emptyPreferences())
        }
        .map { prefs -> Json.decodeFromString<List<PresetEntity>>(prefs[PRESETS_KEY] ?: EMPTY_LIST) }
        .catch { emit(emptyList()) }

    suspend fun savePresets(data: List<PresetEntity>) {
        dataStore.edit { prefs ->
            prefs[PRESETS_KEY] = Json.encodeToString(data)
        }
    }

    companion object {
        private val PRESETS_KEY = stringPreferencesKey("presets_key")
        private const val TAG = "PresetsRepo"
        private const val EMPTY_LIST = "[]"
    }
}