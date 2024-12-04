fun main() {
    val lines = readInput("Day04")

    val horizontalLines = lines.map { it.toCharArray().toList() }
    val verticalLines = horizontalLines.transpose()
    val horizontal: List<List<Char>> = horizontalLines.map { it.windowed(4) }.flatten()
    val vertical: List<List<Char>> = verticalLines.map { it.windowed(4) }.flatten()

    val diagonalLines = mutableListOf<List<Char>>()
    val additionalWidth = horizontalLines.size + verticalLines.size - 1

    for (i in 0..<additionalWidth) {
        var diagonal = mutableListOf<Char>()
        for (columnIndex in 0..i) {
            val rowIndex = i - columnIndex
            val row = lines.getOrNull(rowIndex)
            if (row == null) continue
            val symbol = row.getOrNull(columnIndex)
            if (symbol == null) continue
            diagonal.add(symbol)
        }
        diagonalLines += diagonal
    }

    for (i in 0..<additionalWidth) {
        var diagonal = mutableListOf<Char>()
        for (rowIndex in 0..i) {
            val columnIndex = i - rowIndex
            val row = lines.getOrNull(rowIndex)
            if (row == null) continue
            val symbol = row.getOrNull(columnIndex)
            if (symbol == null) continue
            diagonal.add(symbol)
        }
        diagonalLines += diagonal
    }

    val diagonals = diagonalLines.map { it.windowed(4) }.flatten()

    val allLines = horizontal + vertical + diagonals
    val result1 = allLines.count { chars -> chars.joinToString("").let { it == "XMAS" || it.reversed() == "XMAS" } }

    result1.println()
}