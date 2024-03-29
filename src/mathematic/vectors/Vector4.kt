package ru.vanchikov.laboratory1.mathematic.vectors

import ru.vanchikov.laboratory1.mathematic.vectors.Vector3
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

}
