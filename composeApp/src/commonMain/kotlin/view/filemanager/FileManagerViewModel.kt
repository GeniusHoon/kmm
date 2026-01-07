package view.filemanager

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FileManagerViewModel : ViewModel() {
    var files by mutableStateOf<List<String>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onFilesDropped(newFiles: List<String>) {
        val hasLogcat = newFiles.any { it.contains("logcat.txt") }
        
        if (hasLogcat) {
            files = newFiles
            errorMessage = null
        } else {
            errorMessage = "logcat.txt를 주세요!"
        }
    }

    fun clearError() {
        errorMessage = null
    }
}
