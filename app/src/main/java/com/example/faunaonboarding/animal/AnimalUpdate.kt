package com.example.faunaonboarding.animal

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.InputType
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.example.faunaonboarding.util.AnimalType
import com.example.faunaonboarding.util.CustomType
import com.example.faunaonboarding.util.Gender

data class AnimalUpdate(
    val id: String,
    val type: AnimalType,
    val name: Input<String> = Input.absent(),
    val dateOfBirth: Input<String> = Input.absent(),
    val identificationNumber: Input<String> = Input.absent(),
    val race: Input<String> = Input.absent(),
    val sterilized: Input<Boolean> = Input.absent(),
    val color: Input<String> = Input.absent(),
    val gender: Input<Gender> = Input.absent(),
    val insurancePolicyNumber: Input<String> = Input.absent()
) : InputType {
    override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller.invoke { writer ->
        writer.writeCustom("id", CustomType.ID, this@AnimalUpdate.id)
        writer.writeString("type", this@AnimalUpdate.type.toString())
        if (this@AnimalUpdate.name.defined) {
            writer.writeString("name", this@AnimalUpdate.name.value)
        }
        if (this@AnimalUpdate.dateOfBirth.defined) {
            writer.writeString("dateOfBirth", this@AnimalUpdate.dateOfBirth.value)
        }
        if (this@AnimalUpdate.identificationNumber.defined) {
            writer.writeString("identificationNumber", this@AnimalUpdate.identificationNumber.value)
        }
        if (this@AnimalUpdate.race.defined) {
            writer.writeString("race", this@AnimalUpdate.race.value)
        }
        if (this@AnimalUpdate.sterilized.defined) {
            writer.writeBoolean("sterilized", this@AnimalUpdate.sterilized.value)
        }
        if (this@AnimalUpdate.color.defined) {
            writer.writeString("color", this@AnimalUpdate.color.value)
        }
        if (this@AnimalUpdate.gender.defined) {
            // toString CHANGED
            writer.writeString("gender", this@AnimalUpdate.gender.value?.toString())
        }
        if (this@AnimalUpdate.insurancePolicyNumber.defined) {
            writer.writeString("insurancePolicyNumber", this@AnimalUpdate.insurancePolicyNumber.value)
        }
    }
}