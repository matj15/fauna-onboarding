package com.example.faunaonboarding.animal

import com.example.faunaonboarding.animalCreate.AnimalCreate
import javax.inject.Inject
import javax.inject.Singleton
import com.example.faunaonboarding.util.Result

@Singleton
class AnimalRepository @Inject constructor(
    private val animalDataSource : AnimalDataSource
) {

    suspend fun animalCreate(animalCreate: AnimalCreate): Result<Boolean> {
        return animalDataSource.createAnimal(animalCreate)
    }

    suspend fun animalUpdate(animalUpdate: AnimalUpdate): Result<Boolean> {
        return animalDataSource.updateAnimal(animalUpdate)
    }
}
