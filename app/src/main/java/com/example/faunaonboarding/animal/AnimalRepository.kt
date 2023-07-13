package com.example.faunaonboarding.animal

import com.example.faunaonboarding.animalCreate.AnimalCreate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimalRepository @Inject constructor(
    private val animalDataSource : AnimalDataSource
) {
//    suspend fun animalCreate(animalCreate: AnimalCreate): Flow<Result<CreateAnimalMutation.CreateAnimal>> {
//        return animalDataSource.createAnimal(animalCreate)
//        requestCodeFlow.emit(Result.success(true))
//
//    }

    suspend fun animalCreate(animalCreate: AnimalCreate): com.example.faunaonboarding.util.Result<Boolean> {
        return animalDataSource.createAnimal(animalCreate)
    }

    suspend fun animalUpdate(animalUpdate: AnimalUpdate): com.example.faunaonboarding.util.Result<Boolean> {
        return animalDataSource.updateAnimal(animalUpdate)
    }
}
