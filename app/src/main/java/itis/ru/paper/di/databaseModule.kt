package itis.ru.paper.di

import com.google.firebase.firestore.FirebaseFirestore
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

fun databaseModule() = Kodein.Module("databaseModule") {
    bind<FirebaseFirestore>() with singleton {
        FirebaseFirestore.getInstance()
    }
}