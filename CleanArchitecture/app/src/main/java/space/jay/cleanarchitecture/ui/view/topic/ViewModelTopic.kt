package space.jay.cleanarchitecture.ui.view.topic

import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import space.jay.cleanarchitecture.base.BaseViewModel
import space.jay.cleanarchitecture.base.WrapperResult
import space.jay.cleanarchitecture.domain.entity.Topic
import space.jay.cleanarchitecture.domain.useCase.topic.UseCaseGetListTopic
import javax.inject.Inject

@HiltViewModel
class ViewModelTopic @Inject constructor(
    private val useCaseGetListTopic: UseCaseGetListTopic,
) : BaseViewModel() {

    private val _flowListTopic = MutableSharedFlow<List<Topic>>()
    val flowListTopic = _flowListTopic.asLiveData()

    init {
        getListTopic()
    }

    private fun getListTopic() {
        launchWithIO {
            useCaseGetListTopic(1).let {
                if (it is WrapperResult.Success) {
                    it.value
                } else {
                    null
                }
            }?.also {
                _flowListTopic.emit(it)
            }
        }
    }

}