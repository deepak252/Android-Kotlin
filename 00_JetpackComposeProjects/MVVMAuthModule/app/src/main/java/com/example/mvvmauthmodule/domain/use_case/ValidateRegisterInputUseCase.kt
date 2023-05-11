package com.example.mvvmauthmodule.domain.use_case

import com.example.mvvmauthmodule.domain.model.RegisterInputValidationType
import com.example.mvvmauthmodule.util.containsAlphabet
import com.example.mvvmauthmodule.util.containsNumber


class ValidateRegisterInputUseCase {
    operator fun invoke(email : String, password : String, confirmPassword : String) : RegisterInputValidationType{
        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            return RegisterInputValidationType.EmptyField
        }
        if("@" !in email){
            return RegisterInputValidationType.NoEmail
        }
        if(password!= confirmPassword){
            return RegisterInputValidationType.PasswordNotMatch
        }
        if(password.count() < 8){
            return RegisterInputValidationType.PasswordTooShort
        }
        if(!password.containsNumber()){
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if(!password.containsAlphabet()){
            return RegisterInputValidationType.PasswordCharMissing
        }
        return RegisterInputValidationType.Valid
    }
}