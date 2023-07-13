package com.example.faunaonboarding.animalCreate

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.InputType
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.example.faunaonboarding.util.AnimalType
import com.example.faunaonboarding.util.Gender

data class AnimalCreate(
    val type: AnimalType,
    val name: String,
    val dateOfBirth: Input<String> = Input.absent(),
    val identificationNumber: Input<String> = Input.absent(),
    val race: Input<String> = Input.absent(),
    val sterilized: Input<Boolean> = Input.absent(),
    val color: Input<String> = Input.absent(),
    val gender: Input<Gender> = Input.absent(),
    val insurancePolicyNumber: Input<String> = Input.absent()
) : InputType {
    override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller.invoke { writer ->
        writer.writeString("type", this@AnimalCreate.type.toString())
        writer.writeString("name", this@AnimalCreate.name)
        if (this@AnimalCreate.dateOfBirth.defined) {
            writer.writeString("dateOfBirth", this@AnimalCreate.dateOfBirth.value)
        }
        if (this@AnimalCreate.identificationNumber.defined) {
            writer.writeString("identificationNumber", this@AnimalCreate.identificationNumber.value)
        }
        if (this@AnimalCreate.race.defined) {
            writer.writeString("race", this@AnimalCreate.race.value)
        }
        if (this@AnimalCreate.sterilized.defined) {
            writer.writeBoolean("sterilized", this@AnimalCreate.sterilized.value)
        }
        if (this@AnimalCreate.color.defined) {
            writer.writeString("color", this@AnimalCreate.color.value)
        }
        if (this@AnimalCreate.gender.defined) {
            writer.writeString("gender", this@AnimalCreate.gender.value?.toString())
        }
        if (this@AnimalCreate.insurancePolicyNumber.defined) {
            writer.writeString("insurancePolicyNumber", this@AnimalCreate.insurancePolicyNumber.value)
        }
    }
}