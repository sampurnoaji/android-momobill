package io.android.momobill.util.extension

import java.util.regex.Pattern

private const val EMAIL_REGEX = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
private const val PHONE_REGEX = "^(^\\+62|62|^08)(\\d{3,4}-?){2}\\d{3,4}\$"

fun String.isValidEmail(): Boolean {
    val pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isValidPhone(): Boolean {
    val pattern = Pattern.compile(PHONE_REGEX, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}