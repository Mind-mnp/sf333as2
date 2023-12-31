package com.example.tictactoe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.Aqua
import com.example.tictactoe.ui.theme.GreenishYellow

@Composable
fun BoardBase(){
    Canvas(modifier = Modifier
        .size(300.dp)
        .padding(10.dp)
    ){
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(size.width * 1 / 3, 0f),
            end = Offset(size.width * 1 / 3, size.height)
        )

        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(size.width * 2 / 3, 0f),
            end = Offset(size.width * 2 / 3, size.height)
        )

        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(0f,size.width * 1 / 3 ),
            end = Offset(size.height, size.width * 1 / 3)
        )

        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(0f,size.width * 2 / 3 ),
            end = Offset(size.height, size.width * 2 / 3)
        )
    }
}

@Composable
fun Cross() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height)
        )
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height),
            end = Offset(size.width, 0f)
        )
    }
}

@Composable
fun Circle() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawCircle(
            color = Aqua,
            style = Stroke(width = 20f)

        )

    }
}

@Composable
fun HorizontalLine1() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height * 1 / 6),
            end = Offset(size.width, size.height * 1 / 6)
        )

    }
}

@Composable
fun HorizontalLine2() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height * 3 / 6),
            end = Offset(size.width, size.height * 3 / 6)
        )

    }
}

@Composable
fun HorizontalLine3() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height * 5 / 6),
            end = Offset(size.width, size.height * 5 / 6)
        )

    }
}

@Composable
fun VerticalLine3() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(size.height * 5 / 6, 0f),
            end = Offset(size.height * 5 / 6, size.width)
        )

    }
}

@Composable
fun VerticalLine2() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(size.height * 3 / 6, 0f),
            end = Offset(size.height * 3 / 6, size.width)
        )

    }
}

@Composable
fun VerticalLine1() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(size.height * 1 / 6, 0f),
            end = Offset(size.height * 1 / 6, size.width)
        )

    }
}


@Composable
fun DiagonalLine1() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, 0f),
            end = Offset(size.height , size.width)
        )

    }
}

@Composable
fun DiagonalLine2() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(size.height , 0f),
            end = Offset(0f, size.height)

        )

    }
}
@Preview(showBackground = true)
@Composable
fun BoardBasePreview(){
    BoardBase()

}

@Preview(showBackground = true)
@Composable
fun BoardBasePreview2(){

    Circle()
}

@Preview(showBackground = true)
@Composable
fun BoardBasePreview3(){
    Cross()
}