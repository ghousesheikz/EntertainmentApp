package com.entertainmentapp.view.ui.registration.ui.register

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.entertainmentapp.BaseClass
import com.entertainmentapp.R
import com.entertainmentapp.utils.Helpers
import com.entertainmentapp.view.ui.OtpVerification.ui.OtpVerificationActivity
import com.entertainmentapp.view.ui.registration.data.model.RegistrationPojo
import com.entertainmentapp.view.ui.registration.data.model.RegistrationResponsePojo
import kotlinx.android.synthetic.main.activity_registration.*
import java.io.IOException
import java.util.*


class RegistrationActivity : BaseClass() {

    private lateinit var loginViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registration)
        initLocationFetching(this)

        loginViewModel = ViewModelProvider(this, RegisterViewModelFactory())
            .get(RegisterViewModel::class.java)

        loginViewModel.loginFormState.observe(this@RegistrationActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.firstNameError != null) {
                txt_firstname.error = getString(loginState.firstNameError)
            }
            if (loginState.passwordError != null) {
                txt_password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@RegistrationActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
        })

        txt_firstname.afterTextChanged {
            loginViewModel.loginDataChanged(
                RegistrationPojo(
                    txt_firstname.text.toString(),
                    txt_lastname.text.toString(),
                    txt_mobile.text.toString(),
                    txt_email.text.toString(),

                    txt_password.text.toString(),
                    txt_pincode.text.toString(),
                    txt_vehicleno.text.toString(),
                    Helpers.getMacAddr()
                )
            )
        }

        txt_password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    RegistrationPojo(
                        txt_firstname.text.toString(),
                        txt_lastname.text.toString(),
                        txt_mobile.text.toString(),
                        txt_email.text.toString(),

                        txt_password.text.toString(),
                        txt_pincode.text.toString(),
                        txt_vehicleno.text.toString(),
                        Helpers.getMacAddr()
                    )
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            RegistrationPojo(
                                txt_firstname.text.toString(),
                                txt_lastname.text.toString(),
                                txt_mobile.text.toString(),
                                txt_email.text.toString(),

                                txt_password.text.toString(),
                                txt_pincode.text.toString(),
                                txt_vehicleno.text.toString(),
                                Helpers.getMacAddr()
                            )
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(
                    RegistrationPojo(
                        txt_firstname.text.toString(),
                        txt_lastname.text.toString(),
                        txt_mobile.text.toString(),
                        txt_email.text.toString(),

                        txt_password.text.toString(),
                        txt_pincode.text.toString(),
                        txt_vehicleno.text.toString(),
                        Helpers.getMacAddr()
                    )
                )
            }
        }
    }

    private fun updateUiWithUser(model: RegistrationResponsePojo) {
        val welcome = getString(R.string.welcome)
        val displayName = model.UserId
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
        startActivity(Intent(this, OtpVerificationActivity::class.java))
        finish()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed(Runnable {
            getAddressFromLocation(mLatitude, mLongitude, this)
        }, 2000)

    }

    fun getAddressFromLocation(
        latitude: Double?, longitude: Double?,
        context: Context?
    ) {
        var address: Address? = null
        val thread: Thread = object : Thread() {
            override fun run() {
                val geocoder = Geocoder(context, Locale.getDefault())
                try {
                    val addressList = geocoder.getFromLocation(
                        latitude!!, longitude!!, 1
                    )
                    if (addressList != null && addressList.size > 0) {
                        address = addressList[0]

                    }
                } catch (e: IOException) {
                    Log.e("TAG", "Unable connect to Geocoder", e)
                } finally {
                    runOnUiThread {
                        txt_pincode.setText(address!!.postalCode)
                        txt_pincode.isEnabled = false
                    }

                }
            }
        }
        thread.start()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}