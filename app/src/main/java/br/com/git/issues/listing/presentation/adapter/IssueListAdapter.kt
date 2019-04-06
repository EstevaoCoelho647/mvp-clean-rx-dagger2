package br.com.git.issues.listing.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.git.issues.R
import br.com.git.issues.listing.data.model.Issue
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.recycler_view_issue_item.view.*

class IssueListAdapter : RecyclerView.Adapter<IssueListAdapter.MyViewHolder>() {

    private val issueList = mutableListOf<Issue>()
    private val publish = PublishSubject.create<Issue>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.recycler_view_issue_item,
            viewGroup, false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = issueList.size

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(issueList[i])
    }

    fun onIssueClicked(): PublishSubject<Issue> = publish

    inner class MyViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                publish.onNext(issueList[layoutPosition])
            }
        }

        fun bind(issue: Issue) {
            itemView.textViewMainActivityIssueName.text = issue.title
            itemView.textViewMainActivityIssueNumber.text =
                itemView.context.getString(R.string.issue_number, issue.number)
            itemView.textViewMainActivityIssueDescription.text = issue.body
            itemView.issueStatusViewMainActivityIssueStatus.setStatus(issue.state.ordinal)
        }
    }

    fun setIssueList(issueList: List<Issue>) {
        val size = this.issueList.size
        this.issueList.addAll(issueList)
        notifyItemInserted(size)
    }
}

