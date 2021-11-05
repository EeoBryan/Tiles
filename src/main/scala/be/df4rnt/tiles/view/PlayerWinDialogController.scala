package be.df4rnt.tiles.view
import scalafxml.core.macros.sfxml
import scalafx.Includes._
import be.df4rnt.tiles.MainApp
import scalafx.event.ActionEvent
import scalafx.scene.control.{Label}
import be.df4rnt.tiles.model.Player
import scalafx.stage.Stage

@sfxml
class PlayerWinDialogController(
    private val playerNameWinLabel: Label,
    private var playerScoreWinLabel: Label,
    private var playerTimeWinLabel: Label,
) {
    private var _player : Player = null
    var dialogStage : Stage = null
    var homeClicked = false
    var playAgainClicked = false

    def player = _player
    def player_=(x : Player) {
        _player = x
        playerNameWinLabel.text = _player.playerName.value
        playerScoreWinLabel.text = _player.score.value.toString
        playerTimeWinLabel.text = _player.time.value.toString
    }

    def playAgain(action: ActionEvent) = {
        playAgainClicked = true;
        dialogStage.close()
    }

    def backHome(action: ActionEvent) = {
        homeClicked = true;
        dialogStage.close()
        MainApp.showMainMenuScreen()
    }
}