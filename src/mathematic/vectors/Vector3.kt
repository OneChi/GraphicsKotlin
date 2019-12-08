package ru.vanchikov.laboratory1.mathematic.vectors


import ru.vanchikov.laboratory1.mathematic.matrix.Matrix3
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class Vector3{

    var x : Double = 0.0
    var y : Double = 0.0
    var z : Double = 0.0

    constructor()

    constructor(Xx: Double,Yy: Double, Zz: Double){
        x = Xx
        y = Yy
        z = Zz
    }

    constructor(doubleArray: DoubleArray) {
        x = doubleArray[0]
        y = doubleArray[1]
        z = doubleArray[2]
    }


    fun plus(vert2 : Vector3) : Vector3 {
        return Vector3(
            this.x + vert2.x,
            this.y + vert2.y,
            this.z + vert2.z
        )
    }
    fun minus(vert2 : Vector3) : Vector3 {
        return Vector3(
            this.x - vert2.x,
            this.y - vert2.y,
            this.z - vert2.z
        )
    }
    fun div(vert2 : Vector3) : Vector3 {
        return Vector3(
            this.x / vert2.x,
            this.y / vert2.y,
            this.z / vert2.z
        )
    }
    fun mul(vert2 : Vector3) : Vector3 {
        return Vector3(
            this.x * vert2.x,
            this.y * vert2.y,
            this.z * vert2.z
        )
    }
    fun getLength(): Double {
        return sqrt(
            x.pow(2.0) + y.pow(2.0) + z.pow(2.0)
        )
    }

    fun setVector( vect: Vector3 ){
        this.x = vect.x
        this.y = vect.y
        this.z = vect.z

    }

    fun setNormalVectorRotation(
        rotationX: Double,
        rotationY: Double,
        rotationZ: Double
    ) {
        val xRotationMatrix = Matrix3()
        val yRotationMatrix = Matrix3()
        val zRotationMatrix = Matrix3()
        xRotationMatrix[1, 1] = cos(rotationX)
        xRotationMatrix[2, 2] = cos(rotationX)
        xRotationMatrix[1, 2] = (-sin(rotationX))
        xRotationMatrix[2, 1] = sin(rotationX)
        yRotationMatrix[0, 0] = cos(rotationY)
        yRotationMatrix[2, 2] = cos(rotationY)
        yRotationMatrix[2, 0] = (-sin(rotationY))
        yRotationMatrix[0, 2] = sin(rotationY)
        zRotationMatrix[0, 0] = cos(rotationZ)
        zRotationMatrix[1, 1] = cos(rotationZ)
        zRotationMatrix[0, 1] = (-sin(rotationZ))
        zRotationMatrix[1, 0] = sin(rotationZ)
        val rotationMatrix: Matrix3 = mult(mult(xRotationMatrix, yRotationMatrix), zRotationMatrix)
        var result: Vector3 = mult(rotationMatrix, Vector3())
        result = mult(result, getLength())
        setVector(result)
    }

    fun getNormalVector(): Vector3 {
        return Vector3(x / getLength(), y / getLength(), z / getLength())
    }

    fun getNormalVectorRotationX(): Double {
        val n: Vector3 = getNormalVector()
        return Math.atan2(n.y, n.z)
    }

    fun getNormalVectorRotationY(): Double {
        val n: Vector3 = getNormalVector()
        return Math.atan2(n.x, n.z)
    }

    fun getNormalVectorRotationZ(): Double {
        val n: Vector3 = getNormalVector()
        return Math.atan2(n.x, n.y)
    }

}

