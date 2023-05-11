package com.example.mvvmauthmodule.domain.model

enum class RegisterInputValidationType{
    EmptyField,
    NoEmail,
    PasswordTooShort,
    PasswordNumberMissing,
    PasswordCharMissing,
    PasswordNotMatch,
    Valid
}

