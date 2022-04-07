package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.TicTacToeActivityMainBinding

class TicTacToeGameMainActivity : AppCompatActivity() {

    private lateinit var ticTacToeActivityMainBinding: TicTacToeActivityMainBinding
    private var activePlayer = 1
    private var gameIsActive = true
    private var totalCellsInGridMarked = 0
    private var cellState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private val gridWinningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ticTacToeActivityMainBinding = TicTacToeActivityMainBinding.inflate(layoutInflater)
        setContentView(ticTacToeActivityMainBinding.root)
    }


    fun insertNoughtCircleAndDisplayResult(view: View?) {
        val setPieceImage = view as ImageView
        val pieceImageState = setPieceImage.tag.toString().toInt()

        if (cellState[pieceImageState] == 2 && gameIsActive) {
            if (activePlayer == 1) {
                setPieceImage.setImageResource(R.drawable.player_x)
                activePlayer = 0
                totalCellsInGridMarked++
                cellState[pieceImageState] = 1
            } else {
                setPieceImage.setImageResource(R.drawable.player_o)
                activePlayer = 1
                totalCellsInGridMarked++
                cellState[pieceImageState] = 0
            }
            for (currentGridWinningPositions in gridWinningPositions) {
                if (isGridWinningIndexNotNull(currentGridWinningPositions)
                ) {
                    if (cellState[currentGridWinningPositions[0]] == 1) {
                        ticTacToeActivityMainBinding.ticTacToeGameResultTextView.text =
                            getString(R.string.player_x_won)
                    } else if (cellState[currentGridWinningPositions[0]] == 0) {
                        ticTacToeActivityMainBinding.ticTacToeGameResultTextView.text =
                            getString(R.string.player_o_won)
                    }
                    ticTacToeActivityMainBinding.ticTacToeGameResult.visibility = View.VISIBLE
                    gameIsActive = false
                }
            }

            isGameDrawn()

        }
    }

    private fun isGameDrawn() {
        if (gameIsActive && (totalCellsInGridMarked == 9)) {
            ticTacToeActivityMainBinding.ticTacToeGameResultTextView.text =
                getString(R.string.tic_tac_toe_game_drawn)
            ticTacToeActivityMainBinding.ticTacToeGameResult.visibility =
                View.VISIBLE
            gameIsActive = false
        }
    }

    private fun isGridWinningIndexNotNull(currentGridWinningPositions: IntArray) =
        checkCurrentGridWinningPosition(currentGridWinningPositions)

    private fun checkCurrentGridWinningPosition(currentGridWinningPositions: IntArray): Boolean {
        val currentGridWinningPosition = cellState[currentGridWinningPositions[0]] ==
                cellState[currentGridWinningPositions[1]] &&
                cellState[currentGridWinningPositions[1]] ==
                cellState[currentGridWinningPositions[2]] &&
                cellState[currentGridWinningPositions[0]] != 2
        return currentGridWinningPosition
    }

    fun playTicTacToeGameAgainBtn(view: View?) {
        reinitializeTicTacToeGame()
        for (currentCellValueInGrid in cellState.indices) {
            cellState[currentCellValueInGrid] = 2
        }

        resetImagesInGrid()
    }

    private fun resetImagesInGrid() {
        ticTacToeActivityMainBinding.ticTacToeGrid.also {
            for (currentGridChildCount in 0..it.childCount) {
                (it.getChildAt(currentGridChildCount) as? ImageView)?.setImageResource(0)
            }
        }
    }

    private fun reinitializeTicTacToeGame() {
        gameIsActive = true
        totalCellsInGridMarked = 0
        activePlayer = 1
        ticTacToeActivityMainBinding.ticTacToeGameResult.visibility = View.INVISIBLE
    }


}