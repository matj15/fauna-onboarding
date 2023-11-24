package com.example.faunaonboarding

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.faunaonboarding.createAccount.AccountDataSource
import com.example.faunaonboarding.login.UIState
import com.example.faunaonboarding.onboarding.userCreate.UserCreateComposable
import com.example.faunaonboarding.onboarding.userCreate.UserCreateInputUIState
import com.example.faunaonboarding.onboarding.userCreate.UserCreateRepository
import com.example.faunaonboarding.onboarding.userCreate.UserCreateUIState
import com.example.faunaonboarding.onboarding.userCreate.UserCreateViewModel
import com.example.faunaonboarding.onboarding.userCreate.isEmailError
import com.example.faunaonboarding.onboarding.userCreate.isNameError
import com.example.faunaonboarding.onboarding.userCreate.isPhoneNumberError
import com.example.faunaonboarding.ui.theme.FaunaTheme
import com.example.faunaonboarding.util.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@RunWith(AndroidJUnit4::class)
@RunWith(JUnit4::class)
class UserCreateInputUITestChatbot {

    @get:Rule
    val composeTestRule = createComposeRule()

    // createUser added
//    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var viewModel: UserCreateViewModel

    private lateinit var userCreateRepository: UserCreateRepository

    private lateinit var accountDataSource: AccountDataSource

    // createUser added
    @Before
    fun setUp() {
//        Dispatchers.setMain(testDispatcher)
        // added
        accountDataSource = AccountDataSource(User())
//        val userCreateRepository = UserCreateRepository(accountDataSource, testDispatcher)
        userCreateRepository = UserCreateRepository(accountDataSource)

        // fixed
        viewModel = UserCreateViewModel(userCreateRepository)
    }

    // added runTest
//    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUserInput() = runTest {
        // Arrange

        val initialUiState = UserCreateUIState(
            result = UIState.Initial,
            inputUIState = UserCreateInputUIState(
                "",
                "",
                "",
                false
            )
        )

        // fixed param
        // Act
        composeTestRule.setContent {
            FaunaTheme {
                UserCreateComposable(
                    userCreateInputUIState = initialUiState.inputUIState,
                    onNameChanged = viewModel::setName,
                    onEmailChanged = viewModel::setEmail,
                    onPhoneNumberChanged = viewModel::setPhoneNumber,
                    onCheckboxCheckedChanged = viewModel::setCheckboxCheckedStatus,
                    onBackClick = {},
                    onCloseClick = {},
                    onContinueClick = viewModel::createUser,
                )
            }
        }

        // Assert initial state
        composeTestRule.onNodeWithTag("userName")
            .assert(hasText(initialUiState.inputUIState.name))
        composeTestRule.onNodeWithTag("userEmail")
            .assert(hasText(initialUiState.inputUIState.email))
        composeTestRule.onNodeWithTag("userPhone")
            .assert(hasText(initialUiState.inputUIState.phoneNumber))
//        composeTestRule.onNodeWithTag("checkbox")
//            .assert(isNotSelected())
        // fixed
        composeTestRule.onNodeWithTag("checkbox")
            .assertIsToggleable()

        // Enter valid input
        val validName = "John Doe"
        val validEmail = "test@example.com"
        val validPhoneNumber = "12345678"

        viewModel.userCreateUiState.first()
        // Enter a blank input first
        composeTestRule.onNodeWithTag("userName")
            .performTextInput("")
        composeTestRule.onNodeWithTag("userName")
            .performTextInput(validName)


        // Verify name error
        assertFalse(viewModel.userCreateUiState.value.inputUIState.isNameError())

//        viewModel.userCreateUiState.first()
        // Enter a blank input first
        composeTestRule.onNodeWithTag("userEmail")
            .performTextInput("")
        composeTestRule.onNodeWithTag("userEmail")
            .performTextInput(validEmail)

        // Verify email error
        assertFalse(viewModel.userCreateUiState.value.inputUIState.isEmailError())

//        viewModel.userCreateUiState.first()
        // Enter a blank input first
        composeTestRule.onNodeWithTag("userPhone")
            .performTextInput("")
        composeTestRule.onNodeWithTag("userPhone")
            .performTextInput(validPhoneNumber)

        // Verify email error
        assertFalse(viewModel.userCreateUiState.value.inputUIState.isPhoneNumberError())

//        viewModel.userCreateUiState.first()
        composeTestRule.onNodeWithTag("checkbox")
            .performClick()

        // Verify checkbox checked
        assertTrue(viewModel.userCreateUiState.value.inputUIState.checkboxChecked)

        // Assert updated state
//        val updatedUiState = UserCreateUIState(
//            result = UIState.Initial,
//            inputUIState = UserCreateInputUIState(
//                name = validName,
//                email = validEmail,
//                phoneNumber = validPhoneNumber,
//                checkboxChecked = true
//            )
//        )

        // fixed
//        composeTestRule.onNodeWithTag("userName")
////            .assert(hasText(updatedUiState.inputUIState.name))
//            .assert(hasText(validName))
//        composeTestRule.onNodeWithTag("userEmail")
//            .assert(hasText(validEmail))
//        composeTestRule.onNodeWithTag("userPhone")
//            .assert(hasText(validPhoneNumber))
//        composeTestRule.onNodeWithTag("checkbox")
//            .assertIsToggleable()



        // added
        composeTestRule.onNodeWithTag("continueButton").assertIsEnabled()

        // Perform continue click
        composeTestRule.onNodeWithTag("continueButton")
            .performClick()

        // Assert UI state after continue click
//        val continueClickedUiState = UserCreateUIState(
//            result = UIState.Success,
//            inputUIState = updatedUiState.inputUIState
//        )

//        composeTestRule.onNodeWithTag("userName")
//            .assert(hasText(continueClickedUiState.inputUIState.name))
//        composeTestRule.onNodeWithTag("userEmail")
//            .assert(hasText(continueClickedUiState.inputUIState.email))
//        composeTestRule.onNodeWithTag("userPhone")
//            .assert(hasText(continueClickedUiState.inputUIState.phoneNumber))
//        composeTestRule.onNodeWithTag("checkbox")
//            .assert(isSelected())

        // Assert that the userCreate function is called in the view model (and invokes data source through repository)
//        assertTrue(viewModel.createUserCalled)
//        val result = userCreateRepository.getCreateUserFlow.firstOrNull()
//        assertNotNull(result)
//        assertTrue(result!!.isSuccess)
//        assertEquals(result, Result.success(true))
    }
}

//class MockUserCreateRepository : UserCreateRepository {
//    override suspend fun userCreate(name: String, phoneNumber: String, email: String) {
//        // Mock implementation
//    }
//}
//
//class UserCreateViewModel(private val userCreateRepository: UserCreateRepository) {
//    var createUserCalled = false
//
//    fun createUser() {
//        createUserCalled = true
//    }
//}