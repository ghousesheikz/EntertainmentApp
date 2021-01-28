package com.entertainmentapp.view.ui.registration.ui.register

import com.entertainmentapp.view.ui.registration.data.model.RegistrationResponsePojo

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: RegistrationResponsePojo? = null,
    val error: Int? = null
)