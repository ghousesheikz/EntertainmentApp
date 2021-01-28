package com.entertainmentapp.view.ui.registration.ui.register

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.entertainmentapp.R
import com.entertainmentapp.view.ui.registration.data.LoginRepository
import com.entertainmentapp.view.ui.registration.data.Result
import com.entertainmentapp.view.ui.registration.data.model.RegistrationPojo
import com.entertainmentapp.view.ui.registration.data.model.RegistrationResponsePojo


class RegisterViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<RegisterFormState>()
    val loginFormState: LiveData<RegisterFormState> = _loginForm

    private val _loginResult = MutableLiveData<RegisterResult>()
    val loginResult: LiveData<RegisterResult> = _loginResult

    fun login(userPojo : RegistrationPojo) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(userPojo)

        if (result is Result.Success) {
            _loginResult.value =
                RegisterResult(success = RegistrationResponsePojo(result.data!!))
        } else {
            _loginResult.value = RegisterResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(
      userPojo :RegistrationPojo
    ) {
        if (TextUtils.isEmpty(userPojo.FirstName)) {
            _loginForm.value = RegisterFormState(firstNameError = R.string.first_name_error)
        } else if (TextUtils.isEmpty(userPojo.LastName)) {
            _loginForm.value = RegisterFormState(lastNameError = R.string.last_name_error)
        } else if (!isUserNameValid(userPojo.Email)) {
            _loginForm.value = RegisterFormState(emailError = R.string.invalid_username)
        } else if (!isMobileNumberValid(userPojo.Mobile)) {
            _loginForm.value = RegisterFormState(mobileNumberError = R.string.invalid_mobilenumber)
        } else if (!isPasswordValid(userPojo.Password)) {
            _loginForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (TextUtils.isEmpty(userPojo.Pincode)) {
            _loginForm.value = RegisterFormState(pincodeError = R.string.please_enter_pincode)
        } else if (TextUtils.isEmpty(userPojo.VehicleNumber)) {
            _loginForm.value = RegisterFormState(vehicleNumberError = R.string.please_enter_vehiclenumber)
        } else {
            _loginForm.value = RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String?): Boolean {
        return if (username!!.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username!!.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String?): Boolean {
        return password!!.length > 5
    }

    // A placeholder password validation check
    private fun isMobileNumberValid(mobileNumber: String?): Boolean {
        return mobileNumber!!.length < 10
    }
}