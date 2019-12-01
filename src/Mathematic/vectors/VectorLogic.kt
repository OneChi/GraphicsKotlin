package ru.vanchikov.laboratory1.Mathematic.vectors

class VectorLogic {

    fun add(q1: Vector4, q2: Vector4): Vector4 {
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


}