package be.df4rnt.tiles.view
import scalafxml.core.macros.sfxml
import scalafx.Includes._
import be.df4rnt.tiles.MainApp
import scalafx.event.{ActionEvent, EventHandler}
import be.df4rnt.tiles.model.{Player, RectangleTile, CircleTile}
import scalafx.scene.control.{Label}
import scalafx.animation.{Timeline, KeyFrame}
import scalafx.util.Duration
import java.util.Calendar
import java.text.SimpleDateFormat
import scalafx.scene.layout.TilePane
import scalafx.scene.paint.{Color, Paint}
import scala.collection.mutable.ListBuffer
import scalafx.scene.input.MouseEvent
import scalafx.collections.ObservableBuffer

// figure out how to refresh game when hit try again

@sfxml
class GameplayController(
    private val playerNameGPLabel : Label,
    private var showTimeLabel : Label,
    private var showScoreLabel : Label,
    private val gamePlayTilePane : TilePane
) {
    private var _player : Player = null 
    var timeline = new Timeline()
    var levelMaxTime = 5
    var levelScore = 100
    var playerTime = 0.0
    var playerScore = 0 // score based on time
    var startTimeInt = 0
    var endTimeInt = 0
    var startTime = ""
    var endTime = ""
    val r = scala.util.Random
    var scoreTileClicked = false

    var customColorSize = 3
    var customColor = new Array[String](customColorSize)
    customColor(0) = "a8e10c"
    customColor(1) = "8a6fdf"
    customColor(2) = "2ceef0"

    case class StdRectTile(stdRectWidth: Double, stdRectHeight: Double, stdRectFill: Color) extends RectangleTile(stdRectWidth,stdRectHeight,stdRectFill)
    case class ScoreRectTile(scrRectWidth: Double, scrRectHeight: Double, scrRectFill: Color) extends RectangleTile(scrRectWidth,scrRectHeight,scrRectFill)

    case class StdCircleTile(stdCircleX: Double, stdCircleY: Double, stdRadius: Int, stdCircleFill: Color) extends CircleTile(stdCircleX,stdCircleY,stdRadius,stdCircleFill)
    case class ScoreCircleTile(scrCircleX: Double, scrCircleY: Double, scrRadius: Int, scrCircleFill: Color) extends CircleTile(scrCircleX,scrCircleY,scrRadius,scrCircleFill)

    def playerName = _player
    def playerName_=(x : Player) = {
        _player = x
        playerNameGPLabel.text = _player.playerName.value

        _player.score.value = playerScore
        _player.time.value = playerTime
    }

    def initGame() = {
        startTimeInt = getTimeForScore()
        val colorRnum = r.nextInt(customColorSize)
        var stdColor = customColor(colorRnum) // standard tile color
        val worldRnum = r.nextInt(2)

        var rects = new ListBuffer[RectangleTile]()
        for(a <- 1 to 49) {
            rects += StdRectTile(95.0,95.0,Color.web(stdColor)) // function literal to get rect object
        }

        var circles = new ListBuffer[CircleTile]()
        for(a <- 1 to 49) {
            circles += StdCircleTile(95.0,95.0,50,Color.web(stdColor)) // function literal to get rect object
        }

        val rnum = r.nextInt(49)
        val scoreRectObj = (srect: RectangleTile) => {
            srect.onMouseClicked=(me: MouseEvent)=>{

                endTimeInt = getTimeForScore()
                playerTime = endTimeInt-startTimeInt
                _player.time.value = playerTime

                playerScore = Math.ceil(Math.max(0, levelMaxTime - playerTime) * levelScore).toInt;
                _player.score.value = playerScore
                showScoreLabel.text = playerScore.toString

                MainApp.showPlayerWinScreen(playerName)
                srect.fill_=(Color.Green)
                };
            srect}

        val scoreCircleObj = (scircle: CircleTile) => {
            scircle.onMouseClicked=(me: MouseEvent)=>{

                endTimeInt = getTimeForScore()
                playerTime = endTimeInt-startTimeInt
                _player.time.value = playerTime

                playerScore = Math.ceil(Math.max(0, levelMaxTime - playerTime) * levelScore).toInt;
                _player.score.value = playerScore
                showScoreLabel.text = playerScore.toString

                MainApp.showPlayerWinScreen(playerName)
                scircle.fill_=(Color.Green)
                };
            scircle}
        
        val upRects = rects.updated(rnum, scoreRectObj(ScoreRectTile(95.0,95.0,Color.web(stdColor).brighter)))
        val upCircles = circles.updated(rnum, scoreCircleObj(ScoreCircleTile(95.0,95.0,50,Color.web(stdColor).brighter)))

        gamePlayTilePane.hgap=3
        gamePlayTilePane.vgap=3
        gamePlayTilePane.prefColumns=8

        if (worldRnum == 1) {
            gamePlayTilePane.children_=(upRects) // Iterable   
        } else {
            gamePlayTilePane.children_=(upCircles)
        }
    }

    def getTime() : String = {
        val now = Calendar.getInstance().getTime()

        val hourF = new SimpleDateFormat("hh")
        val minuteF = new SimpleDateFormat("mm")
        val secondF = new SimpleDateFormat("ss")
        val ampmF = new SimpleDateFormat("a")

        val currentHour = hourF.format(now)
        val currentMinute = minuteF.format(now)
        val currentSecond = secondF.format(now)
        val currentAmPm = ampmF.format(now)
        val time = currentHour + ":" + currentMinute + ":" + currentSecond + currentAmPm
        startTime = time.toString
        return time.toString
    }

    def getTimeForScore() : Int = {
        val now = Calendar.getInstance().getTime()

        val hourF = new SimpleDateFormat("hh")
        val minuteF = new SimpleDateFormat("mm")
        val secondF = new SimpleDateFormat("ss")
        val ampmF = new SimpleDateFormat("a")

        val currentHour = hourF.format(now)
        val currentMinute = minuteF.format(now)
        val currentSecond = secondF.format(now)
        val currentAmPm = ampmF.format(now)
        val time = currentHour  + currentMinute + currentSecond
        return time.toInt
    }

    // get time when player wins then calculate to initial time
    def keepTime() : Unit = {
        showTimeLabel.text = getTime() // show start time
    }

    def keepScore() : Unit = {
        showScoreLabel.text = playerScore.toString
    }

    def restartGame(action : ActionEvent) = {
        keepTime()
        initGame()
    }
    // exit
    def backHome(action : ActionEvent) = {
        MainApp.showMainMenuScreen()
    }
    initGame()
    keepTime()
    keepScore()
    print(getTimeForScore())
}