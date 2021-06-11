package com.example.shelter.presentation.onBoarding.registration.reducer

import com.example.shelter.presentation.onBoarding.registration.model.*
import com.example.shelter.presentation.onBoarding.utils.checkHuman
import com.example.shelter.presentation.onBoarding.utils.checkOrganisation
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RegistrationReducer @Inject constructor(
    private var loggedUser: LoggedUserProvider
): IRegistrationReducer {

    override val updateDestination: PublishSubject<RegistrationDestination> = PublishSubject.create()
    override val updateException: PublishSubject<RegistrationException> = PublishSubject.create()
    override val updateState: PublishSubject<RegistrationState> = PublishSubject.create()

    private var state = RegistrationState()
    private var user: User = User()

    override fun updateUserType(type: UserType) {
        state = when (type) {
            UserType.HUMAN -> {
                state.copy(
                    userType = UserType.HUMAN,
                    lastNameVisibility = true,
                    firstNameVisibility = true,
                    organizationNameVisibility = false
                )
            }

            UserType.ORGANIZATION -> {
                state.copy(
                    userType = UserType.ORGANIZATION,
                    lastNameVisibility = false,
                    firstNameVisibility = false,
                    organizationNameVisibility = true
                )
            }
        }

        updateState.onNext(state)
    }

    private val disposeContainer = CompositeDisposable()

    override fun tryToRegistration(user: User) {
        val valid = when {
            (user is Human) -> checkHuman(user)

            (user is Organisation) -> checkOrganisation(user)

            else -> false
        }

        if (valid) {
            this.user = user

//            state = state.copy(
//                toolbar = true
//            )
//            updateState.onNext(state)
            registration()
        } else {
            updateException.onNext(
                RegistrationException(
                    RegistrationErrorCode.WRONG_ENTERED_DATA,
                    "Wrong entered data."
                )
            )
        }
    }

    override fun registration() {
        loggedUser.setLoggedUser(user)
//        state = state.copy(
//                toolbar = true
//            )
//            updateState.onNext(state)

        updateDestination.onNext(RegistrationDestination.NEWS_SCREEN)
    }

    override fun clearDispose() {
        disposeContainer.clear()
    }

}
