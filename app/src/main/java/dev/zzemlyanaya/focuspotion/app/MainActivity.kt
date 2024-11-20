package dev.zzemlyanaya.focuspotion.app

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationRouter: NavigationRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent { FocusPotionApp(navigationRouter) }
    }
}