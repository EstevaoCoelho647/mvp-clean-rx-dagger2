package br.com.git.base.mvp

import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


open class BaseActivity @Inject constructor() : BaseMvpView, DaggerAppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Methods if we want display a default loading
    override fun showLoading() {}

    override fun hideLoading() {}

}