package com.example.faunaonboarding.util

import android.content.Context
import com.example.faunaonboarding.R

//TODO Use this?
fun translateAnimalIdentificationNumber(animalType: AnimalType, context: Context): String {
    return when (animalType) {
        AnimalType.DOG -> context.getString(R.string.animal_type_dog_identification_number)
        AnimalType.CAT -> context.getString(R.string.animal_type_cat_identification_number)
        else -> context.getString(R.string.animal_type_rodent_identification_number)
    }
}

fun translateAnimalType(animalType: AnimalType, context: Context): String {
    return when (animalType) {
        AnimalType.DOG -> context.getString(R.string.dog)
        AnimalType.CAT -> context.getString(R.string.cat)
        else -> context.getString(R.string.rodent)
    }
}

//fun String?.formatDateOfBirth(context: Context): String? {
//    return this?.let {
//        getAge(
//            it,
//            context.getString(R.string.years),
//            context.getString(R.string.months),
//            context.getString(R.string.days)
//        )
//    }
//}

fun AnimalType.translationResource(): Int {
    return when (this) {
        AnimalType.DOG -> R.string.dog
        AnimalType.CAT -> R.string.cat
        else -> R.string.rodent
    }
}

fun AnimalType.drawable(): Int {
    return when (this) {
        AnimalType.DOG -> R.drawable.dog_head
        else -> R.drawable.cat_head
    }
}