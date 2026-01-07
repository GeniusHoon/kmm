package view.logview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LogViewScreen(viewModel: LogViewModel) {
    val logs = viewModel.logs
    var zoomLevel by remember { mutableStateOf(1f) }
    var scrollOffset by remember { mutableStateOf(0f) }
    var mouseX by remember { mutableStateOf(0f) }
    var isHovering by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Visualization Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        zoomLevel *= zoom
                        scrollOffset += pan.x
                    }
                }
                .onPointerEvent(PointerEventType.Move) {
                    val position = it.changes.first().position
                    mouseX = position.x
                    isHovering = true
                }
                .onPointerEvent(PointerEventType.Exit) {
                    isHovering = false
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height
                
                if (logs.isNotEmpty()) {
                    val startTime = logs.first().timeInMillis
                    val endTime = logs.last().timeInMillis
                    val totalTime = endTime - startTime
                    
                    // Draw logs
                    logs.forEach { log ->
                        if (log.level == "W") {
                            val relativeTime = log.timeInMillis - startTime
                            // Apply zoom and scroll
                            val x = (relativeTime.toFloat() / totalTime.toFloat()) * width * zoomLevel + scrollOffset
                            
                            if (x in 0f..width) {
                                drawLine(
                                    color = Color.Red,
                                    start = Offset(x, 0f),
                                    end = Offset(x, height),
                                    strokeWidth = 2f
                                )
                            }
                        }
                    }
                }

                // Draw mouse cursor line
                if (isHovering) {
                    drawLine(
                        color = Color.Blue,
                        start = Offset(mouseX, 0f),
                        end = Offset(mouseX, height),
                        strokeWidth = 2f
                    )
                }
            }
        }

        // Raw Data Text Box
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        ) {
            items(logs) { log ->
                Text(
                    text = "${log.timestamp} ${log.pid} ${log.tid} ${log.level} ${log.tag}: ${log.message}",
                    fontSize = 12.sp,
                    color = if (log.level == "W") Color.Red else Color.Black
                )
            }
        }
    }
}
