package advent_of_code.year_2022.day_07

import advent_of_code.readLinesFromFile
import java.util.*
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val lines = readLinesFromFile("year_2022/day_07", )
    val root = buildDirectoryTree(lines)
    val size = partOneSolution(root)
    println(size)
    val toDelete = partTwoSolution(root)
    println(toDelete)
}

const val DIR_SIZE_CEILING = 100000L
const val TOTAL_DISK_SPACE = 70000000L
const val SPACE_NEEDED = 30000000L

fun partTwoSolution(root: Directory): Long {
    val spaceUsed = root.calculateSize()
    val freeSpace = TOTAL_DISK_SPACE - spaceUsed
    val needToDelete = abs(freeSpace - SPACE_NEEDED)
    var candidateSize = Long.MAX_VALUE
    val queue: LinkedList<Directory> = LinkedList()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val current = queue.poll()
        val currentSize = current.calculateSize()
        if (currentSize > needToDelete) {
            candidateSize = min(candidateSize, currentSize)
        }
        current?.children?.forEach {
            queue.add(it)
        }
    }
    return candidateSize
}

fun partOneSolution(root: Directory): Long {
    var result = 0L
    val queue = LinkedList<Directory>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val currentDir = queue.poll()
        val size = currentDir.calculateSize()
        if (size < DIR_SIZE_CEILING) {
            result += size
        }
        currentDir?.children?.forEach {
            queue.add(it)
        }
    }
    return result
}

private fun buildDirectoryTree(lines: List<String>): Directory {
    var idx = 0
    var root: Directory? = null
    var currentRoot: Directory? = null
    while (idx < lines.size) {
        var line = lines[idx]
        if (line[0] == '$') {
            val cmd = line.drop(2).substring(0, 2)
            if (cmd == "cd" && line[5] == '/') {
                root = Directory("/")
                currentRoot = root
            } else if (cmd == "cd" && line[5] == '.') {
                currentRoot = currentRoot?.parent
            } else if (cmd == "cd") {
                val dirName = line.split(" ").last()
                val newParent = currentRoot?.children?.first { it.name == dirName }
                currentRoot = newParent
            }
            ++idx
        } else {
            while (!line.startsWith("$")) {
                if (line.startsWith("dir")) {
                    val child = Directory(name = line.drop(4), parent = currentRoot)
                    currentRoot?.children?.add(child)
                } else {
                    val (sizeStr, fName) = line.split(" ")
                    val file = FFile(name = fName, size = sizeStr.toLong())
                    currentRoot?.files?.add(file)
                }
                ++idx
                if (idx == lines.size) break
                line = lines[idx]
            }
        }
    }
    return root!!
}

class Directory(
    val name: String,
    var parent: Directory? = null,
    val children: MutableList<Directory> = mutableListOf(),
    val files: MutableList<FFile> = mutableListOf()
) {
    fun calculateSize(): Long {
        fun helper(root: Directory): Long {
            val filesSize = root.files.sumOf { it.size }
            return when {
                root.children.isEmpty() -> filesSize
                else -> {
                    var childrenSize = 0L
                    for (child in root.children) {
                        childrenSize += helper(child)
                    }
                    filesSize + childrenSize
                }
            }
        }
        return helper(this)
    }
}

data class FFile(
    val name: String,
    val size: Long
)
