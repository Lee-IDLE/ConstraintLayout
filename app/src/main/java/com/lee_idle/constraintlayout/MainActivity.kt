package com.lee_idle.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.lee_idle.constraintlayout.ui.theme.ConstraintLayoutTheme
import java.util.IllegalFormatWidthException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
/*
@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 400.dp, height = 220.dp)){
        // createRefs()를 사용해 ConstraintLayout에서 각 컴포저블의 참조를 만든다.(createRefFor()도 있다)
        val (button1, button2, button3) = createRefs()

        // constrainAs를 사용해 button1 참조에 할당
        MyButton(text = "Button1", modifier = Modifier.constrainAs(button1){
            //top.linkTo(parent.top, margin = 60.dp)
            // 편향을 적용해 상대적인 위치 제약 적용(부모 폭의 75%위치에 지정)
            //linkTo(parent.start, parent.end, bias = 0.75f)

            // 반대 제약
            //start.linkTo(parent.start)
            //end.linkTo(parent.end)
            //linkTo(parent.start, parent.end)

            // 중앙 배치만이 목적이라면 다음으로도 구현할 수 있다
            //centerVerticallyTo(parent)
            //centerHorizontallyTo(parent)

            top.linkTo(parent.top)
            bottom.linkTo(button2.top)

            // 편향을 100%로 설정해도 margin 때문에 띄어져 있다.
            //linkTo(parent.start, parent.end, endMargin = 30.dp, bias = 1.0f)
            // 편향 설정 안 해도 마진은 컴포넌트의 위치에 영향을 끼친다.
            linkTo(parent.start, parent.end, startMargin = 30.dp, endMargin = 50.dp)
        })

        MyButton(text = "Button2", modifier = Modifier.constrainAs(button2){
            centerHorizontallyTo(parent)
            top.linkTo(button1.bottom)
            bottom.linkTo(parent.bottom)
        })
    }
}
*/

/*
@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 400.dp, height = 220.dp)) {
        // createRefs()를 사용해 ConstraintLayout에서 각 컴포저블의 참조를 만든다.(createRefFor()도 있다)
        val (button1, button2, button3) = createRefs()

        // 체인
        createHorizontalChain(button1, button2, button3, chainStyle = ChainStyle.SpreadInside)

        MyButton(text = "Button1", modifier = Modifier.constrainAs(button1){
            centerVerticallyTo(parent)
        })
        MyButton(text = "Button2", modifier = Modifier.constrainAs(button2){
            centerVerticallyTo(parent)
        })
        MyButton(text = "Button3", modifier = Modifier.constrainAs(button3){
            centerVerticallyTo(parent)
        })
    }
}
 */

/*
@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 400.dp, height = 220.dp)) {
        // createRefs()를 사용해 ConstraintLayout에서 각 컴포저블의 참조를 만든다.(createRefFor()도 있다)
        val (button1, button2, button3) = createRefs()

        // 가이드라인
        // createGuidelineFromStart(fraction = .25f)
        // createGuidelineFromBottom(offset = 60.dp)
        val guide = createGuidelineFromStart(fraction = .60f)

        MyButton(text = "Button1", modifier = Modifier.constrainAs(button1){
            top.linkTo(parent.top, margin = 30.dp)
            end.linkTo(guide, margin = 30.dp)
        })

        MyButton(text = "Button2", modifier = Modifier.constrainAs(button2){
            top.linkTo(button1.bottom, margin = 20.dp)
            start.linkTo(guide, margin = 40.dp)
        })

        MyButton(text = "Button3", modifier = Modifier.constrainAs(button3){
            top.linkTo(button2.bottom, margin = 40.dp)
            end.linkTo(guide, margin = 20.dp)
        })
    }
}
 */

/*
// 배리어
@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 350.dp, height = 220.dp)) {
        // createRefs()를 사용해 ConstraintLayout에서 각 컴포저블의 참조를 만든다.(createRefFor()도 있다)
        val (button1, button2, button3) = createRefs()

        MyButton(text = "Button1", modifier = Modifier.width(100.dp).constrainAs(button1){
            top.linkTo(parent.top, margin = 30.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        MyButton(text = "Button2", modifier = Modifier.width(100.dp).constrainAs(button2){
            top.linkTo(button1.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        val barrier = createEndBarrier(button1, button2)

        MyButton(text = "Button3", modifier = Modifier.constrainAs(button3){
            linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
            // button1과 연결되어 있어 2가 변경되어도 3은 그대로다
            linkTo(button1.end, parent.end, startMargin = 30.dp, endMargin = 8.dp)

            // 시작 위치를 배리어에 연결
            start.linkTo(barrier, margin = 30.dp)

            // 사용가능한 최대 width와 height를 지정
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })
    }
}
 */

// 제약 집합
@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 200.dp, height = 200.dp)) {
        //val button1 = createRef()

        val constraints = myConstraintSet(margin = 8.dp)

        MyButton(text = "Button1", Modifier.size(200.dp).layoutId("button1")/*{
            linkTo(parent.top, parent.bottom,
                topMargin = 8.dp, bottomMargin = 8.dp)
            linkTo(parent.start, parent.end,
                startMargin = 8.dp, endMargin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }*/)
    }
}

private fun myConstraintSet(margin: Dp): ConstraintSet {
    return ConstraintSet {
        // createRefFor메서드를 사용해 이 제약 집합을 저굥할 컴포저블의 참조를 만든다
        val button1 = createRefFor("button1")

        // constrain 함수를 호출해 제약 집합을 만든다
        constrain(button1) {
            linkTo(parent.top, parent.bottom,
                topMargin = 8.dp, bottomMargin = 8.dp)
            linkTo(parent.start, parent.end,
                startMargin = 8.dp, endMargin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
}

@Composable
fun MyButton(
    text: String,
    modifier: Modifier = Modifier){
    Button(
        onClick = {},
        modifier = modifier
    ) {
        Text(text)
    }
}

@Preview()
@Composable
fun MainPreview(){
    ConstraintLayoutTheme {
        MainScreen()
    }
}