package br.com.git.issues.listing.data

import br.com.git.issues.listing.data.model.Issue
import br.com.git.issues.listing.data.service.MainActivityService
import br.com.git.issues.listing.domain.MainActivityContract
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val service: MainActivityService
) : MainActivityContract.IRepository {

    override fun getIssueList(page: Int, state: String): Single<Response<List<Issue>>> =
        service.getIssueList(state, page)
}
