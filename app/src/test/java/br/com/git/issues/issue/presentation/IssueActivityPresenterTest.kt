package br.com.git.issues.issue.presentation

import br.com.git.issues.issue.domain.IssueActivityContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class IssueActivityPresenterTest {
    private val view = Mockito.mock(IssueActivityContract.IView::class.java)
    private lateinit var presenter: IssueActivityContract.IPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = IssueActivityPresenter(view)
    }

    @Test
    fun attachView_withValidIssue() {
        presenter.attachView(issueMocked)

        Mockito.verify(view).setupToolbarBackButton()
        Mockito.verify(view).setupIssueDataOnView(issueMocked)
    }

    @Test
    fun attachView_withInvalidIssue() {
        presenter.attachView(null)

        Mockito.verify(view, Mockito.times(0)).setupToolbarBackButton()
        Mockito.verify(view, Mockito.times(0)).setupIssueDataOnView(issueMocked)
    }

    @Test
    fun onButtonIssueClicked() {
    }

    @Test
    fun detachView() {
    }
}