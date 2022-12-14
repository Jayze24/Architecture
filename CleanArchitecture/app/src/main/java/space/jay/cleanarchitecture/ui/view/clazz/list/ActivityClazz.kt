package space.jay.cleanarchitecture.ui.view.clazz.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
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
import space.jay.cleanarchitecture.ui.view.clazz.detail.ActivityClazzInfo
import space.jay.cleanarchitecture.ui.view.clazz.detail.EXTRA_CLAZZ_ID

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
                        val listClazzStudent by viewModelClazz.getFlowListClazzStudent.observeAsState()
                        Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {
                            Text(text = "CLASS INFO")
                            Text(text = "name : ${showDialog.value?.name}")
                            Text(text = "capacity : ${showDialog.value?.capacity}")
                            Spacer(modifier = Modifier.height(16.dp))

                            LazyColumn(modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)) {
                                listClazzStudent?.also {
                                    itemsIndexed(it) { index, item ->
                                        Text(
                                            modifier = Modifier.padding(4.dp),
                                            text = "No.${index} name : ${item.name}, grade : ${item.grade}"
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Row {
                                Button(
                                    onClick = {
                                        showDialog.value?.id?.also {
                                            viewModelClazz.delete(it)
                                        }
                                        showDialog.value = null
                                    }
                                ) {
                                    Text(text = "Delete")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        startActivity(
                                            Intent(this@ActivityClazz, ActivityClazzInfo::class.java).apply {
                                                putExtra(EXTRA_CLAZZ_ID, showDialog.value?.id)
                                            }
                                        )
                                    }
                                ) {
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
                        label = { androidx.compose.material3.Text(text = "?????? ??????", color = Color.Gray) },
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
                                        if (item is DataClazz) {
                                            viewModelClazz.searchClazz(item.id)
                                            showDialog.value = item
                                        }
                                    }
                                ) {
                                    Text(text = "?????? ??????")
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
                                    // ??????
                                    TextField(
                                        modifier = Modifier.weight(2f),
                                        value = name.value,
                                        onValueChange = { name.value = it },
                                        label = { Text(text = "??????", color = Color.Gray) }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    // ??????
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
                                        label = { Text(text = "??????", color = Color.Gray) },
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
                                    Text(text = "????????????")
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
                        Text(text = "?????? ????????????")
                    }

                }
            }
        }
    }
}