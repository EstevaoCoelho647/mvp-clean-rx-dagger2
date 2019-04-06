package br.com.git.issues.di

import br.com.git.issues.issue.presentation.IssueActivity
import br.com.git.issues.issue.presentation.IssueActivityModule
import br.com.git.issues.listing.presentation.MainActivity
import br.com.git.issues.listing.presentation.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [IssueActivityModule::class])
    abstract fun bindIssueActivity(): IssueActivity
}