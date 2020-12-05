package com.example.shelter.login

import com.RxJavaRule
import com.example.shelter.data.repository.IUserRepository
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login.model.LoginException
import com.example.shelter.presentation.onBoarding.login.reducer.LoginReducer
import com.example.shelter.presentation.onBoarding.registration2.model.User
import com.example.shelter.presentation.storage.LoggedUserProvider
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.junit.Assert.assertEquals
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class LoginReducerTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val rxRule: RxJavaRule = RxJavaRule()

    @Mock
    lateinit var manager: LoggedUserProvider

    @Mock
    lateinit var rep: IUserRepository

    private val validEmail: String = "newtest@mail.ru"
    private val correctPassword: String = "password"

    private val exception = LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, "")

    // Method CheckData

    @Test
    fun changeUserTest() {
        val reducer = LoginReducer(manager)
        val observer = reducer.updateDestination
            .test()
            .assertValueCount(0)
            .assertNotComplete()

        reducer.login(
                validEmail,
                correctPassword
        )

        observer.assertValueCount(1).assertNotComplete()
        assertEquals(observer.values().first(),LoginDestination.NEWS_SCREEN)
    }

    @Test
    fun emptyUserDataTest() {
        val reducer =
            LoginReducer(
                manager
            )
        val observer = reducer.updateError
            .test()
            .assertValueCount(0)
            .assertNotComplete()
        reducer.login(
                "",
                ""
        )
        assertEquals(observer.values().first(), exception)
    }

    @Test
    fun wrongPassportTest() {
        val reducer =
            LoginReducer(
                manager
            )
        val observer = reducer.updateError
            .test()
            .assertValueCount(0)
            .assertNotComplete()
        reducer.login(
                validEmail,
                ""
        )
        observer.assertValueCount(1)
        assertEquals(observer.values().first(), exception)
    }

    @Test
    fun wrongEmailTest() {
        val reducer =
            LoginReducer(
                manager
            )
        val observer = reducer.updateError
            .test()
            .assertValueCount(0)
            .assertNotComplete()
        reducer.login(
                "newtestissart.com",
                correctPassword
        )
        assertEquals(observer.values().first(),exception)
    }

     @Test
     fun loggedUserTest() {
         val reducer =
             LoginReducer(
                 manager
             )

         reducer.login(
             validEmail,
             correctPassword
         )
         Mockito.verify(manager, Mockito.times(1))
             .setLoggedUser(User(
                 email = validEmail,
                 password = correctPassword
             ))
     }

     @Test
     fun loggedWrongUserTest() {
         val reducer =
             LoginReducer(
                 manager
             )

         reducer.login( "", correctPassword)
         Mockito.verify(manager, Mockito.never()).setLoggedUser(User())
     }
}