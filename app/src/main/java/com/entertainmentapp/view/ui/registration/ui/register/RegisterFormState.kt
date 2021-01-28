package com.entertainmentapp.view.ui.registration.ui.register

/**
 * Data validation state of the login form.
 */
data class RegisterFormState(
    val firstNameError: Int? = null,
    val lastNameError: Int? = null,
    val mobileNumberError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val pincodeError: Int? = null,
    val vehicleNumberError: Int? = null,
    val macaddressError: Int? = null,
    val isDataValid: Boolean = false
)