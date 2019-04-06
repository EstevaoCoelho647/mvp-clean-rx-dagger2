package br.com.git.issues.listing.domain

import br.com.git.issues.issue.presentation.issueMocked
import br.com.git.issues.listing.data.model.IssueState
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.junit.Test

import org.junit.Before
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainActivityInteractorTest {
    private var repository = mock<MainActivityContract.IRepository>()

    lateinit var interactor: MainActivityInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        interactor = MainActivityInteractor(repository)
    }

    @Test
    fun getIssueList_success() {
        //mock
        val mockedResponse = listOf(issueMocked)
        val response = retrofit2.Response.success(mockedResponse)
        Mockito.`when`(repository.getIssueList(1, IssueState.ALL.value)).thenReturn(Single.just(response))

        //call method and test
        interactor.getIssueList(1, IssueState.ALL.value)
            .test()
            .assertResult(Pair(200, mockedResponse))
    }
}