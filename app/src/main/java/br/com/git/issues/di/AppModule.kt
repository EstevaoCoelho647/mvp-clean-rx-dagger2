package br.com.git.issues.di

import android.app.Application
import android.content.Context
import br.com.git.base.modules.NetworkModule
import br.com.git.issues.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named


@Module(includes = [(InnerAppModule::class), (NetworkModule::class)])
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: Application): Context
}

@Module
class InnerAppModule {
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Named("IOScheduler")
    fun provideIOScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("MainScheduler")
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}