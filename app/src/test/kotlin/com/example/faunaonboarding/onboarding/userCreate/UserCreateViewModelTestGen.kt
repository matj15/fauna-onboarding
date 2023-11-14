package com.example.faunaonboarding.onboarding.userCreate
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.faunaonboarding.login.PhoneNumberViewModel
import com.example.faunaonboarding.login.UIState
import com.example.faunaonboarding.onboarding.userCreate.UserCreateRepository
import com.example.faunaonboarding.onboarding.userCreate.UserCreateUIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

//@ExperimentalCoroutinesApi
//class UserCreateViewModelTestGen {
//
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var userCreateRepository: UserCreateRepository
//
//    private lateinit var userCreateViewModel: UserCreateViewModel
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        userCreateViewModel = UserCreateViewModel(userCreateRepository)
//    }
//
//    @Test
//    fun `testSetName`() = runBlockingTest {
//        // Arrange
//        val testName = "John Doe"
//
//        // Act
//        userCreateViewModel.setName(testName)
//
//        // Assert
//        assertEquals(testName, userCreateViewModel.getName())
//    }
//
//    @Test
//    fun `testSetEmail`() = runBlockingTest {
//        // Arrange
//        val testEmail = "test@example.com"
//
//        // Act
//        userCreateViewModel.setEmail(testEmail)
//
//        // Assert
//        assertEquals(testEmail, userCreateViewModel.getEmail())
//    }
//
//    @Test
//    fun `testSetPhoneNumber`() = runBlockingTest {
//        // Arrange
//        val testPhoneNumber = "1234567890"
//
//        // Act
//        userCreateViewModel.setPhoneNumber(testPhoneNumber)
//
//        // Assert
//        assertEquals(testPhoneNumber, userCreateViewModel.getPhoneNumber())
//    }
//
//    @Test
//    fun `testSetCheckboxCheckedStatus`() = runBlockingTest {
//        // Arrange
//        val testCheckboxCheckedStatus = true
//
//        // Act
//        userCreateViewModel.setCheckboxCheckedStatus(testCheckboxCheckedStatus)
//
//        // Assert
//        assertEquals(testCheckboxCheckedStatus, userCreateViewModel.getCheckboxCheckedStatus())
//    }
//
//    @Test
//    fun `testCreateUser`()  {
//        // Arrange
//        val testName = "John Doe"
//        val testPhoneNumber = "1234567890"
//        val testEmail = "test@example.com"
//
//        // Act
//        userCreateViewModel.setName(testName)
//        userCreateViewModel.setPhoneNumber(testPhoneNumber)
//        userCreateViewModel.setEmail(testEmail)
//        userCreateViewModel.createUser()
//
//        // Assert
//        Mockito.verify(userCreateRepository).userCreate(testName, testPhoneNumber, testEmail)
//    }
//
//    @Test
//    fun `testUserCreateUiState`() = runBlockingTest {
//        // Arrange
//        val testName = "John Doe"
//        val testEmail = "test@example.com"
//        val testPhoneNumber = "1234567890"
//        val testCheckboxChecked = true
//        val testUiState = UIState.Success
//
//        // Act
//        userCreateViewModel.setName(testName)
//        userCreateViewModel.setEmail(testEmail)
//        userCreateViewModel.setPhoneNumber(testPhoneNumber)
//        userCreateViewModel.setCheckboxCheckedStatus(testCheckboxChecked)
//
//        val userCreateUiState = userCreateViewModel.userCreateUiState.first()
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
//}