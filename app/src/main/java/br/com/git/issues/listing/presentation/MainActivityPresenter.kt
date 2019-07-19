package br.com.git.issues.listing.presentation

import br.com.git.base.isSuccessCode
import br.com.git.issues.listing.data.model.IssueState
import br.com.git.issues.listing.domain.MainActivityContract
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class MainActivityPresenter @Inject constructor(
    private val view: MainActivityContract.IView,
    private val interactor: MainActivityContract.IInteractor,
    @Named("MainScheduler") private val mainScheduler: Scheduler,
    @Named("IOScheduler") private val ioScheduler: Scheduler
) : MainActivityContract.IPresenter {

    private val compositeDisposable = CompositeDisposable()
    private var issuesPage = 1
    private var requesting = false
    private var issuesFilterState = IssueState.ALL.name

    companion object {
        const val ISSUE_LIST_NUMBER_TO_LOAD = 3
    }

    override fun attachView() {
        view.initRecyclerView()
        getIssues(issuesPage)
    }

    override fun getIssues(page: Int, state: String) {
        compositeDisposable.add(
            interactor.getIssueList(page, state)
                .observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .doOnSubscribe {
                    requesting = true
                    view.showLoading()
                }
                .doFinally { view.hideLoading() }
                .subscribe({
                    requesting = false
                    if (it.first.isSuccessCode()) {
                        view.showIssueList(it.second)
                    } else {
                        view.showRequestError()
                    }
                }, {
                    requesting = false
                    it.printStackTrace()
                    view.showRequestError()
                })
        )
    }

    override fun onRecyclerScrolledToBottom(pos: Int, numItems: Int) {
        if (pos >= numItems - ISSUE_LIST_NUMBER_TO_LOAD && !requesting) {
            getIssues(++issuesPage)
        }
    }

    override fun detachView() {
        compositeDisposable.dispose()
    }
}

