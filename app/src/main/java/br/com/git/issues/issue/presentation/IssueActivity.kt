package br.com.git.issues.issue.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import br.com.git.base.launchUrl
import br.com.git.base.mvp.BaseActivity
import br.com.git.base.toShowDateFormat
import br.com.git.issues.R
import br.com.git.issues.issue.domain.IssueActivityContract
import br.com.git.issues.listing.data.model.Issue
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_issue_view.*
import javax.inject.Inject

/*
 * IssueActivity: activity that show issue details
 */

class IssueActivity : BaseActivity(), IssueActivityContract.IView {

    companion object {
        private const val BUNDLE_ISSUE_ITEM = "bundle_issue"

        fun newInstance(context: Context, issue: Issue): Intent =
            Intent(context, IssueActivity::class.java)
                .putExtra(BUNDLE_ISSUE_ITEM, issue)
    }

    @Inject
    lateinit var presenter: IssueActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_view)

        val issueItem = intent?.extras?.getParcelable<Issue>(IssueActivity.BUNDLE_ISSUE_ITEM)

        presenter.attachView(issueItem)

        buttonIssueActivityViewMore.setOnClickListener {
            presenter.onButtonIssueClicked(issueItem)
        }
    }

    /*
     * Setup toolbar back button
     * action of button listener is in BaseActivity()
     */
    override fun setupToolbarBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun setupIssueDataOnView(issue: Issue) {
        //setting issue data
        textViewIssueActivityIssueTitle.text = issue.title
        textViewIssueActivityIssueDescription.text = issue.body
        issueStatusViewIssueActivityIssueStatus.setStatus(issue.state.ordinal)
        textViewIssueActivityCreateDate.text =
            getString(R.string.creation_date_format, issue.createdAt.toShowDateFormat())

        //setting author data
        Picasso.get().load(issue.author.avatarUrl)
            .placeholder(R.drawable.user_avatar_placeholder)
            .error(R.drawable.user_avatar_placeholder)
            .into(imageViewIssueActivityUserAvatar)
        textViewIssueActivityUserNickname.text = issue.author.nickname
    }

    override fun launchWebView(issueUrl: String) {
        CustomTabsIntent.Builder().launchUrl(this, issueUrl)
    }
}

