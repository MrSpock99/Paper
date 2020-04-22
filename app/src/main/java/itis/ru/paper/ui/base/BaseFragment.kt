package itis.ru.paper.ui.base

import android.content.Context
import android.view.MenuItem
import androidx.fragment.app.Fragment
import itis.ru.paper.MyApp
import itis.ru.paper.ui.main.MainActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

open class BaseFragment : Fragment(), KodeinAware {
    lateinit var rootActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rootActivity = activity as MainActivity
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                rootActivity.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setArrowToolbarVisibility(show: Boolean) {
        rootActivity.supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    override val kodein: Kodein by lazy {
        (rootActivity.application as MyApp).kodein
    }
}
