package itis.ru.paper.ui.main

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import itis.ru.paper.ui.base.BaseViewModel
import itis.ru.paper.utils.Response

class MainViewModel constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseViewModel() {

    val isLoginedLiveData = MutableLiveData<Response<Boolean>>()

    fun checkIsLogined() {
        if (firebaseAuth.currentUser != null) {
            isLoginedLiveData.value = Response.success(true)
        } else {
            isLoginedLiveData.value = Response.success(false)
        }
    }
}
