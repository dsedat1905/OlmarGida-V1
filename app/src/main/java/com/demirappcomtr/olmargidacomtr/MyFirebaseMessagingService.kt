package com.demirappcomtr.olmargidacomtr

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Gelen bildirimi burada işleyebilirsiniz
        Log.d("FCM", "Mesaj alındı: ${remoteMessage.data}")
        // Bildirimi gösterme gibi işlemler burada yapılabilir
        // Bildirim oluşturma
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Android 8 ve üzeri için Notification Channel oluşturulması gerekebilir
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("fcm_channel", "FCM Notifications", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // Bildirim içeriği
        val notification = NotificationCompat.Builder(this, "fcm_channel")
            .setContentTitle(remoteMessage.notification?.title)
            .setContentText(remoteMessage.notification?.body)
           // .setSmallIcon(R.drawable.ic_notification) // Küçük ikon
            .build()

        // Bildirimi gösterme
        notificationManager.notify(0, notification)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Yeni token: $token")
        // Yeni token'i sunucunuza gönderin
    }
}
