package dk.mzw.ragdoll

import dk.mzw.ragdoll.utility.Mouse
import dk.mzw.scalasprites.SpriteCanvas.Loader
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.JSApp

object Main extends JSApp {

    def main(): Unit = {
        val canvas = dom.document.getElementById("spriteCanvas").asInstanceOf[HTMLCanvasElement]
        val loader = new Loader(canvas)

        val sprites = new Sprites(loader)

        loader.complete.foreach { display =>
            val mouse = new Mouse(canvas, display.gameCoordinatesX, display.gameCoordinatesY)
            val game = new Game(display, sprites, display.boundingBox, mouse)

            // This crazy stuff is done to avoid creating and allocating a new anonymous function for each call to requestAnimationFrame
            var loopF : Double => Unit = null

            var last : Double = secondsElapsed() - 0.01
            def loop(_t : Double) : Unit = {
                val now = secondsElapsed()
                val delta = now - last
                if (delta < 1) {
                    game.update(delta, now)
                    game.draw(now)
                }
                last = now
                dom.window.requestAnimationFrame(loopF)
            }
            loopF = loop
            loop(0)
        }
    }

    def secondsElapsed() : Double = {
        scalajs.js.Dynamic.global.performance.now().asInstanceOf[Double] * 0.001
    }

}
