package ru.vanchikov.laboratory1.logic

import ru.vanchikov.laboratory1.mathematic.matrix.Matrix4
import ru.vanchikov.laboratory1.mathematic.vectors.Vector3
import ru.vanchikov.laboratory1.mathematic.vectors.mult

class CameraClass {
    private var xScale = 0.0
    private  var yScale: Double = 0.0
    private  var zScale: Double = 0.0
    private  var xRotation: Double = 0.0
    private  var yRotation:Double = 0.0
    private  var zRotation:Double = 0.0
    private  var xShift:Double = 0.0
    private  var yShift:Double = 0.0
    private  var zShift:Double = 0.0
    private var scaleMatrix: Matrix4 = Matrix4()
    private var xRotationMatrix: Matrix4 = Matrix4()
    private var yRotationMatrix: Matrix4 = Matrix4()
    private var zRotationMatrix: Matrix4 = Matrix4()
    private var shiftMatrix: Matrix4 = Matrix4()
    private var perspectiveMatrix: Matrix4 = Matrix4()
    private var cameraShiftMatrix: Matrix4 = Matrix4()
    private var cameraRotaionMatrix: Matrix4 = Matrix4()
    private var windowWidth = 0.0
    private  var windowHeight:Double = 0.0
    private  var zNear:Double = 0.0
    private  var zFar:Double = 0.0
    private  var FOV:Double = 0.0

    fun Pipeline(
        xScale: Double,
        yScale: Double,
        zScale: Double,
        xRotation: Double,
        yRotation: Double,
        zRotation: Double,
        xShift: Double,
        yShift: Double,
        zShift: Double
    ) {
        setxScale(xScale)
        setyScale(yScale)
        setzScale(zScale)
        setxRotation(xRotation)
        setyRotation(yRotation)
        setzRotation(zRotation)
        setxShift(xShift)
        setyShift(yShift)
        setzShift(zShift)
    }

