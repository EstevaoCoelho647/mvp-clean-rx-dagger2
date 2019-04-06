package br.com.git.issues.listing.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Issue(
    val number: Int,
    val title: String,
    val body: String,
    val state: IssueState,
    @Json(name = "user") val author: Author,
    @Json(name = "created_at") val createdAt: Date,
    @Json(name = "html_url") val issueUrl: String
) : Parcelable

@Parcelize
data class Author(
    @Json(name = "login") val nickname: String,
    @Json(name = "avatar_url") val avatarUrl: String
) : Parcelable

enum class IssueState constructor(val value: String) {
    @Json(name = "closed")
    CLOSED("closed"),
    @Json(name = "open")
    OPEN("open"),
    @Json(name = "all")
    ALL("all");
}
