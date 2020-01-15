import java.lang.StringBuilder
import java.util.ArrayList

class CompositeDemo {

    companion object {
        var compositeStringBuilder = StringBuilder()
    }

    fun main(args: Array<String>) {

        val music = Directory("MUSIC")
        val scorpions = Directory("SCORPIONS")
        val dio = Directory("DIO")
        val track1 = CFile("Don't wary, be happy.mp3")
        val track2 = CFile("track2.m3u")
        val track3 = CFile("Wind of change.mp3")
        val track4 = CFile("Big city night.mp3")
        val track5 = CFile("Rainbow in the dark.mp3")
        music.add(track1)
        music.add(scorpions)
        music.add(track2)
        scorpions.add(track3)
        scorpions.add(track4)
        scorpions.add(dio)
        dio.add(track5)
        music.ls()
    }
}

class CFile(private val name: String) {

    fun ls() {
        println("${CompositeDemo.compositeStringBuilder} $name")
    }
}

internal class Directory(private val name: String) {
    private val includedFiles = ArrayList<Any>()

    fun add(obj: Any) {
        includedFiles.add(obj)
    }

    fun ls() {
        println("${CompositeDemo.compositeStringBuilder} $name")
        CompositeDemo.compositeStringBuilder.append("   ")
        for (obj in includedFiles) {
            // Recover the type of this object
            val name = obj.javaClass.getSimpleName()
            if (name == "Directory") {
                (obj as Directory).ls()
            } else {
                (obj as CFile).ls()
            }
        }
        CompositeDemo.compositeStringBuilder.setLength(CompositeDemo.compositeStringBuilder.length - 3)
    }
}