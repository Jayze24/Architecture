package space.jay.cleanarchitecture.ui.view.professor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import space.jay.cleanarchitecture.ui.theme.CleanArchitectureTheme

@AndroidEntryPoint
class ActivityProfessor : ComponentActivity() {

    private val viewModelProfessor: ViewModelProfessor by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val isAdd = rememberSaveable { mutableStateOf(false) }
                    val isDelete = rememberSaveable { mutableStateOf(false) }
                    val search = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        label = { androidx.compose.material3.Text(text = "이름 검색", color = Color.Gray) },
                        value = search.value,
                        onValueChange = {
                            search.value = it
                            viewModelProfessor.searchProfessor(it.text)
                        }
                    )

                    val listProfessor by viewModelProfessor.getFlowListProfessor.observeAsState(listOf())
                    Log.d("testJay", listProfessor.toString())
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        itemsIndexed(listProfessor) { index, item ->
                            val check = rememberSaveable { mutableStateOf(false) }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp))
                                    .clip(RoundedCornerShape(8.dp))
                                    .toggleable(
                                        value = check.value,
                                        role = Role.Checkbox,
                                        onValueChange = {
                                            if (isDelete.value) {
                                                check.value = it
                                                viewModelProfessor.checkUser(it, item.id)
                                            }
                                        },
                                        enabled = isDelete.value
                                    )
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = item.name, fontSize = 14.sp, modifier = Modifier.padding(vertical = 4.dp))
                                if (isDelete.value) {
                                    Checkbox(checked = check.value, onCheckedChange = null)
                                }
                            }
                        }
                    }



                    if (isAdd.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            val name = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                            val email = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                            val password = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                            val salary = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    // 이름
                                    TextField(
                                        modifier = Modifier.weight(2f),
                                        value = name.value,
                                        onValueChange = { name.value = it },
                                        label = { Text(text = "이름", color = Color.Gray) }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    // 월급
                                    TextField(
                                        modifier = Modifier.weight(2f),
                                        value = salary.value,
                                        onValueChange = {
                                            val value = if (it.text.length > 11) {
                                                it.copy(it.text.slice(IntRange(it.text.length - 11, it.text.length - 1)),
                                                    selection = TextRange(it.selection.start - 1))
                                            } else {
                                                it
                                            }
                                            salary.value = value
                                        },
                                        label = { Text(text = "월급", color = Color.Gray) },
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Decimal
                                        )
                                    )
                                }

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    // 아이디
                                    TextField(
                                        modifier = Modifier.weight(1f),
                                        value = email.value,
                                        onValueChange = { email.value = it },
                                        label = { Text(text = "이메일", color = Color.Gray) },
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Email
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    // 패스워드
                                    TextField(
                                        modifier = Modifier.weight(1f),
                                        value = password.value,
                                        onValueChange = { password.value = it },
                                        label = { Text(text = "패스워드", color = Color.Gray) },
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Password
                                        )
                                    )
                                }

                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        viewModelProfessor.addUser(
                                            name = name.value.text,
                                            email = email.value.text,
                                            password = password.value.text,
                                            salary = salary.value.text.toLong()
                                        )
                                    }
                                ) {
                                    Text(text = "추가하기")
                                }
                            }
                        }
                    }

                    if (isDelete.value) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                viewModelProfessor.deleteUser()
                            }
                        ) {
                            Text(text = "선택된 값 삭제하기")
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                isDelete.value = false
                                isAdd.value = !isAdd.value
                            }
                        ) {
                            Text(text = "추가")
                        }
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                isAdd.value = false
                                isDelete.value = !isDelete.value
                            }
                        ) {
                            Text(text = "삭제")
                        }
                    }

                }
            }
        }
    }
}