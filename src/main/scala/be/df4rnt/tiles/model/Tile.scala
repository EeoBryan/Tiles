package be.df4rnt.tiles.model

import scalafxml.core.macros.sfxml
import scalafx.Includes._

trait Tile {

    var isClicked: Boolean = false
    
    def onClick
}