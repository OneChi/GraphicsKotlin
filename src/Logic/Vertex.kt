package ru.vanchikov.laboratory1.Logic

class Vertex(var x: Double, var y: Double, var z: Double){

    fun plus(vert2 : Vertex) : Vertex{
        return Vertex(this.x+vert2.x, this.y +vert2.y, this.z + vert2.z)
    }
    fun minus(vert2 : Vertex) : Vertex{
        return Vertex(this.x-vert2.x, this.y -vert2.y, this.z - vert2.z)
    }
    fun div(vert2 : Vertex) : Vertex{
        return Vertex(this.x/vert2.x, this.y /vert2.y, this.z / vert2.z)
    }
    fun mul(vert2 : Vertex) : Vertex{
        return Vertex(this.x*vert2.x, this.y *vert2.y, this.z * vert2.z)
    }
}

