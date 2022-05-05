package com.example.centeralperk.util

object Validation {

    /** Email validation regex */
    private const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    /**
     * Email validation return true or false
     * @param email
     * @return boolean
     */
    fun emailValidation(email: String): Boolean {
        return email.matches(emailPattern.toRegex())
    }
}