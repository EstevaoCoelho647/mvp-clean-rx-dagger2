package br.com.git.issues.issue.presentation

import br.com.git.issues.issue.domain.IssueActivityContract
import br.com.git.issues.listing.data.model.Issue
import javax.inject.Inject

class IssueActivityPresenter @Inject constructor(
    private val view: IssueActivityContract.IView
) : IssueActivityContract.IPresenter {

    override fun attachView(issue: Issue?) {
        issue?.let {
            view.setupToolbarBackButton()
            view.setupIssueDataOnView(it)
        }
    }

    override fun onButtonIssueClicked(issueUrl: Issue?) {
        issueUrl?.let {
            view.launchWebView(it.issueUrl)
        }
    }

    override fun detachView() {}
}