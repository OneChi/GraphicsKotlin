package ru.vanchikov.laboratory1.Logic

import Mathematic.Matrix3
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
class Engine : JPanel(){
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
        headingSlider.addChangeListener {this.repaint() }
        pitchSlider.addChangeListener {this.repaint() }

    }

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
                Vertex(-100.0, 100.0, 100.0),
                Vertex(100.0, 100.0, 100.0),
                Vertex(-100.0, 100.0, -100.0),
                Color.RED
            )
        )
        //B
        piramide.add(
            Triangle(
                Vertex(100.0, 100.0, 100.0),
                Vertex(100.0, 100.0, -100.0),
                Vertex(-100.0, 100.0, -100.0),
                Color.GREEN
            )
        )
        //C
        piramide.add(
            Triangle(
                Vertex(100.0, -100.0, 100.0),
                Vertex(100.0, 100.0, -100.0),
                Vertex(100.0, 100.0, 100.0),
                Color.BLUE
            )
        )
        //D
        piramide.add(
            Triangle(
                Vertex(100.0, -100.0, 100.0),
                Vertex(100.0, -100.0, -100.0),
                Vertex(100.0, 100.0, -100.0),
                Color.DARK_GRAY
            )
        )
        //E
        piramide.add(
            Triangle(
                Vertex(-100.0, -100.0, 100.0),
                Vertex(100.0, -100.0, 100.0),
                Vertex(-100.0, 100.0, 100.0),
                Color.CYAN
            )
        )

        //F
        piramide.add(
            Triangle(
                Vertex(100.0, -100.0, 100.0),
                Vertex(100.0, 100.0, 100.0),
                Vertex(-100.0, 100.0, 100.0),
                Color.PINK
            )
        )
        //G
        piramide.add(
            Triangle(
                Vertex(-100.0, -100.0, 100.0),
                Vertex(-100.0, 100.0, 100.0),
                Vertex(-100.0, -100.0, -100.0),
                Color.YELLOW
            )
        )
        //H
        piramide.add(
            Triangle(
                Vertex(-100.0, 100.0, 100.0),
                Vertex(-100.0, 100.0, -100.0),
                Vertex(-100.0, -100.0, -100.0),
                Color.magenta
            )
        )
        //I
        piramide.add(
            Triangle(
                Vertex(-100.0, 100.0, -100.0),
                Vertex(100.0, 100.0, -100.0),
                Vertex(-100.0, -100.0, -100.0),
                Color.ORANGE
            )
        )
        //J
        piramide.add(
            Triangle(
                Vertex(-100.0, -100.0, -100.0),
                Vertex(100.0, 100.0, -100.0),
                Vertex(100.0, -100.0, -100.0),
                Color.gray
            )
        )
        //K
        piramide.add(
            Triangle(
                Vertex(100.0, -100.0, 100.0),
                Vertex(-100.0, -100.0, 100.0),
                Vertex(-100.0, -100.0, -100.0),
                Color.lightGray
            )
        )
        //L
        piramide.add(
            Triangle(
                Vertex(-100.0, -100.0, -100.0),
                Vertex(100.0, -100.0, -100.0),
                Vertex(100.0, -100.0, 100.0),
                Color.PINK
            )
        )


    }


    override fun paintComponent(g: Graphics) {
        val heading = Math.toRadians(headingSlider.value.toDouble())
        val pitching = Math.toRadians(pitchSlider.value.toDouble())
        val endMatix = setRotationMatrix(heading,pitching)
        val g2 = g as Graphics
        val img = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val zBuffer = DoubleArray(img.width * img.height)
        // initialize array with extremely far away depths
        for (q in zBuffer.indices) {
            zBuffer[q] = java.lang.Double.NEGATIVE_INFINITY
        }
        //BACKGROUND
        g2.color = Color.BLACK
        g2.fillRect(0, 0, width, height)

        var mode : Int = 1
        when(mode){
            0 -> {
                for (t in piramide)
                    drawTriangle(t, img, zBuffer, endMatix,Color.ORANGE)
                g2.drawImage(img, 0, 0, null)
            }
            1 -> {
                //WIRES MODEL
                for (t in piramide)
                    drawWiresPolygone(t,img,zBuffer,endMatix,color = Color.red)
                g2.drawImage(img,0,0,null)
            }
        }

    }

    fun setRotationMatrix(heading : Double, pitching : Double) : Matrix3{
        val transformHeading = Matrix3(
            doubleArrayOf(
                cos(heading), 0.0, -sin(heading),
                0.0,          1.0, 0.0,
                sin(heading), 0.0, cos(heading)
            )
        )

        val transformPitching= Matrix3(
            doubleArrayOf(
                1.0 , 0.0, 0.0 ,
                0.0,cos(pitching),sin(pitching),
                0.0,-sin(pitching) , cos(pitching)
            )
        )

        return transformHeading.multiply(transformPitching)
    }

    fun drawTriangle(t: Triangle, img :BufferedImage, zBuffer: DoubleArray, endMatix : Matrix3,color: Color ){
        var v1 = endMatix.transform(t.v1)
        var v2 = endMatix.transform(t.v2)
        var v3 = endMatix.transform(t.v3)

        // трансляция в центр кадра
        v1.x += (width / 2).toDouble()
        v1.y += (height / 2).toDouble()
        v2.x += (width / 2).toDouble()
        v2.y += (height / 2).toDouble()
        v3.x += (width / 2).toDouble()
        v3.y += (height / 2).toDouble()

        // compute rectangular bounds for triangle

        val minX = 0.0.coerceAtLeast(ceil(v1.x.coerceAtMost(v2.x.coerceAtMost(v3.x)))).toInt()
        val maxX = (img.width - 1).toDouble().coerceAtMost(floor(v1.x.coerceAtLeast(v2.x.coerceAtLeast(v3.x)))).toInt()
        val minY = 0.0.coerceAtLeast(ceil(v1.y.coerceAtMost(v2.y.coerceAtMost(v3.y)))).toInt()
        val maxY = (img.height - 1).toDouble().coerceAtMost(floor(v1.y.coerceAtLeast(v2.y.coerceAtLeast(v3.y)))).toInt()

        /*
        val minX = Math.max(0.0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x)))).toInt()
        val maxX = Math.min((img.width - 1).toDouble(), Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x)))).toInt()
        val minY = Math.max(0.0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y)))).toInt()
        val maxY = Math.min((img.height - 1).toDouble(), Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y)))).toInt()
         */

        val triangleArea =(v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x)
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                val b1 = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / triangleArea
                val b2 = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / triangleArea
                val b3 = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / triangleArea
                if (b1 in 0.0..1.0 && b2 in 0.0..1.0 && b3 in 0.0..1.0) {
                    val depth = b1 * v1.z + b2 * v2.z + b3 * v3.z
                    val zIndex = y * img.width + x
                    if ((zBuffer[zIndex] < depth)) {
                        img.setRGB(x, y, t.color.rgb)
                        zBuffer[zIndex] = depth
                    } }
            }
        }

    }

    fun drawWiresPolygone(t: Triangle,img : BufferedImage, zBuffer: DoubleArray, endMatix: Matrix3, color: Color){
        val v1 = endMatix.transform(t.v1)
        val v2 = endMatix.transform(t.v2)
        val v3 = endMatix.transform(t.v3)
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

    }


    fun line(v1 : Vertex, v2 : Vertex, img: BufferedImage,color : Color) {
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
        var dy = y1-y0;
        var derror2 = abs(dy)*2;
        var error2 = 0;
        var y = y0;
        for (x in x0..x1) {
            if (steep) {
                img.setRGB(y, x, color.rgb)
            } else {
                img.setRGB(x, y, color.rgb)
            }
            error2 += derror2

            if (error2 > dx) {
                y += (if(y1>y0)1 else -1)
                error2 -= dx*2;
            }
        }
    }

}



/*

Задать трехмерную модель точками и сделать ее вращение, масштабирование, перемещение
Освещение не менее 3 прожекторами
Несколько режимов работы
1)Проволочная модель
2)Закрашенная модель
А) простая закраска
Б) закраска методом гуго?
В) закраска методом фонга?

*/