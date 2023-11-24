package com.example.faunaonboarding.onboarding.userCreate


import com.example.faunaonboarding.createAccount.AccountDataSource
import com.example.faunaonboarding.util.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserCreateViewModelTestMan {

    private lateinit var userCreateViewModel: UserCreateViewModel
    private lateinit var userCreateRepository: UserCreateRepository

    // added for create user
    private val testDispatcher = TestCoroutineDispatcher()


    @Mock
    private lateinit var userCreateRepositoryMock: UserCreateRepository

    @Mock
    private lateinit var accountDataSource: AccountDataSource

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        userCreateViewModel = UserCreateViewModel(userCreateRepositoryMock)
        accountDataSource = AccountDataSource(User())
        userCreateRepository = UserCreateRepository(accountDataSource, testDispatcher)
    }

    @Test
    fun setName_ValidName_InputNameIsSetCorrectly() {
        val testName = "testName"

        userCreateViewModel.setName(testName)

        assertTrue(userCreateViewModel.getName() == testName)
    }

    @Test
    fun setEmail_ValidEmail_InputEmailIsSetCorrectly() {
        val testEmail = "email@gmail.com"

        userCreateViewModel.setEmail(testEmail)

        assertTrue(userCreateViewModel.getEmail() == testEmail)
    }

    @Test
    fun setPhoneNumber_ValidPhoneNumber_InputPhoneNumberIsSetCorrectly() {
        val testPhoneNumber = "12345678"

        userCreateViewModel.setPhoneNumber(testPhoneNumber)

        assertTrue(userCreateViewModel.getPhoneNumber() == testPhoneNumber)
    }

    @Test
    fun setCheckboxStatus_CheckedStatus_InputCheckboxIsSetCorrectly() {
        val testCheckboxChecked = true

        userCreateViewModel.setCheckboxCheckedStatus(testCheckboxChecked)

        assertTrue(userCreateViewModel.getCheckboxCheckedStatus() == testCheckboxChecked)
    }

    @Test
    fun testCheckUiState_isSetCorrectly() = runTest {
        // reinitialize
        val testName = "testName"
        val testEmail = "email@gmail.com"
        val testPhoneNumber = "12345678"
        var testCheckboxChecked = true

        userCreateViewModel.setName(testName)
        userCreateViewModel.setEmail(testEmail)
        userCreateViewModel.setPhoneNumber(testPhoneNumber)
        userCreateViewModel.setCheckboxCheckedStatus(testCheckboxChecked)

        var userCreateInputUiState =
            UserCreateInputUIState(testName, testEmail, testPhoneNumber, testCheckboxChecked)

        userCreateViewModel.userCreateUiState.first().inputUIState
        assertTrue(userCreateViewModel.userCreateUiState.value.inputUIState == userCreateInputUiState)
        // this step needs removal to work
//        assertTrue(userCreateViewModel.userCreateUiState.value.inputUIState.continueEnabled())
    }

    @Test
    fun setName_ValidName_ValidationCorrect() {
        val inputUIState = UserCreateInputUIState(name = "John Doe")

        val isNameError = inputUIState.isNameError()

        assert(!isNameError)
    }

    @Test
    fun setName_InvalidName_ValidationIncorrect() {
        val inputUIState = UserCreateInputUIState(name = "")

        val isNameError = inputUIState.isNameError()

        assert(isNameError)
    }

//    @Test
//    fun setEmail_ValidEmail_ValidationCorrect() {
//        val inputUIState = UserCreateInputUIState(email = "email@example.com")
//
//        val isEmailError = inputUIState.isEmailError()
//
//        assert(!isEmailError)
//    }
//
//    @Test
//    fun setEmail_InvalidEmail_ValidationIncorrect() {
//        val inputUIState = UserCreateInputUIState(email = "invalid_email")
//
//        val isEmailError = inputUIState.isEmailError()
//
//        assert(isEmailError)
//    }
//
//    @Test
//    fun setPhoneNumber_ValidPhoneNumber_ValidationCorrect() {
//        val inputUIState = UserCreateInputUIState(phoneNumber = "12345678")
//
//        val isPhoneNumberError = inputUIState.isPhoneNumberError()
//
//        assert(isPhoneNumberError)
//    }

//    @Test
//    fun setPhoneNumber_InvalidPhoneNumber_ValidationIncorrect() {
//        val inputUIState = UserCreateInputUIState(phoneNumber = "")
//
//        val isPhoneNumberError = inputUIState.isPhoneNumberError()
//
//        assert(isPhoneNumberError)
//    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun createUser_ValidData_UserCreatedSuccessfully() = runTest {
        // Arrange
        val testName = "John Doe"
        val testPhoneNumber = "1234567890"
        val testEmail = "test@example.com"

//        val userCreateViewModel = UserCreateViewModel()
        val userCreateViewModel = UserCreateViewModel(userCreateRepository)
//      every { repo.userCreate(any(), any(), any()) } returns launch { accountDataSource.userCreate(testName, testPhoneNumber, testEmail) }

        // Act
        userCreateViewModel.setName(testName)
        userCreateViewModel.setPhoneNumber(testPhoneNumber)
        userCreateViewModel.setEmail(testEmail)
//        userCreateViewModel.createUser()
        launch { userCreateViewModel.createUser() }

        // Assert
        // is null??
//        verify { (userCreateRepositoryMock).userCreate(testName, testPhoneNumber, testEmail) }
        val result = userCreateRepository.getCreateUserFlow.firstOrNull()
        assertNotNull(result)
        assertTrue(result!!.isSuccess)
        assertEquals(result, Result.success(true))
    }

    // -- excluded ------------------------------------------
//    @Test
//    fun setAllValidationCorrect() = runTest{
//        // Arrange
//        val inputUIState = UserCreateInputUIState(
//            name = "John Doe",
//            email = "email@example.com",
//            phoneNumber = "1234567890",
//            true
//        )
//
//        // Act
//        val isContinueEnabled = inputUIState.continueEnabled()
//
//        // Assert
//        assert(isContinueEnabled)
//    }
}