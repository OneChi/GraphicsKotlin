package ru.vanchikov.laboratory1.logic


import ru.vanchikov.laboratory1.mathematic.matrix.Matrix3
import ru.vanchikov.laboratory1.mathematic.vectors.*
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JSlider
import javax.swing.SwingConstants
import kotlin.math.*


// panel to display render results
class Engine : JPanel() , Runnable{
    private var cameraClass = CameraClass()
    val frame = JFrame()
    val pane = frame.contentPane
    val piramide = ArrayList<Triangle>()
    // slider to control horizontal rotation
    val headingSlider = JSlider(0, 360, 180)
    // slider to control vertical rotation
    val pitchSlider = JSlider(SwingConstants.VERTICAL, -90, 90, 0)
    var rotation = 0.0


    init {


        pane.layout = BorderLayout()
        pane.add(headingSlider, BorderLayout.SOUTH)
        pane.add(pitchSlider, BorderLayout.EAST)
        pane.add(this, BorderLayout.CENTER)
        piramideInit()
        frame.setSize(400, 400)
        frame.isVisible = true


        Thread(this).start()
    }




    //инициализация квадрата
    fun piramideInit(){
/*
        piramide.add(
            Triangle(
                Vertex(100.0, 100.0, 100.0),
                Vertex(-100.0, -100.0, 100.0),
                Vertex(-100.0, 100.0, -100.0),
                Color.WHITE
            )
        )

        piramide.add(
            Triangle(
                Vertex(100.0, 100.0, 100.0),
                Vertex(-100.0, -100.0, 100.0),
                Vertex(100.0, -100.0, -100.0),
                Color.RED
            )
        )

        piramide.add(
            Triangle(
                Vertex(-100.0, 100.0, -100.0),
                Vertex(100.0, -100.0, -100.0),
                Vertex(100.0, 100.0, 100.0),
                Color.GREEN
            ))


        piramide.add(
            Triangle(
                Vertex(-100.0, 100.0, -100.0),
                Vertex(100.0, -100.0, -100.0),
                Vertex(-100.0, -100.0, 100.0),
                Color.BLUE
            )
        )
*/

        //A
        piramide.add(
            Triangle(
                Vector3(-100.0, 100.0, 100.0),
                Vector3(100.0, 100.0, 100.0),
                Vector3(-100.0, 100.0, -100.0),
                Color.RED
            )
        )
        //B
        piramide.add(
            Triangle(
                Vector3(100.0, 100.0, 100.0),
                Vector3(100.0, 100.0, -100.0),
                Vector3(-100.0, 100.0, -100.0),
                Color.GREEN
            )
        )
        //C
        piramide.add(
            Triangle(
                Vector3(100.0, -100.0, 100.0),
                Vector3(100.0, 100.0, -100.0),
                Vector3(100.0, 100.0, 100.0),
                Color.BLUE
            )
        )
        //D
        piramide.add(
            Triangle(
                Vector3(100.0, -100.0, 100.0),
                Vector3(100.0, -100.0, -100.0),
                Vector3(100.0, 100.0, -100.0),
                Color.DARK_GRAY
            )
        )
        //E
        piramide.add(
            Triangle(
                Vector3(-100.0, -100.0, 100.0),
                Vector3(100.0, -100.0, 100.0),
                Vector3(-100.0, 100.0, 100.0),
                Color.CYAN
            )
        )

        //F
        piramide.add(
            Triangle(
                Vector3(100.0, -100.0, 100.0),
                Vector3(100.0, 100.0, 100.0),
                Vector3(-100.0, 100.0, 100.0),
                Color.PINK
            )
        )
        //G
        piramide.add(
            Triangle(
                Vector3(-100.0, -100.0, 100.0),
                Vector3(-100.0, 100.0, 100.0),
                Vector3(-100.0, -100.0, -100.0),
                Color.YELLOW
            )
        )
        //H
        piramide.add(
            Triangle(
                Vector3(-100.0, 100.0, 100.0),
                Vector3(-100.0, 100.0, -100.0),
                Vector3(-100.0, -100.0, -100.0),
                Color.magenta
            )
        )
        //I
        piramide.add(
            Triangle(
                Vector3(-100.0, 100.0, -100.0),
                Vector3(100.0, 100.0, -100.0),
                Vector3(-100.0, -100.0, -100.0),
                Color.ORANGE
            )
        )
        //J
        piramide.add(
            Triangle(
                Vector3(-100.0, -100.0, -100.0),
                Vector3(100.0, 100.0, -100.0),
                Vector3(100.0, -100.0, -100.0),
                Color.gray
            )
        )
        //K
        piramide.add(
            Triangle(
                Vector3(100.0, -100.0, 100.0),
                Vector3(-100.0, -100.0, 100.0),
                Vector3(-100.0, -100.0, -100.0),
                Color.lightGray
            )
        )
        //L
        piramide.add(
            Triangle(
                Vector3(-100.0, -100.0, -100.0),
                Vector3(100.0, -100.0, -100.0),
                Vector3(100.0, -100.0, 100.0),
                Color.PINK
            )
        )


    }


