package itis.ru.paper.di

import itis.ru.paper.repository.RestaurantRepository
import itis.ru.paper.repository.RestaurantRepositoryImpl
import itis.ru.paper.repository.UserRepository
import itis.ru.paper.repository.UserRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun repositoryModule() = Kodein.Module("repositoryModule") {
    bind<UserRepository>() with singleton {
        UserRepositoryImpl(firebaseAuth = instance(), db = instance())
    }
    bind<RestaurantRepository>() with singleton {
        RestaurantRepositoryImpl(instance())
    }
}