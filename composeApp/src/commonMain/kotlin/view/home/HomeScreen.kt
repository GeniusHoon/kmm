package view.home
// package 선언: 이 파일의 코드가 속한 패키지를 정의합니다.
// 'view.home' 패키지는 홈 화면과 관련된 코드들을 모아두는 역할을 합니다.

import androidx.compose.foundation.background
// import 문: Compose UI 요소의 배경색을 설정하는 데 사용되는 'background' Modifier 함수를 가져옵니다.

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column // 파일 내용을 표시하기 위해 Column 추가
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth // 텍스트가 가로 전체를 채우도록
import androidx.compose.foundation.layout.padding // 패딩 추가
import androidx.compose.foundation.layout.Spacer // 공간을 띄우기 위해 Spacer 추가
import androidx.compose.foundation.layout.height // Spacer의 높이 설정
import androidx.compose.foundation.rememberScrollState // 스크롤 상태 기억
import androidx.compose.foundation.verticalScroll // 세로 스크롤 가능하게
import androidx.compose.material3.Text // 텍스트 표시를 위해 Material3 Text 사용

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState // StateFlow를 Compose State로 변환
import androidx.compose.runtime.getValue // State 값을 편리하게 사용

import androidx.compose.ui.Alignment
// import 문: Box나 Column, Row와 같은 레이아웃 컴포저블 내에서 자식 요소들을 정렬하는 데 사용되는 'Alignment' 객체를 가져옵니다.

import androidx.compose.ui.Modifier
// import 문: Compose UI 요소의 크기, 패딩, 배경색 등 다양한 속성을 설정하는 데 사용되는 'Modifier' 클래스를 가져옵니다.

import androidx.compose.ui.graphics.Color
// import 문: Compose UI에서 색상을 표현하는 데 사용되는 'Color' 클래스를 가져옵니다.
// Color.Magenta, Color.White 등 미리 정의된 색상이나 ARGB 값을 사용하여 색상을 지정할 수 있습니다.

import androidx.compose.foundation.Image
// import 문: Compose UI에서 이미지를 표시하는 데 사용되는 'Image' 컴포저블을 가져옵니다.

import org.jetbrains.compose.resources.painterResource
// import 문: KMM(Kotlin Multiplatform Mobile) 프로젝트에서 공통 리소스(예: 이미지)를 로드하는 데 사용되는 'painterResource' 함수를 가져옵니다.

import kmm.composeapp.generated.resources.Res
// import 문: KMM 프로젝트에서 자동으로 생성된 리소스 접근 클래스인 'Res'를 가져옵니다.
// 이 클래스를 통해 프로젝트에 포함된 이미지, 문자열 등의 리소스에 접근할 수 있습니다.

import kmm.composeapp.generated.resources.lock_icon // lock_icon.png 리소스 임포트
// import 문: 특히 'lock_icon'이라는 이름의 drawable 리소스에 직접 접근하기 위해 가져옵니다.
// 이 주석은 이 임포트가 lock_icon.png 파일을 위한 것임을 설명합니다.

import androidx.compose.ui.unit.dp // dp 단위 사용을 위해 추가

@Composable
// @Composable 어노테이션: 이 함수가 Jetpack Compose UI를 그리는 함수임을 나타냅니다.
// 이 함수는 UI 계층 구조의 일부가 되며, UI 상태가 변경될 때 자동으로 다시 그려질 수 있습니다.
fun HomeScreen(
    homeViewModel: HomeViewModel, // 기존 HomeViewModel
) {
    // fun: 코틀린에서 함수를 선언할 때 사용하는 키워드입니다.
    // HomeScreen: 이 함수의 이름입니다. 이 함수는 홈 화면의 UI를 정의합니다.
    // (homeViewModel: HomeViewModel, fileManagerViewModel: FileManagerViewModel): 이 함수의 매개변수입니다.
    //     HomeScreen은 HomeViewModel과 FileManagerViewModel 타입의 'viewModel' 객체를 받습니다.
    //     이는 MVVM 패턴에 따라 View(HomeScreen)가 ViewModel로부터 데이터를 받고 사용자 이벤트를 전달하기 위함입니다.

    // FileManagerViewModel의 selectedFileContent StateFlow를 Compose 상태로 수집합니다.
    // 이 값이 변경되면 UI가 자동으로 업데이트됩니다.
    // val selectedFile: 변경 불가능한 변수입니다.
    // by: 'delegate' 키워드로, 'fileManagerViewModel.selectedFileContent.collectAsState()'가 제공하는
    //     상태 관리 기능을 'selectedFile' 변수에 연결합니다.
    // fileManagerViewModel.selectedFileContent: FileManagerViewModel에서 노출하는 SelectedFile? 타입의 StateFlow입니다.
    // .collectAsState(): StateFlow의 값을 Compose의 State로 변환하여 Composable 함수에서 사용할 수 있도록 합니다.
    //     State 값이 변경되면 이 State를 읽는 Composable이 자동으로 다시 구성됩니다.

    Column( // Box 대신 Column을 사용하여 로고와 파일 내용을 세로로 배치합니다.
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta) // Purple background as requested
            .padding(16.dp), // 전체 Column에 패딩 추가
        horizontalAlignment = Alignment.CenterHorizontally // 자식 요소들을 가로 중앙에 정렬
    ) {
        // Box: 자식 Composable들을 겹쳐서 배치하거나, 특정 위치에 정렬할 때 사용하는 레이아웃 컴포저블입니다.
        // 여기서는 화면 전체를 채우고, 그 안에 이미지를 중앙에 배치하는 데 사용됩니다.
        Box(
            modifier = Modifier
                .fillMaxWidth() // 가로 전체를 채우도록
                .weight(1f), // 남은 공간을 최대한 차지하도록 (파일 내용과 공간 분배)
            contentAlignment = Alignment.Center
            // contentAlignment = Alignment.Center: Box 내부의 자식 요소(여기서는 Image)를 Box의 중앙에 정렬하도록 지시합니다.
        ) {
            // Box의 content 람다: Box 내부에 표시될 UI 요소들을 정의합니다.
            Image(
                // Image: 이미지를 화면에 표시하는 Composable 함수입니다.
                painter = painterResource(Res.drawable.lock_icon), // lock_icon.png 사용
                // painter: Image에 어떤 이미지를 그릴지 지정합니다.
                // painterResource(Res.drawable.lock_icon): 'painterResource' 함수를 사용하여 프로젝트 리소스에서 이미지를 로드합니다.
                //     'Res.drawable.lock_icon'은 자동으로 생성된 'Res' 클래스를 통해 'drawable' 폴더 안의 'lock_icon' 리소스에 접근합니다.
                //     이것이 우리가 BaroKey 로고로 사용할 이미지입니다.
                contentDescription = "BaroKey Logo"
                // contentDescription: 접근성(Accessibility)을 위한 설명 텍스트입니다.
                //     시각 장애가 있는 사용자가 스크린 리더를 사용할 때 이 이미지가 무엇을 나타내는지 알려줍니다.
                //     UI에는 직접 표시되지 않습니다.
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // 로고와 파일 내용 사이에 공간 추가
    }
}
