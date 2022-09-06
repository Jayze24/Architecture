package space.jay.cleanarchitecture.ui.view.login

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import space.jay.cleanarchitecture.base.BaseViewModel
import space.jay.cleanarchitecture.domain.useCase.user.UseCaseLogin
import javax.inject.Inject

@HiltViewModel
class ViewModelLogin @Inject constructor(
    val useCaseLogin: UseCaseLogin
) : BaseViewModel() {

    fun login(email: String, password: String) {
        launchWithIO {
            useCaseLogin.invoke(email, password)
        }
    }
}