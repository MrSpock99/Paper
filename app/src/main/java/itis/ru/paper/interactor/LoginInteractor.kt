package itis.ru.paper.interactors

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import itis.ru.paper.repository.UserRepository

class LoginInteractor(
    private val userRepository: UserRepository
) {

    fun login(account: GoogleSignInAccount): Completable {
        return userRepository.login(account)
            .subscribeOn(Schedulers.io())
    }
}
