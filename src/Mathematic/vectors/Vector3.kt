package ru.vanchikov.laboratory1.Mathematic.vectors

class Vector3{

    var x : Double = 0.0
    var y : Double = 0.0
    var z : Double = 0.0

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
}

