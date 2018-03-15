package dk.mzw.ragdoll

import dk.mzw.scalashading.Language.{Animation, Image, R, Vec2, Vec3, rgba}
import dk.mzw.scalashading.Math
import dk.mzw.scalashading.util.Prelude
import dk.mzw.scalashading.util.Prelude.gaussianOne
import dk.mzw.scalasprites.SpriteCanvas.{Blending, Loader}

class Sprites(loader : Loader) {

    val ball = loader.apply({t => x : R => y : R =>
        for {
            i <- 1 - Math.min(1, Math.floor(Math.distance(Vec2(0, 0), Vec2(x, y))))
        } yield rgba(1, 0.3, 0.1, i)
    } : Animation)

    val grid = loader.apply({t => x : R => y : R =>
        for {
            //b <- 1 - Math.min(1, Math.floor(Math.distance(Vec2(0, 0), Vec2(x, y))))
            d <- Math.min(Math.distance(Prelude.round(x), x), Math.distance(Prelude.round(y), y))
            i1 <- Math.smoothstep(0.02, 0.01, d)
            d5 <- Math.min(Math.distance(Prelude.round(x * 0.2), x * 0.2), Math.distance(Prelude.round(y * 0.2), y * 0.2))
            i5 <- Math.smoothstep(0.02 * 0.2, 0.01 * 0.2, d5)
            d10 <- Math.min(Math.distance(Prelude.round(x * 0.1), x * 0.1), Math.distance(Prelude.round(y * 0.1), y * 0.1))
            i10 <- Math.smoothstep(0.02 * 0.1, 0.01 * 0.1, d10)
            i <- i1 * 0.1 + i5 * 0.2 + i10 * 0.3
        } yield Prelude.hsva(
            t * 0.05 + i * 0.5,
            0.5 + i * 0.5,
            i,
            1
        )
    } : Animation)

    val wall = loader("assets/wall2.png", repeat = true)

    val line = loader.f2{l => w => x : R => y : R =>
        for {
            center <- Vec2(Math.max(0, Math.min(l, x)), 0)
            d <- Math.distance(center, Vec2(x, y))
            i <- Math.smoothstep(1, 0.95, d)
        } yield rgba(1, 1, 1, i)
    }


}