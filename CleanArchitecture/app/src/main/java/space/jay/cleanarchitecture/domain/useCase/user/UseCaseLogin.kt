package space.jay.cleanarchitecture.domain.useCase.user

import android.util.Patterns
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.base.WrapperResult
import space.jay.cleanarchitecture.domain.boundary.BoundaryLogin
import space.jay.cleanarchitecture.domain.entity.user.User

class UseCaseLogin(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryLogin,
) {

    private val _event by lazy { MutableStateFlow<WrapperResult<User?>?>(null) }
    fun getFlowEvent(): Flow<WrapperResult<User?>> = _event.filterNotNull()

    suspend operator fun invoke(email: String, password: String) = withContext(dispatcher) {
        val e = email.trim()
        val p = password.trim()

        val result = when {
            e.isEmpty() -> WrapperResult.Error(Throwable(message = "이메일이 비어 있음"))
            !Patterns.EMAIL_ADDRESS.matcher(e).matches() -> WrapperResult.Error(Throwable(message = "이메일이 형식이 맞지 않음"))
            p.isEmpty() -> WrapperResult.Error(Throwable(message = "비밀번호가 비어 있음"))
            p.length < 6 -> WrapperResult.Error(Throwable(message = "비밀번호는 6자리 이상"))
            else -> {
                val user = repository.getUser(email = e, password = p)
                if (user == null) {
                    WrapperResult.Error(Throwable(message = "존재하지 않는 유저"))
                } else {
                    WrapperResult.Success<User>(user)
                }
            }
        }

        _event.emit(result)
    }
}