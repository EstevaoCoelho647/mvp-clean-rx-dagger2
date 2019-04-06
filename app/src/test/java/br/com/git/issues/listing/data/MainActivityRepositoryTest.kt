package br.com.git.issues.listing.data

import br.com.git.issues.issue.presentation.issueMocked
import br.com.git.issues.listing.data.model.IssueState
import br.com.git.issues.listing.data.service.MainActivityService
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

class MainActivityRepositoryTest {
    private var service = mock<MainActivityService>()

    private lateinit var repository: MainActivityRepository

    @Before
    fun setUp() {
        repository = MainActivityRepository(service)
    }

    @Test
    fun getIssueList() {
        // mock
        val response = retrofit2.Response.success(listOf(issueMocked))
        Mockito.`when`(service.getIssueList(IssueState.ALL.value, 1)).thenReturn(Single.just(response))

        // call method and test
        repository.getIssueList(1, IssueState.ALL.value)
            .test()
            .assertComplete()
    }
}