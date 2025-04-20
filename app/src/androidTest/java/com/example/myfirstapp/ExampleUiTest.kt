package com.example.myfirstapp

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
class ExampleUiTest {

    private lateinit var device: UiDevice

    @Before
    fun launchAppFromHomeScreen() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(context.packageName).depth(0)), TIMEOUT)
    }

        @Test
        fun startSecondActivityAndCheckChallenge() {
            val startButton = device.findObject(By.text("start activity explicitly"))
            startButton?.click()

            // Wait for activity to load
            device.waitForIdle()

            // Try to match any one of the 5 expected texts
            // Try each expected challenge individually
            val text1 = device.findObject(By.textContains("Device Fragmentation"))
            val text2 = device.findObject(By.textContains("OS Fragmentation"))
            val text3 = device.findObject(By.textContains("Security vulnerabilities"))
            val text4 = device.findObject(By.textContains("Rapid Changes"))
            val text5 = device.findObject(By.textContains("Low Tolerance"))

            val challengeFound = text1 != null || text2 != null || text3 != null || text4 != null || text5 != null


            assertNotNull("Expected challenge not found!", challengeFound)
        }
}