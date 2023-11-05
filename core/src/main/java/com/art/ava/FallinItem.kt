package com.art.ava

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3


class FallinItem(x: Float, y: Float) {
    private var position: Vector3? = null
    private var velosity: Vector3? = null

    private var item: Texture? = null

    init {
        position = Vector3(x.toFloat(), y.toFloat(), 0f)
        velosity = Vector3(0f, 0f, 0f)
        item = Texture("bird.png")
    }

    public fun getPosition() {

    }
}

