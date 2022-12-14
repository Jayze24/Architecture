package space.jay.cleanarchitecture.ui.view.clazz.list

import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import space.jay.cleanarchitecture.base.BaseViewModel
import space.jay.cleanarchitecture.base.WrapperResult
import space.jay.cleanarchitecture.data.repository.room.data.DataClazz
import space.jay.cleanarchitecture.domain.entity.Clazz
import space.jay.cleanarchitecture.domain.entity.Topic
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseAddStudent
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseDeleteClazz
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseGetFlowListClazz
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseGetFlowListClazzStudent
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseInsertClazz
import space.jay.cleanarchitecture.domain.useCase.topic.UseCaseGetListTopic
import javax.inject.Inject

@HiltViewModel
class ViewModelClazz @Inject constructor(
    private val useCaseGetFlowListClazz: UseCaseGetFlowListClazz,
    private val useCaseInsertClazz: UseCaseInsertClazz,
    private val useCaseDeleteClazz: UseCaseDeleteClazz,
    private val useCaseGetFlowListClazzStudent: UseCaseGetFlowListClazzStudent
) : BaseViewModel() {

    val getFlowListClazz = useCaseGetFlowListClazz().asLiveData()
    val getFlowListClazzStudent = useCaseGetFlowListClazzStudent.invoke().asLiveData()

    fun searchClazz(id: Long) {
        launchWithIO {
            useCaseGetFlowListClazzStudent.search(id)
        }
    }

    fun searchClazz(name: String?) {
        launchWithIO {
            useCaseGetFlowListClazz.search(name)
        }
    }

    fun insert(name: String, capacity : Int = 40) {
        launchWithIO {
            useCaseInsertClazz(
                data = DataClazz(
                    name = name,
                    capacity = capacity
                )
            )
        }
    }

    fun delete(id: Long) {
        launchWithIO {
            useCaseDeleteClazz(id)
        }
    }

}