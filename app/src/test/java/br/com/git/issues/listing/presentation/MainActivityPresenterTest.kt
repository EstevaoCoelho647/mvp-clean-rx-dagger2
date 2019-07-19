package br.com.git.issues.listing.presentation

import br.com.git.issues.issue.presentation.issueMocked
import br.com.git.issues.listing.data.model.IssueState
import br.com.git.issues.listing.domain.MainActivityContract
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainActivityPresenterTest {
    private val view = mock<MainActivityContract.IView>()
    private val interactor = mock<MainActivityContract.IInteractor>()

    private lateinit var presenter: MainActivityContract.IPresenter
    private var testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainActivityPresenter(view, interactor, testScheduler, testScheduler)
    }

    @Test
    fun attachView() {
        //Mock response
        val response = Single.just(Pair(200, listOf(issueMocked)))
        //Mock to not call real method now. Will be tested in next test
        Mockito.`when`(interactor.getIssueList(1, IssueState.ALL.value))
            .thenReturn(response)

        presenter.attachView()

        Mockito.verify(view).initRecyclerView()
        Mockito.verify(view).showShimmerEffect()
    }

    @Test
    fun getIssues_success() {
        //Mock object
        val issueObject = Pair(first = 200, second = listOf(issueMocked))
        //Mock response
        val response = Single.just(issueObject)
        Mockito.`when`(interactor.getIssueList(1, IssueState.ALL.value))
            .thenReturn(response)

        presenter.getIssues(1, IssueState.ALL.value)

        Mockito.verify(view).showLoading()
        testScheduler.triggerActions()

        Mockito.verify(view).showIssueList(issueObject.second)

        testScheduler.triggerActions()

        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getIssues_error() {
        //Mock object
        val issueObject = Pair(first = 400, second = listOf(issueMocked))
        //Mock response
        val response = Single.just(issueObject)

        Mockito.`when`(interactor.getIssueList(1, IssueState.ALL.value))
            .thenReturn(response)

        presenter.getIssues(1, IssueState.ALL.value)

        Mockito.verify(view).showLoading()
        testScheduler.triggerActions()

        Mockito.verify(view).showRequestError()

        testScheduler.triggerActions()

        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getIssues_Throwable() {
        //Mock to not call real method now. Will be tested in next test
        Mockito.`when`(interactor.getIssueList(1, IssueState.ALL.value))
            .thenReturn(Single.error(Exception("error")))

        presenter.getIssues(1, IssueState.ALL.value)

        Mockito.verify(view).showLoading()
        testScheduler.triggerActions()

        Mockito.verify(view).showRequestError()

        testScheduler.triggerActions()

        Mockito.verify(view).hideLoading()
    }

    @Test
    fun onRecyclerScrolledToBottom_notCall() {
        presenter.onRecyclerScrolledToBottom(0, 10)

        Mockito.verify(interactor, times(0)).getIssueList(1, IssueState.ALL.value)
    }

    @Test
    fun onRecyclerScrolledToBottom_callRequest() {
        //Mock response
        val response = Single.just(Pair(200, listOf(issueMocked)))
        //Mock to not call real method now. Will be tested in next test
        Mockito.`when`(interactor.getIssueList(2, IssueState.ALL.value))
            .thenReturn(response)

        presenter.onRecyclerScrolledToBottom(8, 10)

        Mockito.verify(interactor, times(1)).getIssueList(2, IssueState.ALL.value)
    }

    @Test
    fun detachView() {
        presenter.detachView()
    }
}