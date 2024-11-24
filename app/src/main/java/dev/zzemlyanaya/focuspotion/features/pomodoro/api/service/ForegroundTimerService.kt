package dev.zzemlyanaya.focuspotion.features.pomodoro.api.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.content.res.Configuration
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import androidx.wear.ongoing.OngoingActivity
import androidx.wear.ongoing.Status
import dagger.hilt.android.AndroidEntryPoint
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.app.MainActivity
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.data.TimerRepository
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.model.toNotification
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ForegroundTimerService : LifecycleService(), TimerListener {

    @Inject
    lateinit var timerRepository: TimerRepository
    private lateinit var notificationManager: NotificationManager

    private var configurationChange = false
    private var serviceRunningInForeground = false
    private var isTimerActive = false
    private val localBinder = LocalBinder()
    private var timerJob: Job? = null

    override fun onCreate() {
        super.onCreate()

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        lifecycleScope.launch {
            timerRepository.isTimerActive.collect {
                if (isTimerActive != it) isTimerActive = it
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        if (intent?.getBooleanExtra(EXTRA_CANCEL_TIMER_FROM_NOTIFICATION, false) == true) {
            stopTimerAndStopService(stopService = true)
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        notForegroundService()

        return localBinder
    }

    override fun onRebind(intent: Intent) {
        super.onRebind(intent)
        notForegroundService()
    }

    override fun onUnbind(intent: Intent): Boolean {
        if (!configurationChange && isTimerActive) {
            val notification = generateNotification(getString(R.string.notification_text))
            startForeground(NOTIFICATION_ID, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC)
            serviceRunningInForeground = true
        }

        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        configurationChange = true
    }

    private fun notForegroundService() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        serviceRunningInForeground = false
        configurationChange = false
    }

    override fun start() {
        startService(Intent(applicationContext, ForegroundTimerService::class.java))

        lifecycleScope.launch {
            timerRepository.timerState.collect {
                if (serviceRunningInForeground) {
                    notificationManager.notify(NOTIFICATION_ID, generateNotification(it.toNotification()))
                }
            }
        }

        timerJob = lifecycleScope.launch { timerRepository.start() }
    }

    override fun pause() {
        timerRepository.pause()
    }

    override fun stop() {
        stopTimerAndStopService(stopService = false)
    }

    private fun stopTimerAndStopService(stopService: Boolean) {
        timerJob?.cancel()

        lifecycleScope.launch {
            timerRepository.stop()
            if (stopService) stopSelf()
        }
    }

    private fun generateNotification(mainText: String): Notification {
        val titleText = getString(R.string.notification_title)
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            titleText,
            NotificationManager.IMPORTANCE_DEFAULT,
        )
        notificationManager.createNotificationChannel(notificationChannel)

        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText(mainText)
            .setBigContentTitle(titleText)

        val launchActivityIntent = Intent(this, MainActivity::class.java)

        val cancelIntent = Intent(this, ForegroundTimerService::class.java)
        cancelIntent.putExtra(EXTRA_CANCEL_TIMER_FROM_NOTIFICATION, true)

        val servicePendingIntent = PendingIntent.getService(
            this,
            0,
            cancelIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val activityPendingIntent = PendingIntent.getActivity(
            this,
            0,
            launchActivityIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val notificationCompatBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)

        val notificationBuilder = notificationCompatBuilder
            .setStyle(bigTextStyle)
            .setContentTitle(titleText)
            .setContentText(mainText)
            .setSmallIcon(R.mipmap.ic_appicon)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_PROGRESS)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(
                R.drawable.ic_potion,
                getString(R.string.launch_app),
                activityPendingIntent,
            )
            .addAction(
                R.drawable.ic_stop,
                getString(R.string.stop),
                servicePendingIntent,
            )

        val ongoingActivityStatus = Status.Builder()
            .addTemplate(mainText)
            .build()

        val ongoingActivity =
            OngoingActivity.Builder(applicationContext, NOTIFICATION_ID, notificationBuilder)
                .setAnimatedIcon(R.drawable.animated_potion)
                .setStaticIcon(R.drawable.ic_potion)
                .setTouchIntent(activityPendingIntent)
                .setStatus(ongoingActivityStatus)
                .build()

        ongoingActivity.apply(applicationContext)

        return notificationBuilder.build()
    }

    inner class LocalBinder : Binder() {
        internal val service: ForegroundTimerService
            get() = this@ForegroundTimerService
    }

    companion object {
        private const val EXTRA_CANCEL_TIMER_FROM_NOTIFICATION = "EXTRA_CANCEL_TIMER_FROM_NOTIFICATION"

        private const val NOTIFICATION_ID = 15_617 // = 25^3-8
        private const val NOTIFICATION_CHANNEL_ID = "focus_potion_channel"
    }
}