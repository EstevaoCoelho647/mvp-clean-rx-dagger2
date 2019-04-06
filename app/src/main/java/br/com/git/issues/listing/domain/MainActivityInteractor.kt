package br.com.git.issues.listing.domain

import br.com.git.issues.listing.data.model.Issue
import io.reactivex.Single
import javax.inject.Inject

class MainActivityInteractor @Inject constructor(
    private val repository: MainActivityContract.IRepository
) : MainActivityContract.IInteractor {

    override fun getIssueList(page: Int, state: String): Single<Pair<Int, List<Issue>>> =
        repository.getIssueList(page, state)
            .map {
                Pair(it.code(), it.body()!!)
            }
}