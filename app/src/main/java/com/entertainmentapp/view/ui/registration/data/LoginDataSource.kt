package com.entertainmentapp.view.ui.registration.data

import androidx.lifecycle.MutableLiveData
import com.entertainmentapp.network.ServiceBuilder
import com.entertainmentapp.view.ui.registration.data.model.RegistrationPojo
import com.entertainmentapp.view.ui.registration.data.model.RegistrationResponsePojo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    var loginDetailsList: MutableLiveData<RegistrationResponsePojo> = MutableLiveData()

    fun login(userPojo: RegistrationPojo): Result<RegistrationResponsePojo> {
        try {
            val compositeDisposable = CompositeDisposable()
            compositeDisposable.add(
                ServiceBuilder.buildService()
                    .validateUser(
                        userPojo.FirstName,
                        userPojo.LastName,
                        userPojo.Mobile,
                        userPojo.Email,
                        userPojo.Password,
                        userPojo.Pincode,
                        userPojo.VehicleNumber,
                        userPojo.MacAddress
                    )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ response ->
                        loginDetailsList.value = response
                    }, {

                    })
            )
            return Result.Success(loginDetailsList.value)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}