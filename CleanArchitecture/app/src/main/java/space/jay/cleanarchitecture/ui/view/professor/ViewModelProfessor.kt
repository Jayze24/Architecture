package space.jay.cleanarchitecture.ui.view.professor

import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import space.jay.cleanarchitecture.base.BaseViewModel
import space.jay.cleanarchitecture.data.repository.room.data.DataUser
import space.jay.cleanarchitecture.domain.entity.user.TypeUser
import space.jay.cleanarchitecture.domain.useCase.profess.UseCaseGetFlowListProfessor
import space.jay.cleanarchitecture.domain.useCase.user.UseCaseDeleteUser
import space.jay.cleanarchitecture.domain.useCase.user.UseCaseInsertUser
import javax.inject.Inject

@HiltViewModel
class ViewModelProfessor @Inject constructor(
    private val useCaseGetFlowListProfessor: UseCaseGetFlowListProfessor,
    private val useCaseInsertUser: UseCaseInsertUser,
    private val useCaseDeleteUser: UseCaseDeleteUser,
) : BaseViewModel() {

    private val deleteUserId = mutableSetOf<Long>()
    val getFlowListProfessor = useCaseGetFlowListProfessor().asLiveData()

    fun searchProfessor(name: String?) {
        launchWithIO {
            deleteUserId.clear()
            useCaseGetFlowListProfessor.search(name)
        }
    }

    fun addUser(name: String, email: String, password: String, salary: Long) {
        launchWithIO {
            useCaseInsertUser(
                DataUser(
                    name = name,
                    email = email,
                    password = password,
                    salary = salary,
                    type = TypeUser.PROFESSOR
                )
            )
        }
    }

    fun deleteUser() {
        launchWithIO {
            useCaseDeleteUser(deleteUserId.toList())
        }
    }

    fun checkUser(isChecked: Boolean, id:Long) {
        if (isChecked) {
            deleteUserId.add(id)
        } else {
            deleteUserId.remove(id)
        }
    }

    fun isChecked(id: Long) = deleteUserId.contains(id)
}