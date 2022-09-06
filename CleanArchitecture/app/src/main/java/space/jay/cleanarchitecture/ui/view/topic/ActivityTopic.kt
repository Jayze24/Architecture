package space.jay.cleanarchitecture.ui.view.topic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import space.jay.cleanarchitecture.ui.theme.CleanArchitectureTheme

@AndroidEntryPoint
class ActivityTopic : ComponentActivity() {

    private val viewModelTopic: ViewModelTopic by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {
                val listTopic = viewModelTopic.flowListTopic.observeAsState()
                val shape = RoundedCornerShape(8.dp)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 120.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    listTopic.value?.also { list ->
                        items(list) { item ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .border(1.dp, Color.Black, shape = shape)
                                    .clip(shape = shape)
                                    .padding(8.dp)
                            ) {
                                Text(text = item.title ?: "NO TITLE", fontSize = 16.sp)
                                item.description?.also {
                                    Spacer(modifier = Modifier.padding(top = 8.dp))
                                    Text(text =  it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}