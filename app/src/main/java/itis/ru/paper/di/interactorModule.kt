package itis.ru.paper.di

import itis.ru.paper.interactor.RestaurantInteractor
import itis.ru.paper.interactors.LoginInteractor
import itis.ru.paper.interactors.ProfileInteractor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun interactorModule() = Kodein.Module("interactorModule") {
    bind<LoginInteractor>() with singleton {
        LoginInteractor(userRepository = instance())
    }
    bind<ProfileInteractor>() with singleton {
        ProfileInteractor(userRepository = instance())
    }
    bind<RestaurantInteractor>() with singleton {
        RestaurantInteractor(instance())
    }
}