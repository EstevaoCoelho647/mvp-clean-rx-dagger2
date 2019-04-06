package br.com.git.issues

import android.content.Context
import androidx.multidex.MultiDex
import br.com.git.issues.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import dagger.android.support.DaggerApplication

class CustomApplication : DaggerApplication(), HasActivityInjector, HasBroadcastReceiverInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}