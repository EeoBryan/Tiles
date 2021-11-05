package be.df4rnt.tiles.model

import javafx.scene.{shape => jfxss}
import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent

class RectangleTile(var w: Double, var h: Double, var f: Color) extends Rectangle with Tile { // with Tile

   val rectThis = new Rectangle(this)
   rectThis.width_=(w)
   rectThis.height_=(h)
   rectThis.fill_=(f)
   def onClick() = {
      rectThis.onMouseClicked=(me: MouseEvent)=>{rectThis.fill_=(Color.Black)}
      isClicked = true
   }
   onClick()
}