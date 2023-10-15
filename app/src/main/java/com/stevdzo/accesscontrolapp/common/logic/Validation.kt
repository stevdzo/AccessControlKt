package com.stevdzo.accesscontrolapp.common.logic

import android.telephony.PhoneNumberUtils
import com.stevdzo.accesscontrolapp.constants.Constants

class Validation {

    companion object {

        private fun isFieldEmpty(field: String): Boolean {
            return field.trim().isEmpty()
        }

        fun isNameOrSurnameValid(field: String, onError: (Boolean) -> Unit): Boolean {
            if (field.trim().matches(Regex(Constants.NAME_SURNAME_REGEX)) && !isFieldEmpty(field)) {
                onError(false)
                return true
            }
            onError(true)
            return false
        }

        fun isEmailValid(field: String, onError: (Boolean) -> Unit): Boolean {
            if (field.trim().matches(Regex(Constants.EMAIL_REGEX)) && !isFieldEmpty(field)) {
                onError(false)
                return true
            }
            onError(true)
            return false
        }

        fun isPhoneValid(field: String, onError: (Boolean) -> Unit): Boolean {
            if (PhoneNumberUtils.isGlobalPhoneNumber(field.trim()) && !isFieldEmpty(field)) {
                onError(false)
                return true
            }
            onError(true)
            return false
        }
    }
}