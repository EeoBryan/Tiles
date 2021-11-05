package be.df4rnt.tiles.model

import scalafx.beans.property.{StringProperty, ObjectProperty}

class Player (_playerName : String){
  var playerName  = new StringProperty(_playerName)
  var score = ObjectProperty[Int](0)
  var time    = ObjectProperty[Double](0.0)
}
