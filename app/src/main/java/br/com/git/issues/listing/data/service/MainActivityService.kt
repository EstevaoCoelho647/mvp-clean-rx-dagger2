package br.com.git.issues.listing.data.service

import br.com.git.issues.listing.data.model.Issue
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainActivityService {

    @GET("repos/JetBrains/kotlin/issues")
    fun getIssueList(@Query("state") state: String, @Query("page") page: Int): Single<Response<List<Issue>>>
}