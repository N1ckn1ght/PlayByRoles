import java.util.*
import kotlin.collections.ArrayList

fun main() {
    var map: MutableMap<String, ArrayList<Pair<Int, String>>> = HashMap()
    var line: String

    readLine()
    line = readLine()!!

    // read roles: until the textLines:
    while (line != "textLines:") {
        map[line] = arrayListOf()
        line = readLine()!!
    }

    // read textLines: until the end of input
    var i: Int = 0
    generateSequence { readLine() }
        .takeWhile { !it.isNullOrEmpty() }
        .forEach {
            i++
            val match = Regex("([^:]*): (.*)").find(it)
            if (match?.groupValues?.size == 3) {
                map[match.groupValues[1]]?.add(Pair<Int, String>(i, match.groupValues[2]))
            }
        }

    // sort by first role order if exist
    val order: Array<Pair<String, Int>> = Array(map.size){Pair<String, Int>("", 0)}
    i = 0
    for ((key, value) in map) {
        if (value.size > 0) {
            order[i] = Pair<String, Int>(key, value[0].first)
        } else {
            order[i] = Pair<String, Int>(key, Int.MAX_VALUE)
        }
        i++
    }
    order.sortWith(compareBy { it.second })

    for (role in order) {
        print("${role.first}\n")
        for (it in map[role.first]!!) {
            print("${it.first}) ${it.second}\n")
        }
        print("\n")
    }
}