package view.mypage
// package 선언: 이 파일의 코드가 속한 패키지를 정의합니다.
// 'view.mypage' 패키지는 마이페이지 화면과 관련된 코드들을 모아두는 역할을 합니다.

import androidx.compose.foundation.layout.Column
// import 문: Compose UI에서 UI 요소를 세로 방향으로 배치하는 데 사용되는 'Column' 레이아웃 컴포저블을 가져옵니다.

import androidx.compose.foundation.layout.fillMaxSize
// import 문: Modifier의 확장 함수로, UI 요소가 부모가 허용하는 최대 크기를 채우도록 하는 'fillMaxSize'를 가져옵니다.

import androidx.compose.foundation.layout.padding
// import 문: Modifier의 확장 함수로, UI 요소 주위에 여백(패딩)을 추가하는 'padding'을 가져옵니다.

import androidx.compose.material3.Text
// import 문: Material Design 3 라이브러리에서 제공하는 텍스트 UI 컴포넌트를 가져옵니다.

import androidx.compose.runtime.Composable
// import 문: 이 함수가 Jetpack Compose UI를 그리는 함수임을 나타내는 '@Composable' 어노테이션을 가져옵니다.

import androidx.compose.ui.Alignment
// import 문: Column이나 Row와 같은 레이아웃 컴포저블 내에서 자식 요소들을 정렬하는 데 사용되는 'Alignment' 객체를 가져옵니다.

import androidx.compose.ui.Modifier
// import 문: Compose UI 요소의 모양, 크기, 동작 등을 설정하는 데 사용되는 'Modifier' 클래스를 가져옵니다.

import androidx.compose.ui.unit.dp
// import 문: Compose UI에서 밀도 독립적인 픽셀(dp) 단위를 사용하기 위한 'dp' 확장 속성을 가져옵니다.
// dp는 다양한 화면 밀도에서 UI 요소의 크기를 일관되게 유지하는 데 도움을 줍니다.

@Composable
// @Composable 어노테이션: 이 함수가 Jetpack Compose UI를 그리는 함수임을 나타냅니다.
// 이 함수는 UI 계층 구조의 일부가 되며, UI 상태가 변경될 때 자동으로 다시 그려질 수 있습니다.
fun MyPageScreen(viewModel: MyPageViewModel) {
    // fun: 코틀린에서 함수를 선언할 때 사용하는 키워드입니다.
    // MyPageScreen: 이 함수의 이름입니다. 이 함수는 마이페이지 화면의 UI를 정의합니다.
    // (viewModel: MyPageViewModel): 이 함수의 매개변수입니다.
    //     MyPageScreen은 MyPageViewModel 타입의 'viewModel' 객체를 받습니다.
    //     이는 MVVM 패턴에 따라 View(MyPageScreen)가 ViewModel로부터 데이터를 받고 사용자 이벤트를 전달하기 위함입니다.

    Column(
        // Column: 자식 Composable들을 세로 방향으로 순서대로 배치하는 레이아웃 컴포저블입니다.
        // 마이페이지의 요소들(텍스트 등)을 세로로 정렬하기 위해 사용됩니다.
        modifier = Modifier
            // modifier: Composable의 모양, 크기, 동작 등을 변경하는 데 사용되는 객체입니다.
            // Modifier는 여러 함수를 체인처럼 연결하여 다양한 속성을 적용할 수 있습니다.
            .fillMaxSize()
            // .fillMaxSize(): Modifier 함수 중 하나로, Column이 부모가 허용하는 최대 크기(여기서는 전체 화면)를 채우도록 합니다.
            .padding(16.dp),
            // .padding(16.dp): Column의 모든 가장자리에 16dp의 여백(패딩)을 추가합니다.
            //     UI 요소들이 화면 가장자리에 너무 붙지 않도록 시각적인 공간을 확보합니다.
        horizontalAlignment = Alignment.CenterHorizontally
        // horizontalAlignment = Alignment.CenterHorizontally: Column 내부의 자식 요소들을 가로 방향으로 중앙에 정렬하도록 지시합니다.
    ) {
        // Column의 content 람다: Column 내부에 표시될 UI 요소들을 정의합니다.
        Text(text = "My Page")
        // Text: 화면에 텍스트를 표시하는 Composable 함수입니다.
        // text = "My Page": 표시될 텍스트 내용입니다. 마이페이지 화면의 제목 역할을 합니다.

        Text(text = "User ID: ${viewModel.userId.value}")
        // Text: 사용자 ID를 표시하는 Composable 함수입니다.
        // text = "User ID: ${viewModel.userId.value}": 표시될 텍스트 내용입니다.
        //     ${...}는 코틀린의 문자열 템플릿(String Template) 기능으로, 문자열 안에 변수나 표현식의 값을 삽입할 때 사용합니다.
        //     viewModel.userId: ViewModel에서 관리하는 사용자 ID 상태(StateFlow)에 접근합니다.
        //     .value: StateFlow의 현재 값을 가져옵니다. Compose UI는 이 .value가 변경되면 자동으로 UI를 업데이트합니다.

        Text(text = "Subscription: ${viewModel.subscriptionStatus.value}")
        // Text: 구독 상태를 표시하는 Composable 함수입니다.
        // text = "Subscription: ${viewModel.subscriptionStatus.value}": 표시될 텍스트 내용입니다.
        //     viewModel.subscriptionStatus: ViewModel에서 관리하는 구독 상태(StateFlow)에 접근합니다.
        //     .value: StateFlow의 현재 값을 가져와 문자열에 삽입합니다.

        // TODO: Add more user information and settings
        // TODO 주석: 개발자가 나중에 구현해야 할 작업이나 기능을 표시하는 표준 주석입니다.
        //     이 부분에 사용자의 프로필 사진, 설정 옵션, 기타 개인 정보 등 더 많은 UI 요소가 추가될 예정입니다.
    }
}
