package com.example.shelter.presentation.onBoarding.utils

import com.example.shelter.utils.checkEmailAndPassword
import com.example.shelter.presentation.onBoarding.registration.model.Human
import com.example.shelter.presentation.onBoarding.registration.model.Organisation
import java.util.regex.Pattern

val pattern: Pattern = Pattern.compile("^[+а-яА-Яa-zA-Z]+$")


fun checkOrganisation(organisation: Organisation): Boolean {
    return checkEmailAndPassword(organisation.email, organisation.password) &&
            checkNameOrganisation(organisation.name) &&
            checkCity(organisation.city) &&
            checkPhone(organisation.phone)
}

fun checkHuman(human: Human): Boolean {
    return checkEmailAndPassword(human.email, human.password) &&
            checkFirstName(human.firstName) &&
            checkLastName(human.lastName) &&
            checkPhone(human.phone) &&
            checkCity(human.city)
}

// Checking human and organisation parameters

fun checkCity(city: String?): Boolean {
    return city != null && pattern.matcher(city).find()
}

fun checkCountry(country: String?): Boolean {
    return country != null && pattern.matcher(country).find()
}

fun checkPhone(phone: String?): Boolean {
    val phonePattern: Pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$")
    return phone != null && phonePattern.matcher(phone).find()
}

// Checking organisation's parameters

fun checkNameOrganisation(name: String?): Boolean {
    val patternName = Pattern.compile("^[+а-яА-Яa-zA-Z]+$")
    return name != null && patternName.matcher(name).find()
}

fun checkHome(name: String?): Boolean {
    val patternHome = Pattern.compile("^\\d[+а-яА-Я]+$")
    return name != null && patternHome.matcher(name).find()
}

fun checkSite(site: String?): Boolean {
    val patternSite = Pattern.compile("^((ftp|http|https):\\/\\/)?(www\\.)?([A-Za-zА-Яа-я0-9]{1}[A-Za-zА-Яа-я0-9\\-]*\\.?)*\\.{1}[A-Za-zА-Яа-я0-9-]{2,8}(\\/([\\w#!:.?+=&%@!\\-\\/])*)?")
    return site != null
}

// Checking people parameters

fun checkFirstName(firstName: String?): Boolean {
    return firstName != null && pattern.matcher(firstName).find()
}

fun checkLastName(lastName: String?): Boolean {
    return lastName != null && pattern.matcher(lastName).find()
}

fun checkPatronymic(patronymic: String?): Boolean {
    TODO()
    return patronymic != null && pattern.matcher(patronymic).find()
}


fun checkBirthday(birthday: Long?): Boolean{
    TODO()
}