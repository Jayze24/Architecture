package space.jay.cleanarchitecture.ui.view.clazz.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import space.jay.cleanarchitecture.data.repository.room.data.DataClazz
import space.jay.cleanarchitecture.ui.theme.CleanArchitectureTheme

@AndroidEntryPoint
class ActivityClazzInfo : ComponentActivity() {

    private val viewModelClazzInfo: ViewModelClazzInfo by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {
                val clazzInfo by viewModelClazzInfo.getFlowClazzInfo.observeAsState()

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = clazzInfo?.name
                                ?: "No Class Name")
                    Text(text = "capacity : ${clazzInfo?.capacity}")

                    val listClazzStudent by viewModelClazzInfo.getFlowListClazzStudent.observeAsState()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {
                        listClazzStudent?.also {
                            itemsIndexed(it) { index, item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "No.$index ${item.name}", fontSize = 14.sp, modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = {

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
        }
    }
}