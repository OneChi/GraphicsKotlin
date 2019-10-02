package Mathematic

import ru.vanchikov.laboratory1.Logic.Vertex


class Matrix3(var values: DoubleArray) {

    fun multiply(other: Matrix3): Matrix3 {
        val result = DoubleArray(9)
        for (row in 0..2) {
            for (col in 0..2) {
                for (i in 0..2) {
                    result[row * 3 + col] += this.values[row * 3 + i] * other.values[i * 3 + col]
                }
            }
        }
        return Matrix3(result)
    }

    fun transform(vert: Vertex): Vertex {
        return Vertex(
            vert.x * values[0] + vert.y * values[3] + vert.z * values[6],
            vert.x * values[1] + vert.y * values[4] + vert.z * values[7],
            vert.x * values[2] + vert.y * values[5] + vert.z * values[8]
        )
    }
}