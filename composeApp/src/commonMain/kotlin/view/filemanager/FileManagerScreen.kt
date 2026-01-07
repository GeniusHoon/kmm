package view.filemanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import view.logview.LogViewModel

@Composable
fun FileManagerScreen(
    viewModel: FileManagerViewModel,
    logViewModel: LogViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Drag and drop files here (must include logcat.txt)")
        
        // In a real desktop application, you would use a library or platform-specific code
        // to handle drag and drop. Since this is a shared UI, we'll simulate the drop
        // or assume the platform implementation is hooked up.
        // For now, we just display the list of files if any.
        
        viewModel.files.forEach { file ->
            Text(text = file)
        }

        Button(onClick = {
            // Simulate loading a log file
            val sampleLog = """
                08-01 14:05:04.355  1896  1896 W vold    : Failed to LOOP_GET_STATUS64 /dev/block/loop3: No such device or address
                08-01 14:05:04.360  1896  1896 D vold    : Operation successful
                08-01 14:05:04.400  1896  1896 W vold    : Another warning here
                08-01 14:05:05.000  1896  1896 I vold    : Info message
            """.trimIndent()
            
            // In a real app, you would read the file content here
            logViewModel.parseLogs(sampleLog)
            viewModel.onFilesDropped(listOf("logcat.txt"))
        }) {
            Text("Load Sample Log (Simulate Drop)")
        }

        if (viewModel.errorMessage != null) {
            AlertDialog(
                onDismissRequest = { viewModel.clearError() },
                confirmButton = {
                    Button(onClick = { viewModel.clearError() }) {
                        Text("OK")
                    }
                },
                title = { Text("Error") },
                text = { Text(viewModel.errorMessage ?: "") }
            )
        }
    }
}
