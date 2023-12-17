import java.io.FileInputStream
import java.util.*

fun main() {
    val sc = Scanner(FileInputStream("roles.txt"))

    val roles = mutableListOf<String>()
    val textLines = mutableListOf<Pair<Int, String>>()

    var isRolesSection = false
    var isTextLinesSection = false

    var lineNumber = 0

    while (sc.hasNextLine()) {
        val line = sc.nextLine().trim()

        when {
            line.startsWith("roles:") -> {
                isRolesSection = true
                isTextLinesSection = false
            }

            line.startsWith("textLines:") -> {
                isRolesSection = false
                isTextLinesSection = true
            }

            isRolesSection -> roles.add(line)
            isTextLinesSection -> {
                lineNumber++
                textLines.add(lineNumber to line)
            }
        }
    }

    val roleMap = mutableMapOf<String, MutableList<Pair<Int, String>>>()

    for ((lineNumber, textLine) in textLines) {
        val roleDelimiterIndex = textLine.indexOf(":")
        val roleName = textLine.substring(0, roleDelimiterIndex).trim()
        val lineText = textLine.substring(roleDelimiterIndex + 1).trim()

        if (roleMap.containsKey(roleName)) {
            roleMap[roleName]?.add(lineNumber to lineText)
        } else {
            roleMap[roleName] = mutableListOf(lineNumber to lineText)
        }
    }


    for (role in roles) {
        val roleName = role.trim()
        val roleLines = roleMap[roleName] ?: emptyList()

        if (roleLines.isNotEmpty()) {
            println("$roleName:")
            roleLines.forEach { (index, line) ->
                println("$index) $line")
            }
            println()
        }
    }
}