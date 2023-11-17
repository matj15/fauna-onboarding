package com.example.faunaonboarding.animal

import com.example.faunaonboarding.animalCreate.AnimalCreate
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.coroutines.CoroutineContext

@Singleton
open class AnimalRepository @Inject constructor(
    private val animalDataSource: AnimalDataSource,
    override val coroutineContext: CoroutineContext = Dispatchers.IO,
) : CoroutineScope {
    private val scope = CoroutineScope(coroutineContext)

    private val animalCreateFlow = MutableSharedFlow<Result<Boolean>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val getAnimalCreateFlow: Flow<Result<Boolean>> = animalCreateFlow

    open suspend fun animalCreate(animalCreate: AnimalCreate) = scope.async {
        animalDataSource.createAnimal(animalCreate).collect {
            animalCreateFlow.emit(it)
        }
    }

    private val animalUpdateFlow = MutableSharedFlow<Result<Boolean>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val getAnimalUpdateFlow: Flow<Result<Boolean>> = animalUpdateFlow

    open suspend fun animalUpdate(animalUpdate: AnimalUpdate) = scope.async {
        animalDataSource.updateAnimal(animalUpdate).collect {
            animalUpdateFlow.emit(it)
        }
    }
}
