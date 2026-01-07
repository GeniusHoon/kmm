package view.logview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LogEntry(
    val timestamp: String,
    val pid: String,
    val tid: String,
    val level: String,
    val tag: String,
    val message: String,
    val timeInMillis: Long
)

class LogViewModel : ViewModel() {
    var logs by mutableStateOf<List<LogEntry>>(emptyList())
        private set

    fun parseLogs(logContent: String) {
        val parsedLogs = mutableListOf<LogEntry>()
        val lines = logContent.lines()
        
        // Simple regex for the example format: 
        // 08-01 14:05:04.355  1896  1896 W vold    : Failed to ...
        val regex = Regex("""(\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}\.\d{3})\s+(\d+)\s+(\d+)\s+([A-Z])\s+([^:]+):\s+(.*)""")

        lines.forEach { line ->
            val matchResult = regex.find(line)
            if (matchResult != null) {
                val (timestamp, pid, tid, level, tag, message) = matchResult.destructured
                // In a real app, you'd parse the timestamp to milliseconds.
                // For simplicity, we'll just use a placeholder or simple hash for now if actual parsing is complex without a date library.
                // Assuming the timestamp format is MM-dd HH:mm:ss.SSS
                // We need a way to convert this to Long. 
                // Since this is KMM common code, we might not have java.time.* directly available without kotlinx-datetime.
                // For visualization purposes, we might just need relative time or index.
                // Let's try to parse it manually or use a simple heuristic.
                
                val timeInMillis = parseTimestampToMillis(timestamp)
                
                parsedLogs.add(LogEntry(timestamp, pid, tid, level, tag.trim(), message, timeInMillis))
            }
        }
        logs = parsedLogs
    }

    private fun parseTimestampToMillis(timestamp: String): Long {
        // timestamp format: 08-01 14:05:04.355
        // This is a very rough parser and assumes current year or doesn't matter for relative plotting
        // Returns a Long representing milliseconds (relative or absolute)
        try {
            val parts = timestamp.split(" ", ":", ".")
            // parts: [08-01, 14, 05, 04, 355]
            val dateParts = parts[0].split("-")
            val month = dateParts[0].toLong()
            val day = dateParts[1].toLong()
            val hour = parts[1].toLong()
            val minute = parts[2].toLong()
            val second = parts[3].toLong()
            val millis = parts[4].toLong()

            // Arbitrary conversion to a single long value for sorting/plotting
            return millis + 1000 * (second + 60 * (minute + 60 * (hour + 24 * (day + 30 * month))))
        } catch (e: Exception) {
            return 0L
        }
    }
}
