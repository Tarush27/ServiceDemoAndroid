package ticTacToeGameViewModel

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel

class TicTacToeViewModel(application: Application) : AndroidViewModel(application) {

    private var activePlayer = 1
    private var gameIsActive = true
    private var totalCellsInGridMarked = 0
    private var cellState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    fun getGridWinningPositions(): Array<IntArray> {
        return storeGridWinningPositions()
    }

    private fun storeGridWinningPositions(): Array<IntArray> {
        val gridWinningPositions = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
        return gridWinningPositions
    }

    fun isActivePlayer(activePlayer: Int): Boolean {
        return activePlayer == 1
    }

    fun isPlayerActive(activePlayer: Int): Boolean {
        return activePlayer == 0
    }

    fun resetGridValuesForPlacingImageX(view: View?) {
        val setPieceImage = view as ImageView
        val pieceImageState = setPieceImage.tag.toString().toInt()
        activePlayer = 0
        totalCellsInGridMarked++
        cellState[pieceImageState] = 1
    }

    fun resetGridValuesForPlacingImageO(view: View?) {
        val setPieceImage = view as ImageView
        val pieceImageState = setPieceImage.tag.toString().toInt()
        activePlayer = 1
        totalCellsInGridMarked++
        cellState[pieceImageState] = 0
    }

    fun checkGridWinningPositionNonNUll(currentGridWinPositions: IntArray): Boolean {
        val myGridWinPositions = cellState[currentGridWinPositions[0]] ==
                cellState[currentGridWinPositions[1]] &&
                cellState[currentGridWinPositions[1]] ==
                cellState[currentGridWinPositions[2]] &&
                cellState[currentGridWinPositions[0]] != 2
        return myGridWinPositions
    }

    fun isPatternFormedImageX(currentGridWinPositions: IntArray): Boolean {
        return cellState[currentGridWinPositions[0]] == 1
    }


    fun isPatternFormedImageO(currentGridWinPositions: IntArray): Boolean {
        return cellState[currentGridWinPositions[0]] == 0
    }

    fun gameDraw(): Boolean {
        return gameIsActive && totalCellsInGridMarked == 9
    }

    fun setGridDefaultValue() {
        for (currentCellValueInGrid in cellState.indices) {
            cellState[currentCellValueInGrid] = 2
        }
    }

    fun resetGridToDefault() {
        gameIsActive = true
        totalCellsInGridMarked = 0
        activePlayer = 1
    }

    fun isGridInitiallyNull(view: View?): Boolean {
        val setPieceImage = view as ImageView
        val pieceImageState = setPieceImage.tag.toString().toInt()
        return cellState[pieceImageState] == 2 && gameIsActive
    }

    fun gameInactive(): Boolean {
        return !gameIsActive
    }
}