package view
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmm.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen() {
    // 배경색 설정 (보라색)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red) // 진한 보라색
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. 자물쇠 이미지
        // resources/drawable 에 lock_icon.xml 또는 png가 있다고 가정
        Icon(
            painter = painterResource(Res.drawable.lock_icon),
            contentDescription = "Lock Icon",
            modifier = Modifier.size(100.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. 제목: Barokey
        Text(
            text = "Barokey",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(48.dp))

        // 3. 버튼: Sign In
        Button(
            onClick = { /* 로그인 로직 */ },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Sign In", color = Color(0xFF6200EE), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 4. 버튼: Sign Up
        OutlinedButton(
            onClick = { /* 회원가입 로직 */ },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            border = BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
