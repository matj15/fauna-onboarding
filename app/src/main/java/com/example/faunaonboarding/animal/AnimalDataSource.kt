package com.example.faunaonboarding.animal

import androidx.compose.runtime.mutableStateOf
import com.example.faunaonboarding.animalCreate.AnimalCreate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimalDataSource @Inject constructor(
//    private val apolloClient: ApolloClient,
//    private val processContext: CoroutineContext = Dispatchers.Default
) {
    private val createAnimalSuccessful = mutableStateOf(true)
    private val animalUpdateSuccessful = mutableStateOf(true)

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

    open fun createAnimal(animalCreate: AnimalCreate): Flow<Result<Boolean>> {
        return flow { emit(Result.success(createAnimalSuccessful.value)) }
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

    open fun updateAnimal(animalUpdate: AnimalUpdate): Flow<Result<Boolean>> {
        return flow { emit(Result.success(animalUpdateSuccessful.value)) }
    }
}