package br.com.git.issues.issue.presentation

import br.com.git.issues.listing.data.model.Author
import br.com.git.issues.listing.data.model.Issue
import br.com.git.issues.listing.data.model.IssueState
import java.util.*


val authorMocked = Author("author Nickname", "author avatar url")
val issueMocked = Issue(
    0, "issue Title", "issue Body",
    IssueState.CLOSED, authorMocked, Date(), "issue url mocked"
)
