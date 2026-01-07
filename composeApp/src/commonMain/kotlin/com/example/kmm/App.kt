package com.example.kmm

import androidx.compose.runtime.*
import view.home.HomeScreen
import view.home.HomeViewModel
import view.signin.SignInScreen
import view.signin.SignInViewModel
import view.signup.SignUpScreen
import view.signup.SignUpViewModel
import view.mypage.MyPageScreen
import view.mypage.MyPageViewModel
import view.filemanager.FileManagerScreen
import view.filemanager.FileManagerViewModel
import view.logview.LogViewScreen
import view.logview.LogViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class Screen {
    Home, SignIn, SignUp, MyPage, FileManager, LogView
}

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }

    val homeViewModel = remember { HomeViewModel() }
    val signInViewModel = remember { SignInViewModel() }
    val signUpViewModel = remember { SignUpViewModel() }
    val myPageViewModel = remember { MyPageViewModel() }
    val fileManagerViewModel = remember { FileManagerViewModel() }
    val logViewModel = remember { LogViewModel() }

    Column {
        Row {
            Button(onClick = { currentScreen = Screen.Home }) { Text("Home") }
            Button(onClick = { currentScreen = Screen.SignIn }) { Text("Sign In") }
            Button(onClick = { currentScreen = Screen.SignUp }) { Text("Sign Up") }
            Button(onClick = { currentScreen = Screen.MyPage }) { Text("My Page") }
            Button(onClick = { currentScreen = Screen.FileManager }) { Text("File Manager") }
            Button(onClick = { currentScreen = Screen.LogView }) { Text("Log View") }
        }
        when (currentScreen) {
            Screen.Home -> HomeScreen(homeViewModel)
            Screen.SignIn -> SignInScreen(signInViewModel)
            Screen.SignUp -> SignUpScreen(signUpViewModel)
            Screen.MyPage -> MyPageScreen(myPageViewModel)
            Screen.FileManager -> FileManagerScreen(fileManagerViewModel, logViewModel)
            Screen.LogView -> LogViewScreen(logViewModel)
        }
    }
}
