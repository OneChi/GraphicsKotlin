package ru.vanchikov.laboratory1.mathematic.vectors

import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

class Vector2 {
    var x: Double = 0.0
        private set
    var y: Double = 0.0
        private set

    constructor(x: Double, y: Double) {
        setVector(x, y)
    }

    constructor(Vector2: DoubleArray) {
        setVector(Vector2[0], Vector2[1])
    }

    constructor(v: Vector2) {
        setVector(v)
    }

    constructor() {
        x = 1.0
        y = 0.0
    }

    val copy: Vector2
        get() = Vector2(x, y)

    val length: Double
        get() = sqrt(
            x.pow(2.0) + y.pow(2.0)
        )

    val normalVector: Vector2
        get() {
            return Vector2(x / length, y / length)
        }

    fun setVector(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    fun setVector(v: Vector2) {
        x = v.x
        y = v.y
    }



}
