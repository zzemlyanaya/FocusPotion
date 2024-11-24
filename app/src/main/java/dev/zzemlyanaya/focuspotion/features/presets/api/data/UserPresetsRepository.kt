package dev.zzemlyanaya.focuspotion.features.presets.api.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import kotlinx.coroutines.flow.*
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