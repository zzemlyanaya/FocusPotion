package dev.zzemlyanaya.focuspotion.app

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.service.ForegroundTimerService
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationRouter: NavigationRouter

    private var foregroundOnlyServiceBound = false
    private var foregroundService: ForegroundTimerService? = null
    private val foregroundServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as ForegroundTimerService.LocalBinder
            foregroundService = binder.service
            foregroundOnlyServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            foregroundService = null
            foregroundOnlyServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent { FocusPotionApp(navigationRouter, { foregroundService }) }
    }

    override fun onStart() {
        super.onStart()

        val serviceIntent = Intent(this, ForegroundTimerService::class.java)
        bindService(serviceIntent, foregroundServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        if (foregroundOnlyServiceBound) {
            unbindService(foregroundServiceConnection)
            foregroundOnlyServiceBound = false
        }
        super.onStop()
    }

}