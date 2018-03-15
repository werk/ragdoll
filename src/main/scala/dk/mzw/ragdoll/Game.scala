package dk.mzw.ragdoll

import dk.mzw.ragdoll.utility.Mouse
import dk.mzw.scalasprites.SpriteCanvas.{BoundingBox, Display}
import Math.PI

class Game(display: Display, sprites : Sprites, boundingBox: BoundingBox, mouse: Mouse) {

    def update(delta: Double, t : Double) = {

    }

    def draw(t : Double) = {
        val h = 20
        val w = boundingBox.width

        display.add(sprites.grid(t), x = 0, y = 0, width = w, height = h, imageX = -0.5 * w, imageY = -0.5 * h, imageWidth = w, imageHeight = h)

        // Body
        line(0, 0, 5, PI/2, 1)

        // Right leg
        line(0, 0, 5, PI*3/2 + PI/6 , 1)

        // Left leg
        line(0, 0, 5, PI*3/2 - PI/6, 1)

        // Right arm
        line(0, 5, 4, PI*3/2 + PI/4 , 1)

        // Left arm
        line(0, 5, 4, PI*3/2 - PI/4, 1)

        // Head
        line(0, 6, 0, 0, 1)

        display.draw((0, 0, 0, 1), h)
    }

    def line(x : Double, y : Double, length : Double, angle : Double, width : Double) : Unit = {
        val x1 = x + Math.cos(angle) * length * 0.5
        val y1 = y + Math.sin(angle) * length * 0.5
        display.add(sprites.line(length * 2)(0), x = x1, y = y1, angle = angle, width = length + 1, height = 1, imageX = -1, imageY = -1, imageWidth = length * 2 + 2, imageHeight = 2)
        display.add(sprites.ball(0), x = 0, y = -5) // TODO
    }

}