    override fun paintComponent(g: Graphics) {
        render(g)
    }

    fun render(g : Graphics){
        val heading = Math.toRadians(headingSlider.value.toDouble())
        val pitching = Math.toRadians(pitchSlider.value.toDouble())
        val worldMatrix = setRotationMatrix(heading,pitching)
        /*
            СameraClass.getWorldMatrix()
        cameraClass.setxRotation(heading)
        cameraClass.setyRotation(pitching)
         */
        val img = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val zBuffer = DoubleArray(img.width * img.height)
        // initialize array with extremely far away depths
        for (q in zBuffer.indices) {
            zBuffer[q] = java.lang.Double.NEGATIVE_INFINITY
        }




        //BACKGROUND
        g.color = Color.BLACK
        g.fillRect(0, 0, width, height)


        var mode : Int = 0
        when(mode){
            0 -> {
                for (t in piramide)
                    drawTriangle(t, img, zBuffer, worldMatrix,Color.ORANGE)
                g.drawImage(img, 0, 0, null)
            }
            1 -> {
                //WIRES MODEL
                for (t in piramide)
                    drawWiresPolygone(t,img,zBuffer,worldMatrix,color = Color.red)
                g.drawImage(img,0,0,null)
            }
        }


    }

    fun setRotationMatrix(heading : Double, pitching : Double) : Matrix3 {

        val transformHeading = Matrix3(
            cos(heading), 0.0, -sin(heading),
            0.0, 1.0, 0.0,
            sin(heading), 0.0, cos(heading)
        )

        val transformPitching= Matrix3(
            1.0, 0.0, 0.0,
            0.0, cos(pitching), sin(pitching),
            0.0, -sin(pitching), cos(pitching)
        )

        return transformHeading.mult(transformPitching)
    }
    // Нарисовать треугольник
    fun drawTriangle(Triangle: Triangle, img :BufferedImage, zBuffer: DoubleArray, worldMatix : Matrix3, color: Color ){
        var v1 = worldMatix.mult(Triangle.v1)
        var v2 = worldMatix.mult(Triangle.v2)
        var v3 = worldMatix.mult(Triangle.v3)

        // трансляция в центр кадра
        v1.x += (width / 2).toDouble()
        v1.y += (height / 2).toDouble()
        v2.x += (width / 2).toDouble()
        v2.y += (height / 2).toDouble()
        v3.x += (width / 2).toDouble()
        v3.y += (height / 2).toDouble()

        // compute rectangular bounds for Triangle

        val minX = 0.0.coerceAtLeast(ceil(v1.x.coerceAtMost(v2.x.coerceAtMost(v3.x)))).toInt()
        val maxX = (img.width - 1).toDouble().coerceAtMost(floor(v1.x.coerceAtLeast(v2.x.coerceAtLeast(v3.x)))).toInt()
        val minY = 0.0.coerceAtLeast(ceil(v1.y.coerceAtMost(v2.y.coerceAtMost(v3.y)))).toInt()
        val maxY = (img.height - 1).toDouble().coerceAtMost(floor(v1.y.coerceAtLeast(v2.y.coerceAtLeast(v3.y)))).toInt()


        val TriangleArea =(v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x)
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                val b1 = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / TriangleArea
                val b2 = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / TriangleArea
                val b3 = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / TriangleArea
                if (b1 in 0.0..1.0 && b2 in 0.0..1.0 && b3 in 0.0..1.0) {
                    val depth = b1 * v1.z + b2 * v2.z + b3 * v3.z
                    val zIndex = y * img.width + x
                    if ((zBuffer[zIndex] < depth)) {
                        img.setRGB(x, y, Triangle.color.rgb)
                        zBuffer[zIndex] = depth
                    } }
            }
        }

    }
    // Нарисовать проволочную модель треугольника
    fun drawWiresPolygone(t: Triangle, img : BufferedImage, zBuffer: DoubleArray, endMatix: Matrix3, color: Color){
        try{
            val v1 = endMatix.mult(t.v1)
            val v2 = endMatix.mult(t.v2)
            val v3 = endMatix.mult(t.v3)
            // трансляция в центр кадра

            v1.x += (width / 2).toDouble()
            v1.y += (height / 2).toDouble()
            v2.x += (width / 2).toDouble()
            v2.y += (height / 2).toDouble()
            v3.x += (width / 2).toDouble()
            v3.y += (height / 2).toDouble()

            line(v1,v2,img, Color.red)
            line(v2,v3,img, Color.PINK)
            line(v3,v1,img,Color.ORANGE)


        } catch (e : Exception){

        }

    }
    // нарисовать линию
    fun line(v1 : Vector3, v2 : Vector3, img: BufferedImage, color : Color) {
        var x0 = v1.x.toInt()
        var y0 = v1.y.toInt()
        var z0 = v1.z.toInt()
        var x1 = v2.x.toInt()
        var y1 = v2.y.toInt()
        var z1 = v2.z.toInt()

        var steep = false;
        if (abs(x0-x1)<abs(y0-y1)) {
            x0 = y0.also {y0 = x0}
            x1 = y1.also {y1 = x1}
            steep = true;
        }
        if (x0>x1) {
            x0 = x1.also { x1 = x0 }
            y0 = y1.also { y1 = y0 }
        }
        var dx = x1-x0;
        var dy = y1-y0
        var derror2 = abs(dy / dx.toDouble());
        var error2 = 0.0;
        var y = y0;

        for (x in x0..x1) {
            if (steep) {
                if (x >= 0 && x < img.height && y >= 0 && y < img.width) {
                    img.setRGB(y, x, color.rgb)
                }
            } else {
                if (y >= 0 && y < img.height && x >= 0 && x < img.width) {
                    img.setRGB(x, y, color.rgb)
                }
            }
            error2 += derror2

            /*if (error2 > dx) {
                y += (if(y1>y0)1 else -1)
                error2 -= dx*2;*/
            if (error2 > 0.5) {
                y += if (y1 > y0) 1 else -1
                error2 -= 1.0

            }
        }
    }
    // Нарисовать точку
    fun drawDot(v1 : Vector3, img : BufferedImage, color : Color){
        // TODO: доделать ф. отрисовки точки
    }


    // MAIN THREAD
    override fun run() {
        while (true) {

            this.repaint()
        }
    }

