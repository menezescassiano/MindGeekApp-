package com.cassiano.mindgeekapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import com.cassiano.mindgeekapp.internal.Constants
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationTest::class, sdk = [Build.VERSION_CODES.O_MR1])
class SharedPrefsTest {
    private lateinit var sharedPrefs: SharedPreferences
    private var context = Mockito.mock(Context::class.java)

    @Before
    fun setup() {
        sharedPrefs = Mockito.mock(SharedPreferences::class.java)
        context = Mockito.mock(Context::class.java)
        Mockito.`when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs)
    }


    @Test
    @Throws(Exception::class)
    fun `checks shared preferences store value`() {
        val my = sharedPrefs.getBoolean(Constants.SHARED_PREF_LOCKED, true)
        assertEquals(my, false)
    }
}