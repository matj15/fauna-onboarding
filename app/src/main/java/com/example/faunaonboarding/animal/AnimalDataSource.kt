package com.example.faunaonboarding.animal

import com.apollographql.apollo.ApolloClient
import com.example.faunaonboarding.animalCreate.AnimalCreate
import com.example.faunaonboarding.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class AnimalDataSource @Inject constructor(
//    private val apolloClient: ApolloClient,
//    private val processContext: CoroutineContext = Dispatchers.Default
) {

//    suspend fun createAnimal(animalCreate: AnimalCreate): Flow<Result<CreateAnimalMutation.CreateAnimal>> = withContext(processContext) {
//        apolloClient.mutate(CreateAnimalMutation(animalCreate)).toFlow()
//            .onEach {
//                Result.Loading
//            }
//            .map{
//                when {
//                    it.hasErrors() -> {
//                        Result.Error(it.errors!![0].message)
//                    }
//                    it.data?.createAnimal == null -> {
//                        Result.Error("Backend error")
//                    }
//                    else -> {
//                        Result.Success(it.data!!.createAnimal)
//                    }
//                }
//            }
//            .catch {
//                Log.d("AnimalDataSource", it.localizedMessage)
//            }
//    }

    suspend fun createAnimal(animalCreate: AnimalCreate): Result<Boolean> {
            return Result.Success(true)
        }

//    suspend fun updateAnimal(animalUpdate: AnimalUpdate): Flow<Result<UpdateAnimalMutation.UpdateAnimal>> = withContext(processContext) {
//        apolloClient.mutate(UpdateAnimalMutation(animalUpdate)).toFlow()
//            .onEach {
//                Result.Loading
//            }
//            .map{
//                when {
//                    it.hasErrors() -> {
//                        Result.Error(it.errors!![0].message)
//                    }
//                    it.data?.updateAnimal == null -> {
//                        Result.Error("Backend error")
//                    }
//                    else -> {
//                        Result.Success(it.data!!.updateAnimal)
//                    }
//                }
//            }
//            .catch {
//                Log.d("AnimalDataSource", it.localizedMessage)
//            }
//    }

    suspend fun updateAnimal(animalUpdate: AnimalUpdate): Result<Boolean> {
            return Result.Success(true)
        }
}