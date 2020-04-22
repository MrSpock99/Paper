package itis.ru.paper.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import itis.ru.paper.ui.main.MainViewModel
import itis.ru.paper.ui.restaurant_list.RestaurantListViewModel
import itis.ru.paper.utils.ViewModelFactory
import itis.ru.scivi.ui.login.LoginViewModel
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun viewModelModule() = Kodein.Module(name = "viewModelModule") {
    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(
            kodein.direct
        )
    }

    bind<ViewModel>(tag = MainViewModel::class.java.simpleName) with provider {
        MainViewModel(firebaseAuth = instance())
    }
    bind<ViewModel>(tag = LoginViewModel::class.java.simpleName) with provider {
        LoginViewModel(loginInteractor = instance(), profileInteractor = instance())
    }
    bind<ViewModel>(tag = RestaurantListViewModel::class.java.simpleName) with provider {
        RestaurantListViewModel(interactor = instance())
    }
}