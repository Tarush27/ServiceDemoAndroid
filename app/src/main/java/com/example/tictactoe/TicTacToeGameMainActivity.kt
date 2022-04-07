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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ticTacToeActivityMainBinding = TicTacToeActivityMainBinding.inflate(layoutInflater)
        setContentView(ticTacToeActivityMainBinding.root)
    }


    fun insertNoughtCircleAndDisplayResult(view: View?) {
        val setPieceImage = view as ImageView

        if (ticTacToeViewModel.isGridInitiallyNull(view)) {
            if (ticTacToeViewModel.isActivePlayer(1)) {
                setPieceImage.setImageResource(R.drawable.player_x)
                ticTacToeViewModel.resetGridValuesForPlacingImageX(view)
            } else if (ticTacToeViewModel.isPlayerActive(0)) {
                setPieceImage.setImageResource(R.drawable.player_o)
                ticTacToeViewModel.resetGridValuesForPlacingImageO(view)
            }
            for (currentGridWinningPositions in ticTacToeViewModel.getGridWinningPositions()) {
                if (gridWinningIndices(currentGridWinningPositions)
                ) {
                    if (ticTacToeViewModel.isPatternFormedImageX(currentGridWinningPositions)) {
                        ticTacToeActivityMainBinding.ticTacToeGameResultTextView.text =
                            getString(R.string.player_x_won)
                    } else if (ticTacToeViewModel.isPatternFormedImageO(currentGridWinningPositions)) {
                        ticTacToeActivityMainBinding.ticTacToeGameResultTextView.text =
                            getString(R.string.player_o_won)
                    }
                    ticTacToeActivityMainBinding.ticTacToeGameResult.visibility = View.VISIBLE
                    ticTacToeViewModel.gameInactive()
                }
            }

            isGameDrawn()

        }
    }

    private fun isGameDrawn() {
        if (ticTacToeViewModel.gameDraw()) {
            ticTacToeActivityMainBinding.ticTacToeGameResultTextView.text =
                getString(R.string.tic_tac_toe_game_drawn)
            ticTacToeActivityMainBinding.ticTacToeGameResult.visibility =
                View.VISIBLE
            ticTacToeViewModel.gameInactive()
        }
    }

    private fun gridWinningIndices(currentGridWinningPositions: IntArray) =
        checkCurrentGridWinningPositionNotNull(currentGridWinningPositions)

    private fun checkCurrentGridWinningPositionNotNull(currentGridWinningPositions: IntArray): Boolean {
        return ticTacToeViewModel.checkGridWinningPositionNonNull(currentGridWinningPositions)
    }

    fun playTicTacToeGameAgainBtn(view: View?) {
        reinitializeTicTacToeGame()
        ticTacToeViewModel.setGridDefaultValue()
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
        ticTacToeViewModel.resetGridToDefault()
        ticTacToeActivityMainBinding.ticTacToeGameResult.visibility = View.INVISIBLE
    }


}