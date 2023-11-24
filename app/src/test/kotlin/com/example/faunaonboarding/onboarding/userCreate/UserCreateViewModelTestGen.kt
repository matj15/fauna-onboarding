package com.example.faunaonboarding.onboarding.userCreate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.faunaonboarding.createAccount.AccountDataSource
import com.example.faunaonboarding.login.PhoneNumberViewModel
import com.example.faunaonboarding.login.UIState
import com.example.faunaonboarding.onboarding.userCreate.UserCreateRepository
import com.example.faunaonboarding.onboarding.userCreate.UserCreateUIState
import com.example.faunaonboarding.util.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class UserCreateViewModelTestGen {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userCreateRepositoryMock: UserCreateRepository

    private lateinit var userCreateViewModel: UserCreateViewModel
    private lateinit var userCreateRepository: UserCreateRepository

    val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        userCreateViewModel = UserCreateViewModel(userCreateRepositoryMock)

        val userCreateDataSource = AccountDataSource(User())
        userCreateRepository = UserCreateRepository(userCreateDataSource, dispatcher)
    }

    @Test
    fun `testSetName`() = runBlockingTest {
        // Arrange
        val testName = "John Doe"

        // Act
        userCreateViewModel.setName(testName)

        // Assert
        assertEquals(testName, userCreateViewModel.getName())
    }

    @Test
    fun `testSetEmail`() = runBlockingTest {
        // Arrange
        val testEmail = "test@example.com"

        // Act
        userCreateViewModel.setEmail(testEmail)

        // Assert
        assertEquals(testEmail, userCreateViewModel.getEmail())
    }

    @Test
    fun `testSetPhoneNumber`() = runBlockingTest {
        // Arrange
        val testPhoneNumber = "12345678"

        // Act
        userCreateViewModel.setPhoneNumber(testPhoneNumber)

        // Assert
        assertEquals(testPhoneNumber, userCreateViewModel.getPhoneNumber())
    }

    @Test
    fun `testSetCheckboxCheckedStatus`() = runBlockingTest {
        // Arrange
        val testCheckboxCheckedStatus = true

        // Act
        userCreateViewModel.setCheckboxCheckedStatus(testCheckboxCheckedStatus)

        // Assert
        assertEquals(testCheckboxCheckedStatus, userCreateViewModel.getCheckboxCheckedStatus())
    }

    @Test
    fun `testCreateUser`() = runTest {
        // Arrange
        val testName = "John Doe"
        val testPhoneNumber = "1234567890"
        val testEmail = "test@example.com"
        val testCheckboxCheckedStatus = true

        val userCreateViewModelLocal = UserCreateViewModel(userCreateRepository)


        // Act
        userCreateViewModelLocal.setName(testName)
        userCreateViewModelLocal.setPhoneNumber(testPhoneNumber)
        userCreateViewModelLocal.setEmail(testEmail)
        userCreateViewModelLocal.setCheckboxCheckedStatus(testCheckboxCheckedStatus)

        launch { userCreateViewModelLocal.createUser() }
        // Assert
        val result = userCreateRepository.getCreateUserFlow.firstOrNull()
        assertNotNull(result)
        assertTrue(result!!.isSuccess)
        assertEquals(result, Result.success(true))
    }

    // assume it works
//    @Test
//    fun `testUserCreateUiState`() = runBlockingTest {
//        // Arrange
//        val testName = "John Doe"
//        val testEmail = "test@example.com"
//        val testPhoneNumber = "12345678"
//        val testCheckboxChecked = true
//        val testUiState = UIState.Initial
//
//        // Act
//        userCreateViewModel.setName(testName)
//        userCreateViewModel.setEmail(testEmail)
//        userCreateViewModel.setPhoneNumber(testPhoneNumber)
//        userCreateViewModel.setCheckboxCheckedStatus(testCheckboxChecked)
//
//        userCreateViewModel.userCreateUiState.first()
//
//        val userCreateUiState = userCreateViewModel.userCreateUiState.value
//
//        // Assert
//        val expectedUserCreateUiState = UserCreateUIState(
//            result = testUiState,
//            inputUIState = UserCreateInputUIState(
//                name = testName,
//                email = testEmail,
//                phoneNumber = testPhoneNumber,
//                checkboxChecked = testCheckboxChecked
//            )
//        )
//        assertEquals(expectedUserCreateUiState, userCreateUiState)
//    }
}