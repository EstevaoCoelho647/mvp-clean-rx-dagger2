package br.com.git.issues.issue.presentation

import br.com.git.issues.issue.domain.IssueActivityContract
import dagger.Module
import dagger.Provides

@Module
class IssueActivityModule {

    @Provides
    fun providePresenter(presenter: IssueActivityPresenter): IssueActivityContract.IPresenter = presenter

    @Provides
    fun provideView(activity: IssueActivity): IssueActivityContract.IView = activity

}