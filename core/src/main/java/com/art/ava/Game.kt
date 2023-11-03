package com.art.ava

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.awt.Rectangle

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Game : ApplicationAdapter() {
    private var image: Texture? = null

    private var camera: OrthographicCamera? = null
    private var batch: SpriteBatch? = null

    private lateinit  var img_character: Texture

    private lateinit var img_food: Rectangle

    override fun create() {

        image = Texture("libgdx.png")

        batch = SpriteBatch()
        camera = OrthographicCamera()
        camera!!.setToOrtho(false, 400f, 800f)

        img_character = Texture("tako.png")

        img_food = Rectangle()
        img_food.x = 800/2 - 64/2
        img_food.y = 20
        img_food.width = 64
        img_food.height = 64
    }

    override fun render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f)

    }
    /*
    override fun dispose() {
        batch!!.dispose()
        image!!.dispose()
    }*/
}
