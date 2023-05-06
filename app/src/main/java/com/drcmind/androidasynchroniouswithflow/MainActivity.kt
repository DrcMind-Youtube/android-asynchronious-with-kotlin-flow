package com.drcmind.androidasynchroniouswithflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drcmind.androidasynchroniouswithflow.ui.theme.AndroidAsynchroniousWithFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAsynchroniousWithFlowTheme {
                MyScreen()
            }
        }
    }
}

@Composable
fun MyScreen(viewModel: MainViewModel = viewModel()) {
    val quotesList =
        viewModel.getQuotes.collectAsState(initial = Result.Loading)

        Column(modifier = Modifier.padding(16.dp)) {


            val state = quotesList.value

            if(state is Result.Loading )
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            else if (state is Result.Success){



                LazyColumn {

                    items(items = state.data) { user ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = user.text, style = TextStyle(
                                fontSize = 18.sp,
                            ))
                            Text(text = user.author, style = TextStyle(
                                fontWeight = FontWeight.Bold,
                            ))
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider()
                        }
                    }
                }
            }
        }
}


@Preview
@Composable
fun DefaultPreview() {
    AndroidAsynchroniousWithFlowTheme {
        MyScreen()
    }
}
