package com.mckapp.loginscreenuicompose

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun isSmallScreenHeight() : Boolean {
    val conf = LocalConfiguration.current
    return conf.screenHeightDp <= 786
}

@Composable
fun rememberImeState(): State<Boolean> {
    // Klavye durumu için bir mutable state tanımlanıyor, başlangıçta false olarak ayarlanmış.
    val imeState = remember {
        mutableStateOf(false)
    }

    // Composable içindeki mevcut View'i almak için LocalView.current kullanılıyor.
    val view = LocalView.current

    // DisposableEffect kullanarak bir global layout listener'ı ekliyoruz.
    DisposableEffect(view) {
        // OnGlobalLayoutListener, global layout değişikliklerini dinlemek için kullanılır.
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            // Klavye durumunu kontrol etmek için ViewCompat.getRootWindowInsets(view) kullanılıyor.
            // isVisible(WindowInsetsCompat.Type.ime()) ile klavyenin açık olup olmadığı belirleniyor.
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true

            // İmeState'i güncelliyoruz, klavye durumuna göre.
            imeState.value = isKeyboardOpen
        }

        // Listener'ı ViewTreeObserver'a ekliyoruz.
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        // DisposableEffect, Composable durdurulduğunda temizlenmesi gereken kaynakları temizler.
        onDispose {
            // Listener'ı ViewTreeObserver'dan kaldırıyoruz.
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    // imeState'i döndürüyoruz, bu durum klavyenin açık olup olmadığını temsil eder.
    return imeState
}
