package com.example.kmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import model.FilePickerFactory
import model.AndroidFilePicker // AndroidFilePicker 클래스 임포트

class MainActivity : ComponentActivity() {

    // ActivityResultLauncher를 선언합니다.
    // 이 런처는 파일 선택 Intent를 실행하고 그 결과를 받기 위해 사용됩니다.
    private lateinit var pickFileLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        // override fun: 부모 클래스(ComponentActivity)의 'onCreate' 함수를 재정의(오버라이드)합니다.
        // onCreate: Activity가 처음 생성될 때 호출되는 생명주기 메서드입니다.
        // (savedInstanceState: Bundle?): Activity가 이전에 종료되었다가 다시 생성될 때,
        //     이전 상태 정보를 담고 있는 Bundle 객체를 받습니다. 처음 생성될 때는 null입니다.

        enableEdgeToEdge()
        // enableEdgeToEdge(): 앱의 UI가 화면의 가장자리(노치, 펀치홀, 시스템 바 등)까지 확장되도록 설정합니다.
        super.onCreate(savedInstanceState)
        // super.onCreate(savedInstanceState): 부모 클래스(ComponentActivity)의 onCreate 메서드를 호출합니다.
        //     이는 Activity의 기본적인 초기화 작업을 수행합니다.

        // FilePickerFactory를 현재 Activity Context로 초기화합니다.
        // 이렇게 해야 commonMain에서 expect로 선언된 FilePickerFactory가 Android 플랫폼에서 제대로 작동합니다.
        FilePickerFactory.init(this)
        // FilePickerFactory.init(this): commonMain의 expect object FilePickerFactory에 대한
        //     actual 구현인 Android의 FilePickerFactory.init 함수를 호출합니다.
        //     'this'는 현재 MainActivity 인스턴스(즉, Activity Context)를 의미하며,
        //     이를 FilePickerFactory에 전달하여 AndroidFilePicker가 Context를 사용할 수 있도록 합니다.

        // ActivityResultLauncher를 등록합니다.
        // registerForActivityResult는 Activity의 생명주기에 안전하게 결과를 처리할 수 있도록 합니다.
        // ActivityResultContracts.OpenDocument()는 문서 선택기를 열어 파일을 선택하게 하는 계약입니다.
        pickFileLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
            // registerForActivityResult(...): ActivityResultLauncher를 등록하고 초기화하는 메서드입니다.
            // ActivityResultContracts.OpenDocument(): 파일 선택기를 열어 사용자가 문서를 선택하도록 하는 표준 계약입니다.
            // { uri: Uri? -> ... }: 파일 선택 결과가 돌아왔을 때 실행될 람다(익명 함수)입니다.
            //     'uri'는 사용자가 선택한 파일의 URI이며, 선택을 취소하면 null이 됩니다.

            // FilePickerFactory에서 AndroidFilePicker 인스턴스를 가져옵니다.
            // 이 인스턴스는 파일 선택 결과를 처리할 수 있는 onFilePicked 함수를 가지고 있습니다.
            val filePicker = FilePickerFactory.getFilePicker() as? AndroidFilePicker
            // FilePickerFactory.getFilePicker(): 초기화된 FilePicker 인스턴스를 가져옵니다.
            // as? AndroidFilePicker: 안전한 캐스팅(safe cast) 연산자입니다.
            //     getFilePicker()가 반환하는 FilePicker 인터페이스 타입의 객체를 AndroidFilePicker 타입으로 캐스팅합니다.
            //     만약 캐스팅이 불가능하면 null을 반환합니다.
            filePicker?.onFilePicked(uri) // 선택된 URI를 AndroidFilePicker에 전달합니다.
            // filePicker가 null이 아니면 (?.), onFilePicked 함수를 호출하고 선택된 uri를 인자로 전달합니다.
            // 이 함수는 AndroidFilePicker 내부의 CompletableDeferred를 완료하여 파일 선택을 기다리던 코루틴을 재개합니다.
        }

        // FilePickerFactory에서 AndroidFilePicker 인스턴스를 가져와서
        // launchFilePicker 콜백을 설정합니다.
        // 이 콜백은 AndroidFilePicker.pickFile()이 호출될 때 실제 ActivityResultLauncher를 실행하는 역할을 합니다.
        (FilePickerFactory.getFilePicker() as? AndroidFilePicker)?.launchFilePicker = { intent ->
            // (FilePickerFactory.getFilePicker() as? AndroidFilePicker)?: FilePickerFactory에서 AndroidFilePicker 인스턴스를 가져옵니다.
            // ?.launchFilePicker = { intent -> ... }: AndroidFilePicker의 'launchFilePicker' 변수에 람다 함수를 할당합니다.
            //     이 람다 함수는 AndroidFilePicker.pickFile() 메서드에서 호출될 것입니다.
            //     'intent'는 AndroidFilePicker에서 생성된 파일 선택 Intent입니다.

            // pickFileLauncher.launch(arrayOf("*/*")) 대신 intent를 직접 사용합니다.
            // OpenDocument 계약은 기본적으로 "*/*"를 처리하므로, 여기서는 인텐트만 넘겨줍니다.
            pickFileLauncher.launch(arrayOf("*/*")) // 모든 파일 타입 허용
            // pickFileLauncher.launch(arrayOf("*/*")): 등록된 ActivityResultLauncher를 실행하여 파일 선택기를 띄웁니다.
            //     arrayOf("*/*")는 모든 파일 타입을 선택할 수 있도록 허용하는 MIME 타입 배열입니다.
        }

        setContent {
            // setContent { ... }: Activity의 UI 콘텐츠를 Jetpack Compose로 설정합니다.
            App()
            // App(): commonMain에 정의된 최상위 Composable 함수를 호출하여 앱의 UI를 그립니다.
        }
    }
}

@Preview
// @Preview 어노테이션: 이 Composable 함수를 IDE의 디자인 탭에서 미리 볼 수 있게 해줍니다.
// 실제 기기나 에뮬레이터 없이도 UI가 어떻게 보일지 확인할 수 있어 개발 시간을 단축시켜 줍니다.
@Composable
fun AppAndroidPreview() {
    // fun: 코틀린에서 함수를 선언할 때 사용하는 키워드입니다.
    // AppAndroidPreview(): 이 함수의 이름입니다. Android 플랫폼에서 App Composable을 미리 보기 위한 함수입니다.
    App()
    // App(): commonMain에 정의된 App Composable 함수를 호출합니다.
}
