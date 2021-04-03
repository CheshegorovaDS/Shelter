package com.example.shelter.login

import com.RxJavaRule
import com.example.shelter.data.user.repositry.IUserRepository
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login.model.LoginException
import com.example.shelter.presentation.onBoarding.login.presenter.LoginPresenter
import com.example.shelter.presentation.onBoarding.login.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login.reducer.LoginReducer
import com.example.shelter.presentation.onBoarding.login.view.LoginView
import com.example.shelter.presentation.storage.LoggedUserProvider
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class LoginPresenterTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val rxRule: RxJavaRule = RxJavaRule()

    @Mock
    lateinit var reducer: ILoginReducer

    @Mock
    lateinit var view: LoginView

    @Mock
    lateinit var repository: IUserRepository

    @Mock
    lateinit var manager: LoggedUserProvider

    @Test
    fun clearDisposeTest() {
        val presenter =
            LoginPresenter(
                reducer
            )
        presenter.detachView()
        Mockito.verify(reducer).clearDispose()
    }

    @Test
    fun renderStateWithCorrectUserTest() {
        reducer = LoginReducer(manager)
        val presenter = LoginPresenter(reducer)
        presenter.attachView(view)
        reducer.login(
            "ggg@mail.ru",
            "12345"
        )

        Mockito.verify(view).navigateTo(LoginDestination.NEWS_SCREEN)
    }

    @Test
    fun renderStateWithEmptyUserTest() {
        reducer = LoginReducer(manager)
        val presenter = LoginPresenter(reducer)
        presenter.attachView(view)
        reducer.login("", "")

        Mockito.verify(view).showError(LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, ""))
    }

    @Test
    fun renderStateWithCorrectPasswordTest() {
        reducer =
            LoginReducer(
                manager
            )
        val presenter =
            LoginPresenter(
                reducer
            )
        presenter.attachView(view)
        reducer.login(
            "",
            "12345"
        )
        Mockito.verify(view).showError(LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, ""))
    }

    @Test
    fun renderStateWithWithValidEmailTest() {
        reducer =
            LoginReducer(
                manager
            )
        val presenter =
            LoginPresenter(
                reducer
            )
        presenter.attachView(view)
        reducer.login(
            "newtest@mail.ru",
            ""
        )

        Mockito.verify(view).showError(LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, ""))
    }
}