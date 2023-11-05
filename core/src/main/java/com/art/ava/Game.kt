package com.art.ava

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.TimeUtils
import kotlin.properties.Delegates


/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Game : ApplicationAdapter() {
    private var image: Texture? = null
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch

    private lateinit var img_main: Texture
    private lateinit var img_fallin: Texture

    private lateinit var rect_main: Rectangle
    private var itemdrop: ArrayList<Rectangle>? = null

    private var lastDropTime: Long? = null

    private var topTodown by Delegates.notNull<Boolean>()

    override fun create() {

        image = Texture("libgdx.png")

        batch = SpriteBatch()
        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 400f)

        img_main = Texture("egor.png")
        img_fallin = Texture("weed.png")

        rect_main = Rectangle()
        rect_main.x = (800 / 2 - 64 / 2).toFloat()
        rect_main.y = 20f
        rect_main.width = 64f
        rect_main.height = 64f

        itemdrop = ArrayList<Rectangle>()
        topTodown = true
        spawnItem()

    }

    override fun render() {
        camera.update()

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f)

        batch.projectionMatrix = camera.combined
        batch.begin()
        batch.draw(img_main, rect_main.x, rect_main.y)
        for (raindrop in itemdrop!!) {
            batch.draw(img_fallin, raindrop.x, raindrop.y)
        }
        batch.end()

        if (Gdx.input.isTouched) {
            val tuchPos = Vector3()
            tuchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(tuchPos)
            rect_main.x = tuchPos.x - 64 / 2

        }
        val iter: MutableIterator<Rectangle> = itemdrop!!.iterator()
        while (iter.hasNext()) {
            val raindrop = iter.next()

            /* Вибрируют
            val randX = MathUtils.random(-1,1).toFloat()
            val randY = MathUtils.random(-1,1).toFloat()
            raindrop.x -= 100 * Gdx.graphics.deltaTime * randX
            raindrop.y -= 100 * Gdx.graphics.deltaTime * randY*/

            raindrop.y -= 100 * Gdx.graphics.deltaTime

            if (raindrop.y + 64 < 0) iter.remove()
        }

        if (TimeUtils.nanoTime() - lastDropTime!! > 1000000000) spawnItem();
    }

    private fun startAnim(){
        val target: Vector2

        target = Vector2((800/2 - 64/2).toFloat(), 20F)

        target.x = MathUtils.random(0, 800).toFloat()
        target.y = MathUtils.random(0, 400).toFloat()
    }
    private fun spawnItem() {
        val raindrop = Rectangle()
        raindrop.x = MathUtils.random(0, 800 - 64).toFloat()
        raindrop.y = MathUtils.random(0, 400 - 64).toFloat()

        raindrop.width = 64f
        raindrop.height = 64f
        itemdrop?.add(raindrop)
        lastDropTime = TimeUtils.nanoTime()

    }

    override fun dispose() {
        batch.dispose()
        img_fallin.dispose()
        img_main.dispose()

    }
}
