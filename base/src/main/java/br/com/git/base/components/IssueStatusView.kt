package br.com.git.base.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.git.base.R
import kotlinx.android.synthetic.main.issue_status_view.view.*

/*
 * Class of IssueStatusView component.
 * For now we have only one attribute (status) that can be
 * closed or open. But in future if necessary we can stylist
 * all attributes like text, color, background line color,
 * textColor etc...
 */

class IssueStatusView constructor(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    companion object {
        const val CLOSED = 0
        const val OPEN = 1
    }

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.IssueStatusView, 0, 0
        )
        val issueStatus = a.getInt(R.styleable.IssueStatusView_status, OPEN)
        a.recycle()

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.issue_status_view, this, true)

        setStatus(issueStatus)
    }

    fun setStatus(issueStatus: Int) {
        when (issueStatus) {
            OPEN ->
                setStatusOpen()
            CLOSED ->
                setStatusClosed()
        }
    }

    private fun setStatusClosed() {
        textViewIssueStatus.text = context.getString(R.string.issue_state_closed)
        textViewIssueStatus.setTextColor(ContextCompat.getColor(context, R.color.closedIssueColor))
        containerIssueStatus.background =
            ContextCompat.getDrawable(context, R.drawable.oval_tag_closed_issue_background)
    }

    private fun setStatusOpen() {
        textViewIssueStatus.text = context.getString(R.string.issue_state_open)
        textViewIssueStatus.setTextColor(ContextCompat.getColor(context, R.color.openIssueColor))
        containerIssueStatus.background = ContextCompat.getDrawable(context, R.drawable.oval_tag_open_issue_background)
    }
}