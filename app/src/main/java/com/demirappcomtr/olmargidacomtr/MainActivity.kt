package com.demirappcomtr.olmargidacomtr

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
//import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private val REQUEST_CODE_NOTIFICATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

      //  setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Android 13 ve üzeri cihazlarda bildirim izni kontrolü
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE_NOTIFICATION)
            }
        }

        // WebView ayarları
        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient() // Dış tarayıcıya gitmeyi engeller
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true // JavaScript desteğini açıyoruz
        webSettings.domStorageEnabled = true // DOM storage desteği
        webSettings.useWideViewPort = true // Geniş ekran desteği
        webSettings.loadWithOverviewMode = true // Ekranı sığdırma
      //  webSettings.builtInZoomControls = true // Yakınlaştırma kontrolleri
        webView.loadUrl("https://www.olmargida.com.tr") // Yüklemek istediğiniz URL
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // İzin isteğinin sonucunu dinleyen metot
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_NOTIFICATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Kullanıcı izni verdi, bildirim gönderme işlemlerini burada yapabilirsiniz.
            } else {
                // Kullanıcı izni reddetti, bildirim izni olmadan devam edebilir ya da bir uyarı gösterebilirsiniz.
            }
        }
    }
}