/*
    private fun triangle(
        pts: Array<Vector2>,
        buf: Image,
        deph: DoubleArray,
        uvs: Array<Vector2>,
        bilinear: Boolean,
        ptso: Array<Vector3>,
        normals: Array<Vector3>,
        worldCoords: Array<Vector3>,
        ltype: LightModel,
        lights: Array<PointLight>,
        specularIntensity: Double,
        specularPower: Double,
        cameraPos: Vector3,
        wNormals: Array<Vector3>,
        useTexture: Boolean
    ) {
        val v1: Vector3 = mult(
            subMath(worldCoords[0], worldCoords[1]),
            subMath(worldCoords[2], worldCoords[1])
        ).getNormalVector()
        if (dot(subMath(cameraPos, worldCoords[0]).getNormalVector(), v1) > 0) {
            return
        }
        val n: Vector3 = addVect(wNormals[0], addVect(wNormals[1], wNormals[2])).getNormalVector()
        var gouraud: Array<Vector3?>? = null
        if (ltype === LightModel.GOURAUD) {
            gouraud = arrayOfNulls<Vector3>(lights.size * 3)
            for (i in lights.indices) {
                for (j in 0..2) {
                    gouraud[i * 3 + j] = lights[i].getSimpleLightModelColor(
                        worldCoords[j], wNormals[j],
                        specularIntensity, specularPower, cameraPos
                    )
                }
            }
        }
        val w: Int = buf.width
        val h: Int = buf.height
        val xmin =
            Math.max(0, Math.floor(w * min(min(pts[0].x, pts[1].x), pts[2].x)).toInt())
        val xmax =
            Math.min(w, Math.floor(w * max(max(pts[0].x, pts[1].x), pts[2].x)).toInt() + 1)
        val ymin =
            Math.max(0, Math.floor(h * min(min(pts[0].y, pts[1].y), pts[2].y)).toInt())
        val ymax =
            Math.min(h, Math.floor(h * max(max(pts[0].y, pts[1].y), pts[2].y)).toInt() + 1)
        for (x in xmin until xmax) {
            for (y in ymin until ymax) {
                val bc: Vector3 = barycentric(pts, Vector2(x / w.toDouble(), y / h.toDouble()), ptso)
                if (bc.x < 0 || bc.y < 0 || bc.z < 0) {
                    continue
                }
                val d: Double = deph[0] * bc.x + deph[1] * bc.y + deph[2] * bc.z
                if (buf.getDeph(x, y) < d) {
                    val uv: Vector2 = addVect(
                        mult(uvs[0], bc.x),
                        addVect(mult(uvs[1], bc.y), mult(uvs[2], bc.z))
                    )
                    var texColor: Vector3? = null
                    if (useTexture) {
                        texColor = if (bilinear) {
                            tex.getBilinear(uv)
                        } else {
                            tex.getNearest(uv)
                        }
                    } else {
                        texColor = Vector3(0.5, 0.5, 0.5)
                    }
                    val wPos: Vector3 = addVect(
                        mult(worldCoords[0], bc.x),
                        addVect(mult(worldCoords[1], bc.y), mult(worldCoords[2], bc.z))
                    )
                    lateinit var lightColor: Vector3
                    if (ltype === LightModel.SIMPLE) {
                        lightColor = Vector3(0.0, 0.0, 0.0)
                        for (i in lights.indices) {
                            val lc: Vector3 = lights[i]
                                .getSimpleLightModelColor(wPos, n, specularIntensity, specularPower, cameraPos)
                            lightColor = add(lightColor, lc)
                        }
                    } else if (ltype === LightModel.GOURAUD) {
                        lightColor = Vector3(0.0, 0.0, 0.0)
                        for (i in lights.indices) {
                            val lc: Vector3 = addVect(
                                mult(gouraud!![i * 3], bc.x),
                                addVect(mult(gouraud!![i * 3 + 1], bc.y), mult(gouraud!![i * 3 + 2], bc.z))
                            )
                            lightColor = addVect(lightColor, lc)
                        }
                    } else if (ltype === LightModel.PHONG) {
                        val nn: Vector3 = addVect(
                            mult(wNormals[0], bc.x),
                            addVect(mult(wNormals[1], bc.y), mult(wNormals[2], bc.z))
                        )
                        lightColor = Vector3(0.0, 0.0, 0.0)
                        for (i in lights.indices) {
                            val lc: Vector3 = lights[i]
                                .getSimpleLightModelColor(wPos, nn, specularIntensity, specularPower, cameraPos)
                            lightColor = addVect(lightColor, lc)
                        }
                    }
                    buf.setColor(x, y, multSimple(lightColor, texColor))
                    buf.setDeph(x, y, d)
                }
            }
        }
    }
*/



}


