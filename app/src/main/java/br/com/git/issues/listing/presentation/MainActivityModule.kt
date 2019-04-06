package br.com.git.issues.listing.presentation

import br.com.git.issues.listing.data.MainActivityRepository
import br.com.git.issues.listing.data.service.MainActivityService
import br.com.git.issues.listing.domain.MainActivityContract
import br.com.git.issues.listing.domain.MainActivityInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainActivityModule {

    @Provides
    fun providePresenter(presenter: MainActivityPresenter): MainActivityContract.IPresenter = presenter

    @Provides
    fun provideInteractor(interactor: MainActivityInteractor): MainActivityContract.IInteractor = interactor

    @Provides
    fun provideView(activity: MainActivity): MainActivityContract.IView = activity

    @Provides
    fun provideService(retrofit: Retrofit): MainActivityService =
        retrofit.create(MainActivityService::class.java)

    @Provides
    fun provideRepository(repository: MainActivityRepository): MainActivityContract.IRepository = repository
}