package com.stevdzo.accesscontrolapp.constants

class Constants {
    companion object {
        const val STRING_SEQUENCE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        const val QR_CODE_LENGTH = 10
        const val DATE_FORMAT = "dd/MM/yyyy HH:mm"
        const val TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss"
        const val EMAIL_SUBJECT = "Personalized QR Code"
        const val EMAIL_BODY = ", here is your personalized QR code!"
        const val AUTHORITY_PROVIDER = "com.stevdzo.accesscontrolapp.fileprovider"
        const val NAME_SURNAME_REGEX= "[a-zA-Z]+"
        const val EMAIL_REGEX = "^[A-Za-z\\d._%+-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,}\$"
        const val ADMIN_USERNAME = "admin"
        const val ADMIN_PASSWORD = "123123"
    }
}