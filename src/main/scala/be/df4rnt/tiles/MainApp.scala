package be.df4rnt.tiles
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import javafx.{scene => jfxs} // alias
import scalafx.collections.ObservableBuffer
import be.df4rnt.tiles.view.{
    MainMenuController, 
    PlayerNameDialogController, 
    HowToPlayDialogController,
    GameplayController,
    LeaderboardDialogController,
    PlayerWinDialogController
    }
import scalafx.stage.{Stage, Modality}
import scalafx.scene.layout.{StackPane, VBox, TilePane}
import be.df4rnt.tiles.model.{Player, Tile, RectangleTile}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scala.util.Random

object MainApp extends JFXApp {

    val playerData = new ObservableBuffer[Player]()
    playerData += new Player("Bot1")

    // transform path of RootLayout.fxml to URI for resource location.
    val rootResource = getClass.getResourceAsStream("view/RootLayout.fxml")
    // initialize the loader object.
    val loader = new FXMLLoader(null, NoDependencyResolver)
    // Load root layout from fxml file.
    loader.load(rootResource);
    // retrieve the root component BorderPane from the FXML 
    val roots = loader.getRoot[jfxs.layout.BorderPane]
    // initialize stage
    stage = new PrimaryStage {
        title = "TILES"
        scene = new Scene {
        root = roots
        }
    }

    def showPlayerWinScreen(player: Player): Boolean = {
        val resource = getClass.getResourceAsStream("view/PlayerWinDialog.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots6  = loader.getRoot[jfxs.Parent] // get container
        val control = loader.getController[PlayerWinDialogController#Controller] 

        val dialog = new Stage() {
            initModality(Modality.APPLICATION_MODAL) // window will stay on top, user must respond to it
            initOwner(stage)
            scene = new Scene {
                root = roots6
            } 
        }
        control.dialogStage = dialog
        control.player = player
        dialog.showAndWait()
        control.playAgainClicked
    }

    def showGamePlayScreen(player : Player) = {
        val resource = getClass.getResourceAsStream("view/GamePlay.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots5 = loader.getRoot[jfxs.layout.BorderPane]
        val control = loader.getController[GameplayController#Controller]
        this.roots.setCenter(roots5) 
        control.playerName = player
    }

    def showLeaderboardScreen() = {
        val resource = getClass.getResourceAsStream("view/LeaderBoardDialog.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots4  = loader.getRoot[jfxs.Parent] // get container
        val control = loader.getController[LeaderboardDialogController#Controller] 

        val dialog = new Stage() {
            initModality(Modality.APPLICATION_MODAL) // window will stay on top, user must respond to it
            initOwner(stage)
            scene = new Scene {
                root = roots4
            } 
        }
        dialog.showAndWait()
    }

    def showHowToPlayScreen() = {
        val resource = getClass.getResourceAsStream("view/HowToPlayDialog.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots3  = loader.getRoot[jfxs.Parent] // get container
        val control = loader.getController[HowToPlayDialogController#Controller] 

        val dialog = new Stage() {
            initModality(Modality.APPLICATION_MODAL) // window will stay on top, user must respond to it
            initOwner(stage)
            scene = new Scene {
                root = roots3
            } 
        }
        dialog.showAndWait()
    }

    def showPlayerNameDialogScreen(player: Player): Boolean = {
        val resource = getClass.getResourceAsStream("view/PlayerNameDialog.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots3  = loader.getRoot[jfxs.Parent] // get container
        val control = loader.getController[PlayerNameDialogController#Controller] 

        // create own stage
        val dialog = new Stage() {
            initModality(Modality.APPLICATION_MODAL) // window will stay on top, user must respond to it
            initOwner(stage)
            scene = new Scene {
                root = roots3
            } 
        }
        control.dialogStage = dialog
        control.player = player
        dialog.showAndWait()
        control.enterClicked // return statement, this is a boolean
    }

    def showMainMenuScreen() = {
        val resource = getClass.getResourceAsStream("view/MainMenu.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
        val control = loader.getController[MainMenuController#Controller]
        this.roots.setCenter(roots2) 
    }
    showMainMenuScreen()
}