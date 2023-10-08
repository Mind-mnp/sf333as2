package com.example.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {
    var state by mutableStateOf(GameState())
    private var nextStartingPlayer = BoardCellValue.CIRCLE

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,
    )

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.BoardTapped -> {
                addValueToBoard(action.cellNo)
            }

            UserAction.PlayAgainButtonClicked -> {
                gameReset()
            }
        }
    }

    private fun computerMove(){
        if (state.currentTurn == BoardCellValue.CROSS){
            if (canWin()) { return }
            if (canBlock()) { return }
            if (middleFree()) {
                addValueToBoard(5)
                return
            }
            val emptyCells = boardItems.filter { it.value == BoardCellValue.NONE }.keys
            if (emptyCells.isNotEmpty()) {
                addValueToBoard(emptyCells.random())
            }
        }
    }


    private fun canWin(): Boolean {
        return findVictoryMove(BoardCellValue.CROSS)
    }

    private fun canBlock(): Boolean {
        return findVictoryMove(BoardCellValue.CIRCLE)
    }

    private fun findVictoryMove(player: BoardCellValue): Boolean {
        for (i in 1..9) {
            if (boardItems[i] == BoardCellValue.NONE) {
                boardItems[i] = player
                if (checkForVictory(player)) {
                    boardItems[i] = BoardCellValue.CROSS
                    boardItems[i] = BoardCellValue.NONE
                    addValueToBoard(i)
                    return true
                } else {
                    boardItems[i] = BoardCellValue.NONE
                }
            }
        }
        return false
    }

    private fun middleFree(): Boolean {
        if (boardItems[5] == BoardCellValue.NONE){
            return true
        }
        return false
    }

    private fun gameReset() {
        boardItems.forEach { (i, _) -> boardItems[i] = BoardCellValue.NONE }
        nextStartingPlayer = if (nextStartingPlayer == BoardCellValue.CIRCLE){
            BoardCellValue.CROSS
        } else {BoardCellValue.CIRCLE}

        state = state.copy(
            hintText = "Player '${if (nextStartingPlayer == BoardCellValue.CIRCLE) "O" else "X"}' turn",
            currentTurn = nextStartingPlayer,
            victoryType = VictoryType.NONE,
            hasWon = false
        )
        if (state.currentTurn == BoardCellValue.CROSS) {
            computerMove()
        }
    }

    private fun addValueToBoard(cellNo: Int) {
        if (boardItems[cellNo] != BoardCellValue.NONE) {
            return
        }
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardItems[cellNo] = BoardCellValue.CIRCLE
            if (checkForVictory(BoardCellValue.CIRCLE)) {
                state = state.copy(
                    hintText = "Player 'O' Won",
                    playerCircleCount = state.playerCircleCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if (hasBoardFull()) {
                state = state.copy(
                    hintText = "Game Draw",
                    drawCount = state.drawCount + 1,
                    currentTurn = BoardCellValue.NONE
                )
            } else {
                state = state.copy(
                    hintText = "Player 'X' turn",
                    currentTurn = BoardCellValue.CROSS
                )
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    computerMove()
                }
            }
        } else if (state.currentTurn == BoardCellValue.CROSS) {
            boardItems[cellNo] = BoardCellValue.CROSS
            state = if (checkForVictory(BoardCellValue.CROSS)) {
                state.copy(
                    hintText = "Player 'X' Won",
                    playerCrossCount = state.playerCrossCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else if (hasBoardFull()) {
                state.copy(
                    hintText = "Game Draw",
                    drawCount = state.drawCount + 1
                )
            } else {
                state.copy(
                    hintText = "Player 'O' turn",
                    currentTurn = BoardCellValue.CIRCLE
                )
            }
        }
    }

    private fun checkForVictory(boardValue: BoardCellValue): Boolean {
        when {
            //ชนะแนวนอนแถวบน
            boardItems[1] == boardValue && boardItems[2] == boardValue && boardItems[3] == boardValue -> {
                state = state.copy(victoryType = VictoryType.HORIZONTAL1)
                return true
            }

            boardItems[4] == boardValue && boardItems[5] == boardValue && boardItems[6] == boardValue -> {
                state = state.copy(victoryType = VictoryType.HORIZONTAL2)
                return true
            }

            boardItems[7] == boardValue && boardItems[8] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.HORIZONTAL3)
                return true
            }

            boardItems[1] == boardValue && boardItems[4] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(victoryType = VictoryType.VERTICAL1)
                return true
            }

            boardItems[2] == boardValue && boardItems[5] == boardValue && boardItems[8] == boardValue -> {
                state = state.copy(victoryType = VictoryType.VERTICAL2)
                return true
            }

            boardItems[3] == boardValue && boardItems[6] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.VERTICAL3)
                return true
            }

            boardItems[1] == boardValue && boardItems[5] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.DIAGONAL1)
                return true
            }

            boardItems[3] == boardValue && boardItems[5] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(victoryType = VictoryType.DIAGONAL2)
                return true
            }

            else -> return false
        }
    }

    public fun hasBoardFull(): Boolean {
        return !boardItems.containsValue(BoardCellValue.NONE)
    }
}