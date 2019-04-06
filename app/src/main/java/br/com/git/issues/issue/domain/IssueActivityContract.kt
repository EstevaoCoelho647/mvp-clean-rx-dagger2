package br.com.git.issues.issue.domain

import br.com.git.base.mvp.BaseMvpPresenter
import br.com.git.base.mvp.BaseMvpView
import br.com.git.issues.listing.data.model.Issue

interface IssueActivityContract {

    interface IPresenter : BaseMvpPresenter {
        override fun attachView(){}
        fun attachView(issue: Issue?)
        fun onButtonIssueClicked(issueUrl: Issue?)
    }

    interface IView : BaseMvpView {
        fun setupToolbarBackButton()
        fun setupIssueDataOnView(issue: Issue)
        fun launchWebView(issueUrl: String)
    }
}