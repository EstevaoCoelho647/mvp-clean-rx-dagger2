package br.com.git.base

import android.annotation.SuppressLint
import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import java.text.SimpleDateFormat
import java.util.*

/*
 * If this file is getting too big
 * we can separate in other extensions files
 * such as "StatusCodeExtensions"
 */

//All kotlin extensions for launch intents
fun CustomTabsIntent.Builder.launchUrl(context: Context, url: String) {
    this.addDefaultShareMenuItem()
        .setToolbarColor(androidx.core.content.ContextCompat.getColor(context, R.color.primaryColor))
        .setShowTitle(true)
        .build()
        .launchUrl(context, android.net.Uri.parse(url))
}

//All kotlin extensions for Date
@SuppressLint("SimpleDateFormat")
fun Date.toShowDateFormat(): String =
    SimpleDateFormat("dd/MM/yyyy").format(this)

//All kotlin extensions for HTTP Code
fun Int.isSuccessCode() = this == 200
