package com.example.kmm
// package 선언: 이 파일의 코드가 속한 패키지를 정의합니다.
// 자바와 유사하게 코드를 논리적으로 묶고 이름 충돌을 방지하는 역할을 합니다.

import androidx.compose.runtime.*
// import 문: 다른 패키지에 있는 클래스나 함수를 현재 파일에서 사용할 수 있도록 가져옵니다.
// '*'는 'runtime' 패키지 안에 있는 모든 public 요소를 가져오겠다는 의미입니다.
// Compose UI의 상태 관리(remember, mutableStateOf 등)에 필요한 요소들이 여기에 포함됩니다.

import view.home.HomeScreen
import view.home.HomeViewModel
import view.signin.SignInScreen
import view.signin.SignInViewModel
import view.signup.SignUpScreen
import view.signup.SignUpViewModel
import view.mypage.MyPageScreen
import view.mypage.MyPageViewModel
// 위 import 문들은 우리가 만든 각 화면(Screen)과 해당 화면의 비즈니스 로직을 담당하는 ViewModel 클래스를 가져옵니다.
// 이렇게 해야 App.kt 파일에서 이 클래스들을 이름만으로 직접 사용할 수 있습니다.

// 새로 추가된 FileManager 화면 관련 import 문

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
// import 문: Compose UI에서 UI 요소를 배치하는 데 사용되는 레이아웃 컴포저블(Column, Row)을 가져옵니다.
// Column은 자식 요소들을 세로로, Row는 가로로 배치합니다.

import androidx.compose.material3.Button
import androidx.compose.material3.Text
// import 문: Material Design 3 라이브러리에서 제공하는 UI 컴포넌트(버튼, 텍스트)를 가져옵니다.
// Material Design은 구글에서 만든 UI 디자인 가이드라인입니다.

import androidx.compose.ui.Modifier
// import 문: Compose UI 요소의 크기, 패딩, 배경색 등 다양한 속성을 설정하는 데 사용되는 Modifier 클래스를 가져옵니다.

import org.jetbrains.compose.ui.tooling.preview.Preview
// import 문: Android Studio나 IntelliJ IDEA에서 Composable 함수를 미리 볼 수 있게 해주는 @Preview 어노테이션을 가져옵니다.

enum class Screen {
    // enum class (열거형 클래스): 미리 정의된 상수들의 집합을 만듭니다.
    // 여기서는 앱의 각 화면을 나타내는 상수를 정의하여, 현재 어떤 화면이 표시되어야 하는지 쉽게 관리할 수 있도록 합니다.
    Home, SignIn, SignUp, MyPage // 새로 추가된 FileManager 화면을 enum 상수에 추가합니다.
}

