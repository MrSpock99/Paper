package itis.ru.paper.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.firebase.FirebaseApp
import itis.ru.paper.MyApp
import itis.ru.paper.R
import itis.ru.paper.dialogs.ProgressDialog
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein =
        MyApp.app.kodein

    private val viewModeFactory: ViewModelProvider.Factory by instance()
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModeFactory).get(MainViewModel::class.java)
    }

    val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.checkIsLogined()
        observeIsLoginedLiveData()
    }

    fun showLoading(show: Boolean) {
        val dialog = ProgressDialog.newInstance()
        if (show) {
            dialog.show(supportFragmentManager, null)
        } else {
            if (dialog.isVisible)
                dialog.dismiss()
        }
    }

    private fun observeIsLoginedLiveData() =
        viewModel.isLoginedLiveData.observe(this, Observer { response ->
            if (response?.data != null) {
                if (response.data) {
                    navController.navigate(R.id.restaurantListFragment)
                } else {
                    navController.navigate(R.id.loginFragment)
                }
            }
        })

}