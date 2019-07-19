package com.sikrinick.openweathermapclient.data.local.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.Gson
import java.util.*
import kotlin.reflect.KProperty


abstract class PreferenceDelegates(private val prefs: SharedPreferences,
                                   private val rxPrefs: RxSharedPreferences,
                                   private val gson: Gson) {

    abstract class PrefDelegate<T>(val prefKey: String?) {
        abstract operator fun getValue(thisRef: Any?, property: KProperty<*>): T
        abstract operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
    }

    fun stringPref(prefKey: String? = null, defaultValue: String = "") = StringPrefDelegate(prefKey, defaultValue)
    fun observeString(prefKey: String) = rxPrefs.getString(prefKey).asObservable()

    inner class StringPrefDelegate(prefKey: String? = null, val defaultValue: String) : PrefDelegate<String>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>): String = prefs.getString(prefKey ?: property.name, defaultValue) ?: defaultValue
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            prefs.edit {
                putString(prefKey ?: property.name, value)
            }
        }
    }


    fun intPref(prefKey: String? = null, defaultValue: Int = 0) = IntPrefDelegate(prefKey, defaultValue)
    fun observeInt(prefKey: String) = rxPrefs.getInteger(prefKey).asObservable()

    inner class IntPrefDelegate(prefKey: String? = null, val defaultValue: Int) : PrefDelegate<Int>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getInt(prefKey ?: property.name, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            prefs.edit {
                putInt(prefKey ?: property.name, value)
            }
        }
    }


    fun floatPref(prefKey: String? = null, defaultValue: Float = 0f) = FloatPrefDelegate(prefKey, defaultValue)
    fun observeFloat(prefKey: String) = rxPrefs.getFloat(prefKey).asObservable()

    inner class FloatPrefDelegate(prefKey: String? = null, val defaultValue: Float) : PrefDelegate<Float>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getFloat(prefKey ?: property.name, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
            prefs.edit {
                putFloat(prefKey ?: property.name, value)
            }
        }
    }


    fun booleanPref(prefKey: String? = null, defaultValue: Boolean = false) = BooleanPrefDelegate(prefKey, defaultValue)
    fun observeBoolean(prefKey: String) = rxPrefs.getBoolean(prefKey).asObservable()

    inner class BooleanPrefDelegate(prefKey: String? = null, val defaultValue: Boolean) : PrefDelegate<Boolean>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getBoolean(prefKey ?: property.name, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
            prefs.edit().putBoolean(prefKey ?: property.name, value).apply()
        }
    }


    fun longPref(prefKey: String? = null, defaultValue: Long = 0L) = LongPrefDelegate(prefKey, defaultValue)
    fun observeLong(prefKey: String) = rxPrefs.getLong(prefKey).asObservable()

    inner class LongPrefDelegate(prefKey: String? = null, val defaultValue: Long) : PrefDelegate<Long>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getLong(prefKey ?: property.name, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
            prefs.edit().putLong(prefKey ?: property.name, value).apply()
        }
    }


    fun stringSetPref(prefKey: String? = null, defaultValue: Set<String> = HashSet()) = StringSetPrefDelegate(prefKey, defaultValue)
    fun observeStringSet(prefKey: String) = rxPrefs.getStringSet(prefKey).asObservable()

    inner class StringSetPrefDelegate(prefKey: String? = null, val defaultValue: Set<String>) : PrefDelegate<Set<String>>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Set<String> = prefs.getStringSet(prefKey ?: property.name, defaultValue) ?: defaultValue
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) {
            prefs.edit().putStringSet(prefKey ?: property.name, value).apply()
        }
    }

    fun <T> objectPref(prefKey: String, defaultValue: T, type: Class<T>) = ObjectPrefDelegate(prefKey, defaultValue, type)
    fun <T> observeObject(prefKey: String, defaultValue: T, classOfT: Class<T>) = rxPrefs.getString(prefKey)
        .asObservable()
        .map { gson.fromJson(it, classOfT) ?: defaultValue }

    inner class ObjectPrefDelegate<T>(prefKey: String? = null, val defaultValue: T, val classOfT: Class<T>) : PrefDelegate<T>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            val json = prefs.getString(prefKey ?: property.name, null) ?: return defaultValue
            return gson.fromJson(json, classOfT) ?: defaultValue
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            val json = gson.toJson(value)
            prefs.edit().putString(prefKey ?: property.name, json).apply()
        }
    }

}