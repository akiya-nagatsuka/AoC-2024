fun main() {
    val lines = readInput("Day03")

    fun part1(input: String): Int = Regex("""mul\((\d{1,3}),(\d{1,3})\)""").findAll(input).map { it.destructured }
        .map { it.component1().toInt() * it.component2().toInt() }.sum()

    fun part2(input: String): Int = input.split("do()").map { it.substringBefore("don't()") }.sumOf { part1(it) }

    val answer1 = lines.sumOf {
        part1(it)
    }
    answer1.println()

    val answer2 = part2(lines.joinToString(separator = ""))
    answer2.println()
}