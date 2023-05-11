package com.example.mvvmauthmodule.util

fun String.containsNumber(): Boolean{
    val regex = Regex(".*\\d+.*")
    return regex.matches(this)
}

fun String.containsAlphabet(): Boolean{
    val regex = Regex(".*[a-zA-Z]+.*")
    return regex.matches(this)
}