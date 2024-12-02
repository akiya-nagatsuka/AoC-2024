fun main() {
    val reports = readInput("Day02").map { it.split(" ").map { it.toInt() } }

    // Part 1
    fun check(report: List<Int>) = report.windowed(2).let { pairs ->
        pairs.all { it[0] - it[1] in  1..3 } || pairs.all { it[1] - it[0] in 1..3 }
    }
    val result1 = reports.count { check(it) }
    println(result1)

    // Part 2
    val result2 = reports.count {
        check(it) || List(it.size) { i -> it.toMutableList().apply { removeAt(i) } }.any { check(it) }
    }
    println(result2)
}