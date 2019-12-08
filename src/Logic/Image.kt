package ru.vanchikov.laboratory1.logic

import ru.vanchikov.laboratory1.mathematic.vectors.Vector3

class Image(val width: Int, val height: Int) {
    private val colorBuff: Array<Array<Vector3?>> = Array<Array<Vector3?>>(width) { arrayOfNulls<Vector3>(height) }
    private val dephBuff: Array<DoubleArray> = Array(width) { DoubleArray(height) }
    fun clear() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                colorBuff[x][y]!!.setVector(black)
                dephBuff[x][y] = 0.0
            }
        }
    }

    fun setColor(x: Int, y: Int, color: Vector3) {
        colorBuff[x][y]!!.setVector(color)
    }

    fun getColor(x: Int, y: Int): Vector3 {
        return colorBuff[x][y]!!
    }

    fun setDeph(x: Int, y: Int, d: Double) {
        dephBuff[x][y] = d
    }

    fun getDeph(x: Int, y: Int): Double {
        return dephBuff[x][y]
    }

    companion object {
        private val black: Vector3 = Vector3(0.0, 0.0, 0.0)
    }

    init {
        for (x in 0 until width) {
            for (y in 0 until height) {
                colorBuff[x][y] = Vector3(0.0, 0.0, 0.0)
            }
        }
    }
}