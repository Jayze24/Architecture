package space.jay.cleanarchitecture.ui.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import space.jay.cleanarchitecture.ui.theme.CleanArchitectureTheme
import space.jay.cleanarchitecture.ui.view.clazz.list.ActivityClazz
import space.jay.cleanarchitecture.ui.view.professor.ActivityProfessor
import space.jay.cleanarchitecture.ui.view.topic.ActivityTopic


class ActivityMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            startActivity(Intent(this@ActivityMain, ActivityTopic::class.java))
                        }
                    ) {
                        Text(text = "토픽")
                    }
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Button(
                        onClick = {
                            startActivity(Intent(this@ActivityMain, ActivityProfessor::class.java))
                        }
                    ) {
                        Text(text = "교수 관리")
                    }
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Button(
                        onClick = {
                            startActivity(Intent(this@ActivityMain, ActivityClazz::class.java))
                        }
                    ) {
                        Text(text = "과목 관리")
                    }
                }

            }
        }
    }
}