package ru.vanchikov.laboratory1.Mathematic.matrices

import ru.vanchikov.laboratory1.Mathematic.vectors.Vector3


class Matrix3 {
    var data = DoubleArray(9)

    constructor(
        m00: Double, m01: Double, m02: Double,
        m10: Double, m11: Double, m12: Double,
        m20: Double, m21: Double, m22: Double
    ) {
        data[0] = m00
        data[1] = m01
        data[2] = m02
        data[3] = m10
        data[4] = m11
        data[5] = m12
        data[6] = m20
        data[7] = m21
        data[8] = m22

    }

    constructor() {
        data[0] = 1.0
        data[1] = 0.0
        data[2] = 0.0
        data[3] = 0.0
        data[4] = 1.0
        data[5] = 0.0
        data[6] = 0.0
        data[7] = 0.0
        data[8] = 1.0
    }

    operator fun set(x: Int, y: Int, newData: Double) {
        data[x * 3 + y] = newData
    }

    operator fun get(x: Int, y: Int): Double {
        return data[x * 3 + y]
    }


    fun mult(m2: Matrix3): Matrix3 {
        val ret = Matrix3()
        for (i in 0..2) {
            for (j in 0..2) {
                ret[i, j] = this[i, 0] * m2[0, j] + this[i, 1] * m2[1, j] + this[i, 2] * m2[2, j]
            }
        }
        return ret
    }

    fun mult(v: Vector3): Vector3 {
        val v1 = DoubleArray(3)
        for (i in 0..2) {
            v1[i] = (this[i, 0] * v.x + this[i, 1] * v.y + this[i, 2] * v.z)
        }
        return Vector3(v1)
    }

    fun mult(scalar: Double): Matrix3 {
        val m1 = Matrix3()
        for (i in 0..2) {
            for (j in 0..2) {
                m1[i, j] = this[i, j] * scalar
            }
        }
        return m1
    }
    /*
    companion object {
        fun mult(m1: Matrix3, m2: Matrix3): Matrix3 {
            val ret = Matrix3()
            for (i in 0..2) {
                for (j in 0..2) {
                    ret[i, j] = m1[i, 0] * m2[0, j] + m1[i, 1] * m2[1, j] + m1[i, 2] * m2[2, j]
                }
            }
            return ret
        }

        fun mult(m: Matrix3, v: Vertex): Vertex {
            val v1 = DoubleArray(3)
            for (i in 0..2) {
                v1[i] = (m[i, 0] * v.X + m[i, 1] * v.Y + m[i, 2] * v.Z)
            }
            return Vertex(v1)
        }

        fun mult(m: Matrix3, scalar: Float): Matrix3 {
            val m1 = Matrix3()
            for (i in 0..2) {
                for (j in 0..2) {
                    m1[i, j] = m[i, j] * scalar
                }
            }
            return m1
        }
    }

     */
}
