package be.df4rnt.tiles.view
import scalafxml.core.macros.sfxml
import be.df4rnt.tiles.MainApp
import scalafx.event.ActionEvent
import be.df4rnt.tiles.model.Player

@sfxml
class MainMenuController() {

    def startGame(action : ActionEvent) = {
        val player = new Player("")
        val enterClicked = MainApp.showPlayerNameDialogScreen(player);
            if(enterClicked) {
                MainApp.playerData += player
                MainApp.showGamePlayScreen(player)
            }
    }

    def showLeaderboard(action : ActionEvent) = {
        MainApp.showLeaderboardScreen()
    }

    def showHowToPlay(action: ActionEvent) = {
        MainApp.showHowToPlayScreen()
    }
}