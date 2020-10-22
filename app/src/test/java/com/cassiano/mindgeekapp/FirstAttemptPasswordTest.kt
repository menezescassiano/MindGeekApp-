package com.cassiano.mindgeekapp

import android.os.Build
import com.cassiano.mindgeekapp.password.view.viewmodel.BaseViewModel.Companion.MAX_CHARACTERS
import com.cassiano.mindgeekapp.password.view.viewmodel.FirstAttemptPasswordViewModel
import io.mockk.clearAllMocks
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationTest::class, sdk = [Build.VERSION_CODES.O_MR1])
class FirstAttemptPasswordTest {

    private lateinit var viewModelMock: FirstAttemptPasswordViewModel

    @Before
    fun init() {
        clearAllMocks()
        viewModelMock = FirstAttemptPasswordViewModel()
    }

    @Test
    fun `checks something`() {
        viewModelMock.run {
            password.set("1111")
            enabled.set(true)
            Assert.assertTrue(password.get()?.length == MAX_CHARACTERS)
            Assert.assertTrue(enabled.get())
        }
    }
}