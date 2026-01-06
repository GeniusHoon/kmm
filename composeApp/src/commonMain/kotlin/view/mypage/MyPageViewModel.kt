package view.mypage
// package 선언: 이 파일의 코드가 속한 패키지를 정의합니다.
// 'view.mypage' 패키지는 마이페이지 화면과 관련된 코드들을 모아두는 역할을 합니다.

// import 문: 다른 패키지에 있는 클래스나 함수를 현재 파일에서 사용할 수 있도록 가져옵니다.
// 여기서는 'moko-mvvm' 라이브러리에서 제공하는 'ViewModel' 클래스를 가져옵니다.
// KMM 프로젝트의 commonMain 모듈에서는 Android의 'androidx.lifecycle.ViewModel' 대신
// 이처럼 플랫폼 독립적인 ViewModel을 사용해야 합니다.
// ViewModel은 UI와 비즈니스 로직을 분리하여 관리하는 MVVM 패턴의 핵심 구성 요소입니다.

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
// import 문: 코틀린 코루틴(Coroutines)의 Flow API에서 'MutableStateFlow' 클래스를 가져옵니다.
// MutableStateFlow는 변경 가능한 상태를 나타내는 Flow로, 값을 발행(emit)하고 구독(collect)할 수 있습니다.
// UI 상태를 ViewModel에서 관리하고 View로 전달하는 데 주로 사용됩니다.

import kotlinx.coroutines.flow.StateFlow
// import 문: 코틀린 코루틴(Coroutines)의 Flow API에서 'StateFlow' 인터페이스를 가져옵니다.
// StateFlow는 읽기 전용 상태를 나타내는 Flow로, 항상 최신 값을 가지고 있으며, 값이 변경될 때마다 구독자에게 알립니다.
// ViewModel 내부에서는 MutableStateFlow로 값을 변경하고, 외부(View)에는 읽기 전용인 StateFlow로 노출하여
// 외부에서 직접 상태를 변경하는 것을 방지합니다.

class MyPageViewModel : ViewModel() {
    // class 선언: 'MyPageViewModel'이라는 이름의 클래스를 정의합니다.
    // MyPageViewModel: 이 클래스의 이름입니다.
    // : ViewModel(): 'MyPageViewModel' 클래스가 'ViewModel' 클래스를 상속(extends)한다는 의미입니다.
    //     상속을 통해 'ViewModel'이 제공하는 생명주기 관리 및 데이터 유지와 같은 기능을 'MyPageViewModel'도 사용할 수 있게 됩니다.
    //     괄호 '()'는 부모 클래스의 기본 생성자를 호출한다는 의미입니다.

    private val _userId = MutableStateFlow("Guest")
    // private: 이 변수가 이 클래스 내부에서만 접근 가능하도록 제한하는 접근 제한자입니다.
    // val: 변경 불가능한(읽기 전용) 변수를 선언할 때 사용합니다.
    // _userId: 변수의 이름입니다. 일반적으로 ViewModel에서 내부적으로 관리하는 MutableStateFlow 변수에는 '_' 접두사를 붙입니다.
    // MutableStateFlow("Guest"): 'MutableStateFlow' 객체를 생성하고 초기값으로 "Guest" 문자열을 설정합니다.
    //     이 변수는 사용자의 ID 상태를 나타내며, 값이 변경될 수 있습니다.

    val userId: StateFlow<String> = _userId
    // val: 변경 불가능한(읽기 전용) 변수를 선언합니다.
    // userId: 변수의 이름입니다. 이 변수는 View에서 사용자 ID를 관찰(observe)하는 데 사용됩니다.
    // : StateFlow<String>: 이 변수의 타입이 'String' 값을 발행하는 'StateFlow'임을 명시합니다.
    // = _userId: 내부적으로 관리하는 '_userId' (MutableStateFlow)를 외부에는 읽기 전용인 'StateFlow'로 노출합니다.
    //     이렇게 하면 View에서는 userId의 값을 읽을 수만 있고, 직접 변경할 수는 없습니다.

    private val _subscriptionStatus = MutableStateFlow("Free Tier")
    // private val _subscriptionStatus: 사용자 구독 상태를 내부적으로 관리하는 MutableStateFlow 변수입니다.
    // MutableStateFlow("Free Tier"): 초기값으로 "Free Tier" 문자열을 설정합니다.

    val subscriptionStatus: StateFlow<String> = _subscriptionStatus
    // val subscriptionStatus: 외부(View)에 노출되는 읽기 전용 구독 상태 StateFlow입니다.
    // = _subscriptionStatus: 내부 MutableStateFlow를 외부 StateFlow로 노출합니다.

    init {
        // init 블록: 클래스 인스턴스가 생성될 때(객체가 만들어질 때) 가장 먼저 실행되는 초기화 블록입니다.
        //     주 생성자(primary constructor)의 일부로 간주되며, 객체 생성 시 필요한 초기 설정을 수행합니다.

        // TODO: Load actual user data
        // TODO 주석: 개발자가 나중에 구현해야 할 작업이나 기능을 표시하는 표준 주석입니다.
        //     이 부분에 실제 사용자 데이터를 서버나 로컬 저장소에서 불러오는 로직이 추가될 예정입니다.
        _userId.value = "john.doe@example.com"
        // _userId.value = "john.doe@example.com": _userId MutableStateFlow의 현재 값을 "john.doe@example.com"으로 변경합니다.
        //     .value는 MutableStateFlow의 값을 직접 설정하거나 가져올 때 사용합니다.
        //     이 값이 변경되면 userId를 구독하고 있는 모든 Composable 함수가 자동으로 업데이트됩니다.
        _subscriptionStatus.value = "Premium"
        // _subscriptionStatus.value = "Premium": _subscriptionStatus MutableStateFlow의 현재 값을 "Premium"으로 변경합니다.
        //     마찬가지로 이 값이 변경되면 subscriptionStatus를 구독하고 있는 Composable 함수가 업데이트됩니다.
    }

    // TODO: Add more business logic related to user profile, settings, etc.
    // TODO 주석: 개발자가 나중에 구현해야 할 작업이나 기능을 표시하는 표준 주석입니다.
    //     이 부분에 사용자 프로필 업데이트, 설정 변경, 구독 관리 등 마이페이지와 관련된 추가적인 비즈니스 로직 함수들이 구현될 예정입니다.
}