@Composable
// @Composable 어노테이션: 이 함수가 Jetpack Compose UI를 그리는 함수임을 나타냅니다.
// Compose 컴파일러가 이 함수를 특별하게 처리하여 UI 계층 구조를 만들 수 있게 합니다.
@Preview
// @Preview 어노테이션: 이 Composable 함수를 IDE의 디자인 탭에서 미리 볼 수 있게 해줍니다.
// 실제 기기나 에뮬레이터 없이도 UI가 어떻게 보일지 확인할 수 있어 개발 시간을 단축시켜 줍니다.
fun App() {
    // fun: 코틀린에서 함수를 선언할 때 사용하는 키워드입니다.
    // App(): 이 함수의 이름입니다. 이 함수가 우리 앱의 최상위 UI를 구성합니다.

    var currentScreen by remember { mutableStateOf(Screen.Home) }
    // var: 변경 가능한 변수를 선언할 때 사용합니다. 여기서는 현재 화면 상태를 저장합니다.
    // currentScreen: 변수의 이름입니다.
    // by: 'delegate' 키워드로, 속성(property)의 getter/setter 로직을 다른 객체에 위임합니다.
    //     여기서는 'mutableStateOf'가 제공하는 상태 관리 기능을 'currentScreen' 변수에 연결합니다.
    // remember { ... }: Compose에서 상태(state)를 기억하게 하는 함수입니다.
    //     컴포저블이 다시 구성(recomposition)될 때도 이전에 저장된 값을 유지하도록 합니다.
    // mutableStateOf(Screen.Home): 변경 가능한 상태 객체를 생성합니다.
    //     초기값으로 Screen.Home을 설정하여 앱이 시작될 때 홈 화면이 보이도록 합니다.
    //     이 변수의 값이 변경되면, 이 변수를 사용하는 모든 Composable 함수가 자동으로 다시 그려집니다 (recomposition).

    val homeViewModel = remember { HomeViewModel() }
    val signInViewModel = remember { SignInViewModel() }
    val signUpViewModel = remember { SignUpViewModel() }
    val myPageViewModel = remember { MyPageViewModel() }
    // val: 변경 불가능한(읽기 전용) 변수를 선언할 때 사용합니다.
    // ViewModel 인스턴스들을 생성하고 remember를 사용하여 컴포저블이 다시 구성되어도 ViewModel 인스턴스가 유지되도록 합니다.
    // ViewModel은 화면의 UI 로직과 비즈니스 로직을 분리하여 관리하는 역할을 합니다.
    // MVVM(Model-View-ViewModel) 패턴에서 View(여기서는 Composable 함수)는 ViewModel을 통해 데이터를 받고 사용자 이벤트를 전달합니다.

    // FileManagerViewModel 인스턴스 생성: FilePickerFactory를 통해 플랫폼별 FilePicker 구현체를 주입합니다.

    Column { // 기존 Row를 Column으로 변경하여 버튼과 화면이 세로로 배치되도록 합니다.
        Row {
            // Row: 자식 Composable들을 가로 방향으로 순서대로 배치하는 레이아웃 컴포저블입니다.
            // Basic navigation buttons for demonstration: 이 주석은 이 버튼들이 데모용이라는 것을 설명합니다.
            Button(onClick = { currentScreen = Screen.Home }) { Text("Home") }
            // Button: 클릭 가능한 버튼 UI 컴포넌트입니다.
            // onClick = { ... }: 버튼이 클릭되었을 때 실행될 람다(익명 함수)입니다.
            //     여기서는 currentScreen 변수의 값을 Screen.Home으로 변경합니다.
            //     currentScreen이 변경되면, 아래 when 문이 다시 평가되어 HomeScreen이 표시됩니다.
            // Text("Home"): 버튼 안에 표시될 텍스트입니다.
            Button(onClick = { currentScreen = Screen.SignIn }) { Text("Sign In") }
            Button(onClick = { currentScreen = Screen.SignUp }) { Text("Sign Up") }
            Button(onClick = { currentScreen = Screen.MyPage }) { Text("My Page") }
            // FileManager 화면으로 전환하기 위한 버튼 추가
            // 새로 추가된 "File Manager" 버튼입니다. 클릭 시 currentScreen을 Screen.FileManager로 변경하여 해당 화면을 표시합니다.
        }
        // 기존 Row를 Column으로 감싸서 버튼들이 위에 있고, 실제 화면이 그 아래에 오도록 합니다.
        when (currentScreen) {
            // when: 코틀린의 조건문으로, 여러 가지 경우(case) 중 하나를 선택할 때 사용합니다.
            // 자바의 switch 문과 유사하지만 더 강력하고 유연합니다.
            // currentScreen 변수의 값에 따라 다른 Composable 함수를 호출합니다.
            Screen.Home -> HomeScreen(homeViewModel)
            // currentScreen이 Screen.Home일 경우, HomeScreen Composable 함수를 호출하고 homeViewModel을 인자로 전달합니다.
            Screen.SignIn -> SignInScreen(signInViewModel)
            // currentScreen이 Screen.SignIn일 경우, SignInScreen Composable 함수를 호출하고 signInViewModel을 인자로 전달합니다.
            Screen.SignUp -> SignUpScreen(signUpViewModel)
            // currentScreen이 Screen.SignUp일 경우, SignUpScreen Composable 함수를 호출하고 signUpViewModel을 인자로 전달합니다.
            Screen.MyPage -> MyPageScreen(myPageViewModel)
            // currentScreen이 Screen.MyPage일 경우, MyPageScreen Composable 함수를 호출하고 myPageViewModel을 인자로 전달합니다.
            // 새로 추가된 부분: currentScreen이 Screen.FileManager일 경우, FileManagerScreen Composable 함수를 호출하고 fileManagerViewModel을 인자로 전달합니다.
        }
    }
}
