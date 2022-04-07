package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.TicTacToeActivityMainBinding
import ticTacToeGameViewModel.TicTacToeViewModel

class TicTacToeGameMainActivity : AppCompatActivity() {

    private lateinit var ticTacToeActivityMainBinding: TicTacToeActivityMainBinding
    private val ticTacToeViewModel: TicTacToeViewModel by viewModels()
    private var activePlayer = 1
    private var gameIsActive = true
    private var totalCellsInGridMarked = 0
    private var cellState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ticTacToeActivityMainBinding = TicTacToeActivityMainBinding.inflate(layoutInflater)
        setContentView(ticTacToeActivityMainBinding.root)
    }


    fun insertNoughtCircleAndDisplayResult(view: View?) {
        val setPieceImage = view as ImageView
        val pieceImageState = setPieceImage.tag.toString().toInt()
        if (ticTacToeViewModel.isGridInitiallyNull(view)) {
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
            for (currentGridWinningPositions in ticTacToeViewModel.getGridWinningPositions()) {
                if (gridWinningIndices(currentGridWinningPositions)
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

    private fun gridWinningIndices(currentGridWinningPositions: IntArray) =
        checkCurrentGridWinningPositionNotNull(currentGridWinningPositions)

    private fun checkCurrentGridWinningPositionNotNull(currentGridWinningPositions: IntArray): Boolean {
        val myGridWinPositions = cellState[currentGridWinningPositions[0]] ==
                cellState[currentGridWinningPositions[1]] &&
                cellState[currentGridWinningPositions[1]] ==
                cellState[currentGridWinningPositions[2]] &&
                cellState[currentGridWinningPositions[0]] != 2
        return myGridWinPositions
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