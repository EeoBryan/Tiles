package be.df4rnt.tiles.model

import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.input.MouseEvent

class CircleTile(var cX: Double, var cY: Double, var r: Int, var f: Color) extends Circle with Tile { // with Tile

   val circThis = new Circle(this)
   circThis.centerX_=(cX)
   circThis.centerY_=(cY)
   circThis.radius_=(r)
   circThis.fill_=(f)
   def onClick() = {
      circThis.onMouseClicked=(me: MouseEvent)=>{circThis.fill_=(Color.Black)}
      isClicked = true
   }
   onClick()
}