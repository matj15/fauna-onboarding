//package com.example.faunaonboarding
//
//import android.app.Application
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.faunaonboarding.createAccount.AccountDataSource
//import com.example.faunaonboarding.onboarding.userCreate.UserCreateComposable
//import com.example.faunaonboarding.onboarding.userCreate.UserCreateInputUIState
//import com.example.faunaonboarding.onboarding.userCreate.UserCreateRepository
//import com.example.faunaonboarding.onboarding.userCreate.UserCreateViewModel
//import com.example.faunaonboarding.onboarding.userCreate.continueEnabled
//import com.example.faunaonboarding.onboarding.userCreate.isEmailError
//import com.example.faunaonboarding.onboarding.userCreate.isNameError
//import com.example.faunaonboarding.onboarding.userCreate.isPhoneNumberError
//import com.example.faunaonboarding.ui.theme.FaunaTheme
//import com.example.faunaonboarding.util.User
//import dagger.hilt.android.AndroidEntryPoint
//import junit.framework.TestCase.assertFalse
//import junit.framework.TestCase.assertTrue
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.last
//import kotlinx.coroutines.flow.single
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//
//@RunWith(JUnit4::class)
////@HiltAndroidTest
////@CustomTestApplication(com.example.faunaonboarding.CustomTestApplication::class)
//class UserCreateUITest {
//
////    @get:Rule
////    val activityRule = ActivityScenarioRule(UserCreate::class.java)
//
////    @get:Rule(order = 0)
////    var hiltRule = HiltAndroidRule(this)
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    lateinit var userCreateViewModel: UserCreateViewModel
//
//    //    @Mock
//    private lateinit var userCreateRepository: UserCreateRepository
//
//    //    @Mock
//    private lateinit var userCreateDataSource: AccountDataSource
//
////    @Before
////    fun init() {
////        hiltRule.inject()
////    }
//
//    @Before
//    fun setUp() {
////        MockitoAnnotations.initMocks(this)
////        userCreateRepository = Mockito.mock(UserCreateRepository::class.java)
//        userCreateDataSource = AccountDataSource(User())
//        userCreateRepository = UserCreateRepository(userCreateDataSource)
//        userCreateViewModel = UserCreateViewModel(userCreateRepository)
//    }
//
//    @Test
//    fun testInputValidationAndErrorColors() = runTest {
//        val userName = "John"
//        val userEmail = "john@doe.com"
//        val userPhone = "12345678"
//        composeTestRule.setContent {
////            userCreateViewModel = hiltViewModel()
//            FaunaTheme {
//                UserCreateComposable(userCreateInputUIState = UserCreateInputUIState(
//                    "",
//                    "",
//                    "",
//                    false
//                ),
//                    onNameChanged = userCreateViewModel::setName,
//                    onEmailChanged = userCreateViewModel::setEmail,
//                    onPhoneNumberChanged = userCreateViewModel::setPhoneNumber,
//                    onCheckboxCheckedChanged = userCreateViewModel::setCheckboxCheckedStatus,
//                    onContinueClick = {},
//                    onBackClick = {},
//                    onCloseClick = {})
//            }
//        }
//        println("UI state")
//        println(userCreateViewModel.userCreateUiState.first())
//
//        // Enter a blank name
//        composeTestRule.onNodeWithTag("userName")
//            .performTextInput("")
//
//        // Verify name error
////        assertTrue(userCreateViewModel.userCreateUiState.first().inputUIState.isNameError())
//
//        // Enter a valid name
//        composeTestRule.onNodeWithTag("userName")
//            .performTextInput(userName)
//        println(userCreateViewModel.userCreateUiState.first())
//
//        // Verify name error
//        assertFalse(userCreateViewModel.userCreateUiState.first().inputUIState.isNameError())
//
//        println("UI state")
//        println(userCreateViewModel.userCreateUiState.first())
//
//        // Enter a blank email
//        composeTestRule.onNodeWithTag("userEmail")
//            .performTextInput("")
//
//        // Verify email error
//        assertTrue(userCreateViewModel.userCreateUiState.first().inputUIState.isEmailError())
//
//        // Enter a valid email
//        composeTestRule.onNodeWithTag("userEmail")
//            .performTextInput(userEmail)
//        println(userCreateViewModel.userCreateUiState.first())
//
//        // Verify email error
//        assertFalse(userCreateViewModel.userCreateUiState.first().inputUIState.isEmailError())
//
//        println("UI state")
//        println(userCreateViewModel.userCreateUiState.first())
//
//        // Enter a blank phone number
//        composeTestRule.onNodeWithTag("userPhone")
//            .performTextInput("")
//
//        // Verify phone number error
//        assertTrue(userCreateViewModel.userCreateUiState.first().inputUIState.isPhoneNumberError())
//
//        // Enter a valid phone number
//        composeTestRule.onNodeWithTag("userPhone")
//            .performTextInput(userPhone)
//        println(userCreateViewModel.userCreateUiState.first())
//
//        // Verify email error
//        assertFalse(userCreateViewModel.userCreateUiState.first().inputUIState.isPhoneNumberError())
//
//        println("UI state")
//        println(userCreateViewModel.userCreateUiState.first())
//
//
//        // Check the checkbox
//        composeTestRule.onNodeWithTag("checkbox").assertIsToggleable()
//        composeTestRule.onNodeWithTag("checkbox").performClick()
//
//        // Verify checkbox checked
//        assertTrue(userCreateViewModel.userCreateUiState.first().inputUIState.checkboxChecked)
//    }
//}
//
//@AndroidEntryPoint
//class UserCreateTestActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val userCreateViewModel: UserCreateViewModel = hiltViewModel()
//            UserCreateComposable(userCreateInputUIState = UserCreateInputUIState(
//                "",
//                "",
//                "",
//                false
//            ),
//                onNameChanged = userCreateViewModel::setName,
//                onEmailChanged = userCreateViewModel::setEmail,
//                onPhoneNumberChanged = userCreateViewModel::setPhoneNumber,
//                onCheckboxCheckedChanged = userCreateViewModel::setCheckboxCheckedStatus,
//                onContinueClick = {},
//                onBackClick = {},
//                onCloseClick = {})
//        }
//    }
//}
//
//class CustomTestApplication : Application()