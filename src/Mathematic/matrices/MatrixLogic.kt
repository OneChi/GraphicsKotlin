package ru.vanchikov.laboratory1.Mathematic.matrices

import ru.vanchikov.laboratory1.Mathematic.vectors.Vector3
import ru.vanchikov.laboratory1.Mathematic.vectors.Vector4

class MatrixLogic {


    fun mult(data: Matrix3, m2: Matrix3): Matrix3 {
        val ret = Matrix3()
        for (i in 0..2) {
            for (j in 0..2) {
                ret[i, j] = data[i, 0] * m2[0, j] + data[i, 1] * m2[1, j] + data[i, 2] * m2[2, j]
            }
        }
        return ret
    }

    fun mult(m: Matrix3, v: Vector3): Vector3 {
        val v1 = DoubleArray(3)
        for (i in 0..2) {
            v1[i] = (m[i, 0] * v.x + m[i, 1] * v.y + m[i, 2] * v.z)
        }
        return Vector3(v1)
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

    fun mult(m1: Matrix4, m2: Matrix4): Matrix4 {
        val ret = Matrix4()
        for (i in 0..3) {
            for (j in 0..3) {
                ret[i, j] = m1[i, 0] * m2[0, j] + m1[i, 1] * m2[1, j] + m1[i, 2] * m2[2, j] + m1[i, 3] * m2[3, j]
            }
        }
        return ret
    }

    fun mult(m: Matrix4, v: Vector4): Vector4 {
        val v1 = DoubleArray(4)
        for (i in 0..3) {
            v1[i] = m[i, 0] * v.x + m[i, 1] * v.y + m[i, 2] * v.z + m[i, 3] * v.w
        }
        return Vector4(v1)
    }
}