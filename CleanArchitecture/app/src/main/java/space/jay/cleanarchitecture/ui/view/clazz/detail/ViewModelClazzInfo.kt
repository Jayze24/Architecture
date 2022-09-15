package space.jay.cleanarchitecture.ui.view.clazz.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import space.jay.cleanarchitecture.base.BaseViewModel
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseAddStudent
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseGetFlowClazzInfo
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseGetFlowListClazzStudent
import space.jay.cleanarchitecture.domain.useCase.student.UseCaseGetFlowListStudent
import javax.inject.Inject

const val EXTRA_CLAZZ_ID = "EXTRA_CLAZZ_ID"

@HiltViewModel
class ViewModelClazzInfo @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCaseGetFlowClazzInfo: UseCaseGetFlowClazzInfo,
    private val useCaseAddStudent: UseCaseAddStudent,
    private val useCaseGetFlowListStudent: UseCaseGetFlowListStudent
) : BaseViewModel() {

    val idClazz = savedStateHandle.get<Long>(EXTRA_CLAZZ_ID) ?: -1

    val getFlowClazzInfo = useCaseGetFlowClazzInfo(idClazz).asLiveData()
    val getFlowListStudent = useCaseGetFlowListStudent().asLiveData()

    fun addStudent(idStudent: Long) {
        launchWithIO {
            useCaseAddStudent(idClazz, listOf(idStudent))
        }
    }

}