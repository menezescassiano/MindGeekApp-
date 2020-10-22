package com.cassiano.mindgeekapp

import android.os.Build
import com.cassiano.mindgeekapp.home.view.viewmodel.SettingsViewModel
import io.mockk.clearAllMocks
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationTest::class, sdk = [Build.VERSION_CODES.O_MR1])
class SettingsUnitTest {

    private lateinit var viewModelMock: SettingsViewModel

    @Before
    fun init() {
        clearAllMocks()
        viewModelMock = SettingsViewModel()
    }

    @Test
    fun `checks if switch is checked`() {
        viewModelMock.run {
            isChecked.set(true)
            Assert.assertTrue(isChecked.get())
        }
    }
    @Test
    fun `checks if switch is not checked`() {
        viewModelMock.run {
            isChecked.set(false)
            Assert.assertFalse(isChecked.get())
        }
    }
}
