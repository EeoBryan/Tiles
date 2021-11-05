package be.df4rnt.tiles.view
import be.df4rnt.tiles.model.Player
import be.df4rnt.tiles.MainApp
import scalafx.scene.control.{TextField, Label, Alert}
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.stage.Stage
import scalafx.Includes._

@sfxml
class PlayerNameDialogController(
    private val playerNameField : TextField,
    private val errMessageLabel : Label ) {

    var dialogStage : Stage = null
    private var _player : Player = null
    var enterClicked = false
    //val playerNameField : TextField = null

    def player = _player
    def player_=(x : Player) {
        _player = x
    }

    def nullChecking (x : String) = x == null || x.length == 0

    def inputChecking() : Boolean = {
        var errMsg = ""
        if(nullChecking(playerNameField.text.value))
            errMsg += "Please enter your PlayerName\n"

        if (errMsg.length() == 0) {
            return true;
        } else {
            errMessageLabel.text = "Please enter your PlayerName"
            return false;
        }
    } 

    def handleEnter(action: ActionEvent) {
        if(inputChecking()) {
            _player.playerName.value = playerNameField.text.value
            enterClicked = true
            dialogStage.close()
        }
    }
}