    fun Pipeline() {
        Pipeline(1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }

    fun initPerspectiveMatrix(
        windowWidth: Double,
        windowHeight: Double,
        zNear: Double,
        zFar: Double,
        FOV: Double
    ) {
        this.windowWidth = windowWidth
        this.windowHeight = windowHeight
        this.zNear = zNear
        this.zFar = zFar
        this.FOV = FOV
        val ar = windowWidth / windowHeight
        val yScale = (1.0 / Math.tan(FOV / 2.0.toDouble())).toDouble()
        val xScale = yScale / ar
        val zRange = zNear - zFar
        perspectiveMatrix.set(0, 0, xScale)
        perspectiveMatrix.set(1, 1, yScale)
        perspectiveMatrix.set(2, 2, (-zNear - zFar) / zRange)
        perspectiveMatrix.set(2, 3, 2.0 * zFar * zNear / zRange)
        perspectiveMatrix.set(3, 2, 1.0)
        perspectiveMatrix.set(3, 3, 0.0)
    }

    fun setCamera(pos: Vector3, target: Vector3, up: Vector3) {
        setCameraRotation(target, up)
        setCameraShift(pos)
    }

    fun setCameraRotation(target: Vector3, up: Vector3) {
        val N: Vector3 = target.getNormalVector()
        var U: Vector3 = up.getNormalVector()
        U =  mult(U, target)
        var V: Vector3 =  mult(N, U)
        U = U.getNormalVector()
        V = V.getNormalVector()
        cameraRotaionMatrix.set(0, 0, U.x)
        cameraRotaionMatrix.set(0, 1, U.y)
        cameraRotaionMatrix.set(0, 2, U.z)
        cameraRotaionMatrix.set(1, 0, V.x)
        cameraRotaionMatrix.set(1, 1, V.y)
        cameraRotaionMatrix.set(1, 2, V.z)
        cameraRotaionMatrix.set(2, 0, N.x)
        cameraRotaionMatrix.set(2, 1, N.y)
        cameraRotaionMatrix.set(2, 2, N.z)
    }

    fun invertCameraV() {
        cameraRotaionMatrix.set(1, 0, -cameraRotaionMatrix.get(1, 0))
        cameraRotaionMatrix.set(1, 1, -cameraRotaionMatrix.get(1, 1))
        cameraRotaionMatrix.set(1, 2, -cameraRotaionMatrix.get(1, 2))
    }

    fun setCameraShift(pos: Vector3) {
        setCameraShift(pos.x, pos.y, pos.z)
    }

    fun setCameraShift(x: Double, y: Double, z: Double) {
        setCameraXShift(x)
        setCameraYShift(y)
        setCameraZShift(z)
    }

    fun setCameraXShift(x: Double) {
        cameraShiftMatrix.set(0, 3, -x)
    }

    fun setCameraYShift(y: Double) {
        cameraShiftMatrix.set(1, 3, -y)
    }

    fun setCameraZShift(z: Double) {
        cameraShiftMatrix.set(2, 3, -z)
    }

    fun toRadian(degree: Double): Double {
        return (degree / 180.0 * Math.PI)
    }

    fun toDegree(radian: Double): Double {
        return (radian / Math.PI * 180.0)
    }

    private fun getVPMatrix(): Matrix4 {
        var VPMatrix = Matrix4()
        VPMatrix =  mult(VPMatrix, perspectiveMatrix)
        VPMatrix =  mult(VPMatrix, cameraRotaionMatrix)
        VPMatrix =  mult(VPMatrix, cameraShiftMatrix)
        return VPMatrix
    }





    fun getWorldMatrix(): Matrix4 {
        var worldMatrix = Matrix4()
        worldMatrix =  mult(worldMatrix, shiftMatrix)
        worldMatrix =  mult(worldMatrix, zRotationMatrix)
        worldMatrix =  mult(worldMatrix, yRotationMatrix)
        worldMatrix =  mult(worldMatrix, xRotationMatrix)
        worldMatrix =  mult(worldMatrix, scaleMatrix)
        return worldMatrix
    }

    fun getMoveMatrix(): Matrix4? {
        var moveMatrix = Matrix4()
        moveMatrix =  mult(moveMatrix, getVPMatrix())
        moveMatrix =  mult(moveMatrix, getWorldMatrix())
        return moveMatrix
    }

    fun setScale(xScale: Double, yScale: Double, zScale: Double) {
        setxScale(xScale)
        setyScale(yScale)
        setzScale(zScale)
    }

    fun setScale(scale: Double) {
        setScale(scale, scale, scale)
    }

    fun getxScale(): Double {
        return xScale
    }

    fun setxScale(xScale: Double) {
        this.xScale = xScale
        scaleMatrix.set(0, 0, xScale)
    }

    fun getyScale(): Double {
        return yScale
    }

    fun setyScale(yScale: Double) {
        this.yScale = yScale
        scaleMatrix.set(1, 1, yScale)
    }

    fun getzScale(): Double {
        return zScale
    }

    fun setzScale(zScale: Double) {
        this.zScale = zScale
        scaleMatrix.set(2, 2, zScale)
    }

    fun setRotation(xRotation: Double, yRotation: Double, zRotation: Double) {
        setxRotation(xRotation)
        setyRotation(yRotation)
        setzRotation(zRotation)
    }

    fun setRotation(rotation: Vector3) {
        setxRotation(rotation.getNormalVectorRotationX())
        setyRotation(rotation.getNormalVectorRotationY())
        setzRotation(rotation.getNormalVectorRotationZ())
    }

    fun getRotation(): Vector3? {
        val v = Vector3()
        v.setNormalVectorRotation(getxRotation(), getyRotation(), getzRotation())
        return v
    }

    fun getxRotation(): Double {
        return xRotation
    }

    fun setxRotation(xRotation: Double) {
        this.xRotation = xRotation
        xRotationMatrix.set(1, 1, Math.cos(xRotation.toDouble()).toDouble())
        xRotationMatrix.set(2, 2, Math.cos(xRotation.toDouble()).toDouble())
        xRotationMatrix.set(1, 2, (-Math.sin(xRotation.toDouble())).toDouble())
        xRotationMatrix.set(2, 1, Math.sin(xRotation.toDouble()).toDouble())
    }

    fun getyRotation(): Double {
        return yRotation
    }

    fun setyRotation(yRotation: Double) {
        this.yRotation = yRotation
        yRotationMatrix.set(0, 0, Math.cos(yRotation.toDouble()).toDouble())
        yRotationMatrix.set(2, 2, Math.cos(yRotation.toDouble()).toDouble())
        yRotationMatrix.set(0, 2, (-Math.sin(yRotation.toDouble())).toDouble())
        yRotationMatrix.set(2, 0, Math.sin(yRotation.toDouble()).toDouble())
    }

    fun getzRotation(): Double {
        return zRotation
    }

    fun setzRotation(zRotation: Double) {
        this.zRotation = zRotation
        zRotationMatrix.set(1, 1, Math.cos(zRotation.toDouble()).toDouble())
        zRotationMatrix.set(0, 0, Math.cos(zRotation.toDouble()).toDouble())
        zRotationMatrix.set(0, 1, (-Math.sin(zRotation.toDouble())).toDouble())
        zRotationMatrix.set(1, 0, Math.sin(zRotation.toDouble()).toDouble())
    }

    fun setShift(xShift: Double, yShift: Double, zShift: Double) {
        setxShift(xShift)
        setyShift(yShift)
        setzShift(zShift)
    }

    fun setShift(shift: Vector3) {
        setxShift(shift.x)
        setyShift(shift.y)
        setzShift(shift.z)
    }

    fun getShift(): Vector3? {
        return Vector3(getxShift(), getyShift(), getzShift())
    }

    fun getxShift(): Double {
        return xShift
    }

    fun setxShift(xShift: Double) {
        this.xShift = xShift
        shiftMatrix.set(0, 3, xShift)
    }

    fun getyShift(): Double {
        return yShift
    }

    fun setyShift(yShift: Double) {
        this.yShift = yShift
        shiftMatrix.set(1, 3, yShift)
    }

    fun getzShift(): Double {
        return zShift
    }

    fun setzShift(zShift: Double) {
        this.zShift = zShift
        shiftMatrix.set(2, 3, zShift)
    }

    fun getPerspectiveMatrix(): Matrix4? {
        return perspectiveMatrix
    }

    fun setPerspectiveMatrix(perspectiveMatrix: Matrix4) {
        this.perspectiveMatrix = perspectiveMatrix
    }

    fun getCameraShiftMatrix(): Matrix4? {
        return cameraShiftMatrix
    }

    fun setCameraShiftMatrix(cameraShiftMatrix: Matrix4) {
        this.cameraShiftMatrix = cameraShiftMatrix
    }

    fun getCameraRotaionMatrix(): Matrix4? {
        return cameraRotaionMatrix
    }

    fun setCameraRotaionMatrix(cameraRotaionMatrix: Matrix4) {
        this.cameraRotaionMatrix = cameraRotaionMatrix
    }

    fun getWindowWidth(): Double {
        return windowWidth
    }

    fun getWindowHeight(): Double {
        return windowHeight
    }

    fun getzNear(): Double {
        return zNear
    }

    fun getzFar(): Double {
        return zFar
    }

    fun getFOV(): Double {
        return FOV
    }

}