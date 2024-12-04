fun main() {
    val lines = readInput("Day04")

    // Part1
    val horizontalLines = lines.map { it.toCharArray().toList() }
    val verticalLines = horizontalLines.transpose()
    val horizontal: List<List<Char>> = horizontalLines.map { it.windowed(4) }.flatten()
    val vertical: List<List<Char>> = verticalLines.map { it.windowed(4) }.flatten()

    val diagonalLines = mutableListOf<List<Char>>()
    val additionalWidth = horizontalLines.size + verticalLines.size - 1

    for (i in 0..<additionalWidth) {
        var diagonal1 = mutableListOf<Char>()
        var diagonal2 = mutableListOf<Char>()

        for (columnIndex in 0..i) {
            val rowIndex1 = i - columnIndex
            val row1 = lines.getOrNull(rowIndex1)
            if (row1 != null) {
                val symbol = row1.getOrNull(columnIndex)
                if (symbol != null) {
                    diagonal1.add(symbol)
                }
            }

            val rowIndex2 = lines.size - 1 - i + columnIndex
            val row2 = lines.getOrNull(rowIndex2)
            if (row2 != null) {
                val symbol = row2.getOrNull(columnIndex)
                if (symbol != null) {
                    diagonal2.add(symbol)
                }
            }
        }

        diagonalLines += diagonal1
        diagonalLines += diagonal2
    }

    val diagonals = diagonalLines.map { it.windowed(4) }.flatten()

    val allLines = horizontal + vertical + diagonals
    val result1 = allLines.count { chars -> chars.joinToString("").let { it == "XMAS" || it.reversed() == "XMAS" } }

    result1.println()

    // Part2
    var result2 = 0
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == 'A') {
                listOf(
                    listOf(Point(x - 1, y - 1), Point(x + 1, y + 1)),
                    listOf(Point(x - 1, y + 1), Point(x + 1, y - 1)),
                ).all {
                    it.mapNotNull { lines.getOrNull(it.y)?.getOrNull(it.x) }.joinToString("").let { it == "SM" || it == "MS" }
                }.let { bool -> if (bool) result2++ }
            }
        }
    }

    result2.println()
}

data class Point(val x: Int, val y: Int)