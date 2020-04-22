package itis.ru.paper.repository

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import itis.ru.paper.Const
import itis.ru.paper.model.RemoteRestaurant

class RestaurantRepositoryImpl(private val db: FirebaseFirestore) : RestaurantRepository {
    override fun getRestaurantList(): Observable<MutableList<RemoteRestaurant>> {
        return Observable.create { emitter ->
            db.collection(Const.RESTAURANTS)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.toObjects(RemoteRestaurant::class.java)
                            ?.let { emitter.onNext(it) }
                    } else {
                        emitter.onError(
                            task.exception ?: java.lang.Exception("error getting all restaurants")
                        )
                    }
                }
        }
    }
}
