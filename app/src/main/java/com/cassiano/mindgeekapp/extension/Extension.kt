package com.cassiano.mindgeekapp.extension

import android.content.Context
import android.content.SharedPreferences
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun AppCompatActivity.bindingContentView(layout: Int): ViewDataBinding {
    return DataBindingUtil.setContentView(this, layout)
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, expression: (T?) -> Unit) {
    liveData.observe(this, Observer(expression))
}

fun Context.getSharedPreferences(name: String): SharedPreferences {
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).run {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}

fun SharedPreferences.get(key: String, default: String? = null): String {
    val value by lazy { getString(key, default).orEmpty() }

    return takeIf { contains(key) }
        ?.run { key }
        ?: value
}

fun SharedPreferences.savePrefs(id: String, save: Boolean) {
    with(edit()) {
        putBoolean(id, save)
        commit()
    }
}

fun SharedPreferences.savePrefs(id: String, string: String) {
    with(edit()) {
        putString(id, string)
        commit()
    }
}

fun SharedPreferences.savePrefs(id: String, count: Int) {
    with(edit()) {
        putInt(id, count)
        commit()
    }
}

fun SharedPreferences.clearPrefs(id: String) {
    with(edit()) {
        remove(id)
        commit()
    }
}
