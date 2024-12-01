import kotlin.math.abs

fun main() {
    val (list1, list2) = readInput("Day01").map { it.split("   ").map { it.toInt() } }.transpose()

    // Part 1
    val (list1Sorted, list2Sorted) = list1.sorted() to list2.sorted()
    var sum = 0
    for (i in list1Sorted.indices) {
        sum += abs(list1Sorted[i] - list2Sorted[i])
    }
    println(sum)

    // Part2
    val similarityMap = mutableMapOf<Int, Int>()
    val similarity = list1.sumOf {
        similarityMap.getOrPut(it) {
            it * list2.count { i -> it == i }
        }
    }
    println(similarity)
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    var transposed = mutableListOf<List<T>>()
    for (i in first().indices) {
        val col: MutableList<T> = mutableListOf()
        forEach { row ->
            col.add(row[i])
        }
        transposed.add(col)
    }
    return transposed
}
