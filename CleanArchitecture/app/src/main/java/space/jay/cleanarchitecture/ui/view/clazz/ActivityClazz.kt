package space.jay.cleanarchitecture.ui.view.clazz

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dagger.hilt.android.AndroidEntryPoint
import space.jay.cleanarchitecture.data.repository.room.data.DataClazz
import space.jay.cleanarchitecture.ui.theme.CleanArchitectureTheme

@AndroidEntryPoint
class ActivityClazz : ComponentActivity() {

    private val viewModelClazz: ViewModelClazz by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {

                val showDialog = remember<MutableState<DataClazz?>> { mutableStateOf(null) }

                if (showDialog.value != null) {
                    Dialog(
                        onDismissRequest = {
                            showDialog.value = null
                        }
                    ) {
                        Column {
                            Text(text = "CLASS INFO")
                            Text(text = "name : ${showDialog.value!!.name}")
                            Text(text = "capacity : ${showDialog.value!!.capacity}")
                            Spacer(modifier = Modifier.height(16.dp))
                            Row {
                                Button(
                                    onClick = {
                                        viewModelClazz.delete(showDialog.value!!.id)
                                        showDialog.value = null
                                    }
                                ) {
                                    Text(text = "Delete")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(onClick = { }) {
                                    Text(text = "Add Student")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        showDialog.value = null
                                    }
                                ) {
                                    Text(text = "Close")
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val search = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        label = { androidx.compose.material3.Text(text = "이름 검색", color = Color.Gray) },
                        value = search.value,
                        onValueChange = {
                            search.value = it
                            viewModelClazz.searchClazz(it.text)
                        }
                    )

                    val listClazz by viewModelClazz.getFlowListClazz.observeAsState(listOf())
                    Log.d("testJay", listClazz.toString())
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        itemsIndexed(listClazz) { index, item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = item.name, fontSize = 14.sp, modifier = Modifier.padding(vertical = 4.dp))
                                androidx.compose.material3.Button(
                                    onClick = {

                                    }
                                ) {
                                    Text(text = "상세보기")
                                }
                            }
                        }
                    }


                    val isAdd = rememberSaveable { mutableStateOf(false) }

                    if (isAdd.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            val name = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
                            val capacity = rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

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
                                    // 정원
                                    TextField(
                                        modifier = Modifier.weight(2f),
                                        value = capacity.value,
                                        onValueChange = {
                                            val value = if (it.text.length > 3) {
                                                it.copy(it.text.slice(IntRange(0, 3)))
                                            } else {
                                                it
                                            }
                                            capacity.value = value
                                        },
                                        label = { Text(text = "정원", color = Color.Gray) },
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Decimal
                                        )
                                    )
                                }

                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        viewModelClazz.insert(
                                            name = name.value.text,
                                            capacity = capacity.value.text.toInt()
                                        )
                                    }
                                ) {
                                    Text(text = "추가하기")
                                }
                            }
                        }
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            isAdd.value = !isAdd.value
                        }
                    ) {
                        Text(text = "과목 추가추가")
                    }

                }
            }
        }
    }
}