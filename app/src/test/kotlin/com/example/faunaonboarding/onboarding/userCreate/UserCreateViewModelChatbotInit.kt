//package com.example.faunaonboarding.onboarding.userCreate
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.example.faunaonboarding.onboarding.userCreate.UserCreateInputUIState
//import com.example.faunaonboarding.onboarding.userCreate.UserCreateRepository
//import com.example.faunaonboarding.onboarding.userCreate.UserCreateViewModel
//import com.example.faunaonboarding.onboarding.userCreate.continueEnabled
//import com.example.faunaonboarding.onboarding.userCreate.isEmailError
//import com.example.faunaonboarding.onboarding.userCreate.isNameError
//import com.example.faunaonboarding.onboarding.userCreate.isPhoneNumberError
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.junit.Assert.*
//import org.junit.Rule
//import org.mockito.MockitoAnnotations
//
//@ExperimentalCoroutinesApi
//class UserCreateViewModelTestChatbotInit {
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
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//        userCreateViewModel = UserCreateViewModel(userCreateRepository)
//    }
//
//    @Test
//    fun `setName should update userName`() {
//        // Arrange
//        val name = "John Doe"
//
//        // Act
//        userCreateViewModel.setName(name)
//
//        // Assert
//        assertEquals(name, userCreateViewModel.getName())
//    }
//
//    @Test
//    fun `setEmail should update userEmail`() {
//        // Arrange
//        val email = "test@example.com"
//
//        // Act
//        userCreateViewModel.setEmail(email)
//
//        // Assert
//        assertEquals(email, userCreateViewModel.getEmail())
//    }
//
//    @Test
//    fun `setPhoneNumber should update userPhoneNumber when input is valid`() {
//        // Arrange
//        val phoneNumber = "1234567890"
//
//        // Act
//        userCreateViewModel.setPhoneNumber(phoneNumber)
//
//        // Assert
//        assertEquals(phoneNumber, userCreateViewModel.getPhoneNumber())
//    }
//
//    @Test
//    fun `setPhoneNumber should not update userPhoneNumber when input is too long`() {
//        // Arrange
//        val phoneNumber = "12345678901234567890"
//
//        // Act
//        userCreateViewModel.setPhoneNumber(phoneNumber)
//
//        // Assert
//        assertNotEquals(phoneNumber, userCreateViewModel.getPhoneNumber())
//    }
//
//    @Test
//    fun `setCheckboxCheckedStatus should update userCheckboxChecked`() {
//        // Arrange
//        val isChecked = true
//
//        // Act
//        userCreateViewModel.setCheckboxCheckedStatus(isChecked)
//
//        // Assert
//        assertEquals(isChecked, userCreateViewModel.getCheckboxCheckedStatus())
//    }
//
//    @Test
//    fun `createUser should call userCreateRepository with correct parameters`() = runBlockingTest {
//        // Arrange
//        val userName = "John Doe"
//        val userEmail = "test@example.com"
//        val userPhoneNumber = "1234567890"
//
//        // Act
//        userCreateViewModel.setName(userName)
//        userCreateViewModel.setEmail(userEmail)
//        userCreateViewModel.setPhoneNumber(userPhoneNumber)
//        userCreateViewModel.createUser()
//
//        // Assert
//        Mockito.verify(userCreateRepository).userCreate(userName, userPhoneNumber, userEmail)
//    }
//
//    @Test
//    fun `isNameError should return true when name is blank`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(name = "")
//
//        // Act
//        val result = inputUIState.isNameError()
//
//        // Assert
//        assertTrue(result)
//    }
//
//    @Test
//    fun `isNameError should return false when name is not blank`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(name = "John Doe")
//
//        // Act
//        val result = inputUIState.isNameError()
//
//        // Assert
//        assertFalse(result)
//    }
//
//    @Test
//    fun `isEmailError should return true when email is invalid`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(email = "invalid_email")
//
//        // Act
//        val result = inputUIState.isEmailError()
//
//        // Assert
//        assertTrue(result)
//    }
//
//    @Test
//    fun `isEmailError should return false when email is valid`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(email = "test@example.com")
//
//        // Act
//        val result = inputUIState.isEmailError()
//
//        // Assert
//        assertFalse(result)
//    }
//
//    @Test
//    fun `isPhoneNumberError should return true when phone number is invalid`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(phoneNumber = "123")
//
//        // Act
//        val result = inputUIState.isPhoneNumberError()
//
//        // Assert
//        assertTrue(result)
//    }
//
//    @Test
//    fun `isPhoneNumberError should return false when phone number is valid`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(phoneNumber = "1234567890")
//
//        // Act
//        val result = inputUIState.isPhoneNumberError()
//
//        // Assert
//        assertFalse(result)
//    }
//
//    @Test
//    fun `continueEnabled should return true when all input is valid and checkbox is checked`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(
//            name = "John Doe",
//            email = "test@example.com",
//            phoneNumber = "1234567890",
//            checkboxChecked = true
//        )
//
//        // Act
//        val result = inputUIState.continueEnabled()
//
//        // Assert
//        assertTrue(result)
//    }
//
//    @Test
//    fun `continueEnabled should return false when name is blank`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(
//            name = "",
//            email = "test@example.com",
//            phoneNumber = "1234567890",
//            checkboxChecked = true
//        )
//
//        // Act
//        val result = inputUIState.continueEnabled()
//
//        // Assert
//        assertFalse(result)
//    }
//
//    @Test
//    fun `continueEnabled should return false when email is invalid`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(
//            name = "John Doe",
//            email = "invalid_email",
//            phoneNumber = "1234567890",
//            checkboxChecked = true
//        )
//
//        // Act
//        val result = inputUIState.continueEnabled()
//
//        // Assert
//        assertFalse(result)
//    }
//
//    @Test
//    fun `continueEnabled should return false when phone number is invalid`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(
//            name = "John Doe",
//            email = "test@example.com",
//            phoneNumber = "123",
//            checkboxChecked = true
//        )
//
//        // Act
//        val result = inputUIState.continueEnabled()
//
//        // Assert
//        assertFalse(result)
//    }
//
//    @Test
//    fun `continueEnabled should return false when checkbox is not checked`() {
//        // Arrange
//        val inputUIState = UserCreateInputUIState(
//            name = "John Doe",
//            email = "test@example.com",
//            phoneNumber = "1234567890",
//            checkboxChecked = false
//        )
//
//        // Act
//        val result = inputUIState.continueEnabled()
//
//        // Assert
//        assertFalse(result)
//    }
//}