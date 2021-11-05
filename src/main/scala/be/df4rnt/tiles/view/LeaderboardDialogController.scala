package be.df4rnt.tiles.view
import scalafxml.core.macros.sfxml
import scalafx.Includes._
import be.df4rnt.tiles.MainApp
import scalafx.event.ActionEvent
import be.df4rnt.tiles.model.Player
import scalafx.scene.control.{TableView, TableColumn, Label}

@sfxml
class LeaderboardDialogController(
    private val leaderboardTable : TableView[Player],
    private val playerNameColumn : TableColumn[Player, String],
    private val scoreColumn : TableColumn[Player, Int],
    private val timeColumn : TableColumn[Player, Double]
) {
    leaderboardTable.items = MainApp.playerData
    private var _player : Player = null 

    private def showLeaderboardData(player : Some[Player]) = {
        player match {
            case Some(x) =>
            playerNameColumn.cellValueFactory = (x) => {x.value.playerName}
            scoreColumn.cellValueFactory  = {_.value.score} 
            timeColumn.cellValueFactory  = {_.value.time} 
        }
    }
    
    showLeaderboardData(Some(_player))
}