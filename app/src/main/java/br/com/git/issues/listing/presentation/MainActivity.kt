package br.com.git.issues.listing.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.git.base.mvp.BaseActivity
import br.com.git.issues.R
import br.com.git.issues.issue.presentation.IssueActivity
import br.com.git.issues.listing.data.model.Issue
import br.com.git.issues.listing.domain.MainActivityContract
import br.com.git.issues.listing.presentation.adapter.IssueListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/*
 * MainActivity: activity that show list of issues
 */

class MainActivity : BaseActivity(), MainActivityContract.IView {

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var presenter: MainActivityPresenter

    private lateinit var issueListAdapter: IssueListAdapter

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView()
    }

    override fun initRecyclerView() {
        issueListAdapter = IssueListAdapter()
        recyclerViewMainActivityIssues.adapter = issueListAdapter
        val dividerItemDecoration = DividerItemDecoration(
            this, DividerItemDecoration.VERTICAL
        )
        recyclerViewMainActivityIssues.addItemDecoration(dividerItemDecoration)
        val linearLayoutManager = LinearLayoutManager(this)

        recyclerViewMainActivityIssues.layoutManager = linearLayoutManager

        recyclerViewMainActivityIssues.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val pos = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val numItems = issueListAdapter.itemCount
                presenter.onRecyclerScrolledToBottom(pos, numItems)
            }
        })

        compositeDisposable.add(issueListAdapter.onIssueClicked()
            .subscribe {
                startActivity(IssueActivity.newInstance(this, it))
            })
    }

    override fun showShimmerEffect() {
        shimmerLayoutMainActivityPlaceHolder.startShimmerAnimation()
    }

    override fun hideLoading() {
        shimmerLayoutMainActivityPlaceHolder.stopShimmerAnimation()
        shimmerLayoutMainActivityPlaceHolder.visibility = View.GONE
    }

    override fun showIssueList(issueList: List<Issue>) {
        textViewMainActivityRequestMessage.visibility = View.GONE
        issueListAdapter.setIssueList(issueList)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun showRequestError() {
        textViewMainActivityRequestMessage.visibility = View.VISIBLE
    }
}
