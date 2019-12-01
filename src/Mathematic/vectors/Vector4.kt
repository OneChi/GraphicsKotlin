package ru.vanchikov.laboratory1.Mathematic.vectors

import java.io.Serializable
import kotlin.math.pow
import kotlin.math.sqrt

class Vector4 : Serializable {
    var x: Double = 0.0
        private set
    var y: Double = 0.0
        private set
    var z: Double = 0.0
        private set
    var w: Double = 0.0
        private set

    constructor(x: Double, y: Double, z: Double, w: Double) {
        setVector4f(x, y, z, w)
    }

    constructor(quaternion: DoubleArray) {
        setVector4f(quaternion[0], quaternion[1], quaternion[2], quaternion[3])
    }

    constructor(v: Vector4) {
        setVector4f(v)
    }

    constructor() {
        x = 1.0
        y = 0.0
        z = 0.0
        w = 0.0
    }

    val invertVector4: Vector4
        get() = Vector4(-x, -y, -z, w)

    val copy: Vector4
        get() = Vector4(x, y, z, w)

    val length: Float
        get() = sqrt(
            x.pow(2.0) + y.pow(2.0) + z.pow(2.0) + w.toDouble().pow(2.0)
        ).toFloat()

    val normalVector: Vector4
        get() {
            return Vector4(x / length, y / length, z / length, w / length)
        }

    fun setVector4f(q: Vector4) {
        x = q.x
        y = q.y
        z = q.z
        w = q.w
    }

    fun setVector4f(x: Double, y: Double, z: Double, w: Double) {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }

    val xYZ: Vector3
        get() {
            return Vector3(x, y, z)
        }


        fun add(q2: Vector4): Vector4 {
            return Vector4(this.x + q2.x, this.y + q2.y, this.z + q2.z, this.w + q2.w)
        }

        fun dot(q2: Vector4): Double {
            return (this.x * q2.x) + (this.y * q2.y) + (this.z * q2.z) + (this.w * q2.w)
        }

        fun mult(scalar: Double): Vector4 {
            return Vector4(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar)
        }

        fun mult(q2: Vector4): Vector4 {
            val w: Double = (this.w * q2.w) - (this.x * q2.x) - (this.y * q2.y) - (this.z * q2.z)
            val x: Double = (this.w * q2.x) + (this.x * q2.w) + (this.y * q2.z) - this.z * q2.y
            val y: Double = (this.w * q2.y - this.x * q2.z) + (this.y * q2.w) + (this.z * q2.x)
            val z: Double = this.w * q2.z + this.x * q2.y - this.y * q2.x + this.z * q2.w
            return Vector4(x, y, z, w)
        }

        fun mult(v: Vector3): Vector4 {
            val w: Double = (-this.x * v.x) - (this.y * v.y) - (this.z * v.z)
            val x: Double = this.w * v.x + this.y * v.z - this.z * v.y
            val y: Double = this.w * v.y - this.x * v.z + this.z * v.x
            val z: Double = this.w * v.z + this.x * v.y - this.y * v.x
            return Vector4(x, y, z, w)
        }

}
