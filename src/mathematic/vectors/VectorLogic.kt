package ru.vanchikov.laboratory1.mathematic.vectors

import ru.vanchikov.laboratory1.mathematic.matrix.Matrix3
import ru.vanchikov.laboratory1.mathematic.matrix.Matrix4
import kotlin.math.acos



    fun addVect(v1: Vector3, v2: Vector3): Vector3 {
        return Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z)
    }

    fun subMath(v1: Vector3, v2: Vector3): Vector3? {
        return Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z)
    }

    fun dot(v1: Vector3, v2: Vector3): Double {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z
    }

    fun mult(v: Vector3, scalar: Double): Vector3 {
        return Vector3(v.x * scalar, v.y * scalar, v.z * scalar)
    }

    fun mult(v1: Vector3, v2: Vector3): Vector3 {
        val x: Double
        val y: Double
        val z: Double
        x = v1.y * v2.z - v1.z * v2.y
        y = v1.z * v2.x - v1.x * v2.z
        z = v1.x * v2.y - v1.y * v2.x
        return Vector3(x, y, z)
    }

    fun multSimple(v1: Vector3, v2: Vector3): Vector3 {
        return Vector3(v1.x * v2.x, v1.y * v2.y, v1.z * v2.z)
    }

    fun reflect(v: Vector3, n: Vector3): Vector3? {
        return subMath(mult(n, 2 * dot(n, v)), v)
    }

    fun  addVect(q1: Vector4, q2: Vector4): Vector4 {
        return Vector4(q1.x + q2.x, q1.y + q2.y, q1.z + q2.z, q1.w + q2.w)
    }

    fun dot(q1: Vector4, q2: Vector4): Double {
        return (q1.x * q2.x) + (q1.y * q2.y) + (q1.z * q2.z) + (q1.w * q2.w)
    }

    fun mult(q: Vector4, scalar: Double): Vector4 {
        return Vector4(q.x * scalar, q.y * scalar, q.z * scalar, q.w * scalar)
    }

    fun mult(q1: Vector4, q2: Vector4): Vector4 {
        val w: Double = (q1.w * q2.w) - (q1.x * q2.x) - (q1.y * q2.y) - (q1.z * q2.z)
        val x: Double = (q1.w * q2.x) + (q1.x * q2.w) + (q1.y * q2.z) - q1.z * q2.y
        val y: Double = (q1.w * q2.y - q1.x * q2.z) + (q1.y * q2.w) + (q1.z * q2.x)
        val z: Double = q1.w * q2.z + q1.x * q2.y - q1.y * q2.x + q1.z * q2.w
        return Vector4(x, y, z, w)
    }

    fun mult(q: Vector4, v: Vector3): Vector4 {
        val w: Double = (-q.x * v.x) - (q.y * v.y) - (q.z * v.z)
        val x: Double = q.w * v.x + q.y * v.z - q.z * v.y
        val y: Double = q.w * v.y - q.x * v.z + q.z * v.x
        val z: Double = q.w * v.z + q.x * v.y - q.y * v.x
        return Vector4(x, y, z, w)
    }

    fun  addVect(v1: Vector2, v2: Vector2): Vector2 {
        return Vector2(v1.x + v2.x, v1.y + v2.y)
    }

    fun subMath(v1: Vector2, v2: Vector2): Vector2 {
        return Vector2(v1.x - v2.x, v1.y - v2.y)
    }

    fun mult(v: Vector2, scalar: Double): Vector2 {
        return Vector2(v.x * scalar, v.y * scalar)
    }

    fun dot(v1: Vector2, v2: Vector2): Double {
        return v1.x * v2.x + v1.y * v2.y
    }

    fun angleBetweenVectors(v1: Vector2, v2: Vector2): Double {
        return acos(
            dot(
                v1.normalVector,
                v2.normalVector
            )
        )
    }

    public fun mult(data: Matrix3, m2: Matrix3): Matrix3 {
        val ret = Matrix3()
        for (i in 0..2) {
            for (j in 0..2) {
                ret[i, j] = data[i, 0] * m2[0, j] + data[i, 1] * m2[1, j] + data[i, 2] * m2[2, j]
            }
        }
        return ret
    }

    public fun mult(m: Matrix3, v: Vector3): Vector3 {
        val v1 = DoubleArray(3)
        for (i in 0..2) {
            v1[i] = (m[i, 0] * v.x + m[i, 1] * v.y + m[i, 2] * v.z)
        }
        return Vector3(v1)
    }

    public fun mult(m: Matrix3, scalar: Float): Matrix3 {
        val m1 = Matrix3()
        for (i in 0..2) {
            for (j in 0..2) {
                m1[i, j] = m[i, j] * scalar
            }
        }
        return m1
    }

    public  fun mult(m1: Matrix4, m2: Matrix4): Matrix4 {
        val ret = Matrix4()
        for (i in 0..3) {
            for (j in 0..3) {
                ret[i, j] = m1[i, 0] * m2[0, j] + m1[i, 1] * m2[1, j] + m1[i, 2] * m2[2, j] + m1[i, 3] * m2[3, j]
            }
        }
        return ret
    }

    public   fun mult(m: Matrix4, v: Vector4): Vector4 {
        val v1 = DoubleArray(4)
        for (i in 0..3) {
            v1[i] = m[i, 0] * v.x + m[i, 1] * v.y + m[i, 2] * v.z + m[i, 3] * v.w
        }
        return Vector4(v1)
    }
