package br.com.git.issues.listing.domain

import br.com.git.base.mvp.BaseMvpPresenter
import br.com.git.base.mvp.BaseMvpView
import br.com.git.issues.listing.data.model.Issue
import br.com.git.issues.listing.data.model.IssueState
import io.reactivex.Single
import retrofit2.Response

interface MainActivityContract {

    interface IPresenter : BaseMvpPresenter {
        fun onRecyclerScrolledToBottom(pos: Int, numItems: Int)
        fun getIssues(page: Int = 1, state: String = IssueState.ALL.value)
    }

    interface IView : BaseMvpView {
        fun showIssueList(issueList: List<Issue>)
        fun showRequestError()
        fun initRecyclerView()
        fun showShimmerEffect()
    }

    interface IInteractor {
        fun getIssueList(page: Int, state: String): Single<Pair<Int, List<Issue>>>
    }

    interface IRepository {
        fun getIssueList(page: Int, state: String): Single<Response<List<Issue>>>
    }
}