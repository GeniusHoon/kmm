package view.home

import androidx.lifecycle.ViewModel

// package 선언: 이 파일의 코드가 속한 패키지를 정의합니다.
// 'view.home' 패키지는 홈 화면과 관련된 코드들을 모아두는 역할을 합니다.

// import 문: 다른 패키지에 있는 클래스나 함수를 현재 파일에서 사용할 수 있도록 가져옵니다.
// 여기서는 'moko-mvvm' 라이브러리에서 제공하는 'ViewModel' 클래스를 가져옵니다.
// KMM 프로젝트의 commonMain 모듈에서는 Android의 'androidx.lifecycle.ViewModel' 대신
// 이처럼 플랫폼 독립적인 ViewModel을 사용해야 합니다.
// ViewModel은 UI와 비즈니스 로직을 분리하여 관리하는 MVVM 패턴의 핵심 구성 요소입니다.

class HomeViewModel : ViewModel() {
    // class 선언: 'HomeViewModel'이라는 이름의 클래스를 정의합니다.
    // HomeViewModel: 이 클래스의 이름입니다.
    // : ViewModel(): 'HomeViewModel' 클래스가 'ViewModel' 클래스를 상속(extends)한다는 의미입니다.
    //     상속을 통해 'ViewModel'이 제공하는 생명주기 관리 및 데이터 유지와 같은 기능을 'HomeViewModel'도 사용할 수 있게 됩니다.
    //     괄호 '()'는 부모 클래스의 기본 생성자를 호출한다는 의미입니다.

    // TODO: Add home screen specific business logic here
    // TODO 주석: 개발자가 나중에 구현해야 할 작업이나 기능을 표시하는 표준 주석입니다.
    //     이 부분에 홈 화면과 관련된 데이터 처리, 네트워크 통신, 사용자 상호작용 로직 등이 추가될 예정입니다.
    //     예를 들어, 홈 화면에 표시될 데이터를 로드하거나, 특정 이벤트에 응답하는 함수들을 여기에 작성할 수 있습니다.
}
