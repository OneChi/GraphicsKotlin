package ru.vanchikov.laboratory1.mathematic.matrix


import ru.vanchikov.laboratory1.mathematic.vectors.Vector4
import java.io.Serializable


class Matrix4 : Serializable {
    val data = DoubleArray(16)

    constructor(
        m00: Double, m01: Double, m02: Double, m03: Double,
        m10: Double, m11: Double, m12: Double, m13: Double,
        m20: Double, m21: Double, m22: Double, m23: Double,
        m30: Double, m31: Double, m32: Double, m33: Double
    ) {
        data[0] = m00
        data[1] = m01
        data[2] = m02
        data[3] = m03
        data[4] = m10
        data[5] = m11
        data[6] = m12
        data[7] = m13
        data[8] = m20
        data[9] = m21
        data[10] = m22
        data[11] = m23
        data[12] = m30
        data[13] = m31
        data[14] = m32
        data[15] = m33
    }

    constructor() {
        data[0] = 1.0
        data[1] = 0.0
        data[2] = 0.0
        data[3] = 0.0
        data[4] = 0.0
        data[5] = 1.0
        data[6] = 0.0
        data[7] = 0.0
        data[8] = 0.0
        data[9] = 0.0
        data[10] = 1.0
        data[11] = 0.0
        data[12] = 0.0
        data[13] = 0.0
        data[14] = 0.0
        data[15] = 1.0
    }

    operator fun set(x: Int, y: Int, newData: Double) {
        data[x * 4 + y] = newData
    }

    operator fun get(x: Int, y: Int): Double {
        return data[x * 4 + y]
    }

    fun transpose(): Matrix4 {
        val m = Matrix4()
        for (i in 0..3) {
            for (j in 0..3) {
                m[i, j] = get(j, i)
            }
        }
        return m
    }

    fun mult(m2: Matrix4): Matrix4 {
        val ret = Matrix4()
        for (i in 0..3) {
            for (j in 0..3) {
                ret[i, j] = this[i, 0] * m2[0, j] + this[i, 1] * m2[1, j] + this[i, 2] * m2[2, j] + this[i, 3] * m2[3, j]
            }
        }
        return ret
    }

    fun mult(v: Vector4): Vector4 {
        val v1 = DoubleArray(4)
        for (i in 0..3) {
            v1[i] = this[i, 0] * v.x + this[i, 1] * v.y + this[i, 2] * v.z + this[i, 3] * v.w
        }
        return Vector4(v1)
    }


}