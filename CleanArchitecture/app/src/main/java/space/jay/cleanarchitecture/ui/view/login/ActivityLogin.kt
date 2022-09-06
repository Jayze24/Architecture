package space.jay.cleanarchitecture.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import space.jay.cleanarchitecture.base.WrapperResult
import space.jay.cleanarchitecture.ui.theme.CleanArchitectureTheme
import space.jay.cleanarchitecture.ui.view.main.ActivityMain

@AndroidEntryPoint
class ActivityLogin : ComponentActivity() {

    private val viewModelLogin: ViewModelLogin by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelLogin.useCaseLogin.getFlowEvent().asLiveData().observe(this) {
            if (it is WrapperResult.Success) {
                startActivity(Intent(this, ActivityMain::class.java))
                finish()
            } else if (it is WrapperResult.Error) {
                Toast.makeText(this, it.throwable.message, Toast.LENGTH_SHORT).show()
            }
        }

        setContent {
            CleanArchitectureTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "Android Architecture Test App", fontStyle = MaterialTheme.typography.h1.fontStyle)

                    Spacer(modifier = Modifier.padding(top = 32.dp))

                    val email = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                    TextField(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        label = { Text(text = "이메일", color = Color.Gray) },
                        value = email.value,
                        onValueChange = {
                            email.value = it
                        }
                    )

                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    val password = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                    TextField(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        label = { Text(text = "비밀번호", color = Color.Gray) },
                        value = password.value,
                        onValueChange = {
                            password.value = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password
                        )
                    )

                    Spacer(modifier = Modifier.padding(top = 32.dp))

                    Button(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        onClick = {
                            viewModelLogin.login(
                                email = email.value.text,
                                password = password.value.text
                            )
                        }
                    ) {
                        Text(text = "로그인")
                    }

                    Spacer(modifier = Modifier.weight(2f))
                }
            }
        }
    }
}