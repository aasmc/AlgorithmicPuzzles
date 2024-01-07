package leetcode.top_interview_150.array_string.test_justification

class TextJustificationSolution {

    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val result = mutableListOf<String>()
        var currentLineLength = 0
        // current line is represented as a list of strings so that it is
        // easy to add spaces to each word individually
        val currentLine = mutableListOf<String>()
        var i = 0
        // traverse all words in input array
        while (i < words.size) {
            // case 1: line complete
            // we can check it by comparing maxWidth with the length of current
            // line + length of the next word in the input array + number of single space
            // characters between all words in this line plus the next word, that is
            // because we need to have at least one space between words.
            // the number of single space characters between words in this line
            // plus the next word is calculated as the number of words in this line,
            // e.g. we have line, represented as a list of words: ["this", "is", "an"]
            // maxWidth = 16, next word = "example"
            // currentLineLength = 8
            // if we add next word to the list of current words, then
            // currentLineLength will be = 8 + 7 = 15
            // but we will have 4 words, so we will need to add 3 spaces (same as the
            // number of words BEFORE adding "example" to the list) between
            // the words to get a correct line: "this is an example",
            // in this case the length of the line will exceed maxWidth
            if (currentLineLength + currentLine.size + words[i].length > maxWidth) {
                // here we calculate the extra space that must be evenly distributed
                // between the words in the line
                val extraSpace = maxWidth - currentLineLength
                // now we need to calculate the number of spaces we need to add after
                // each word in the line except the last word.
                // this is done by dividing extra space by the number of words in the line
                // minus one, because we don't need to add spaces after the last word
                // and here we take into account an edge case, when there's only one
                // word on the line - if we didn't use maxOf(1, currentLine.size - 1),
                // then we would get a division by 0 exception
                val numSpaces = extraSpace / maxOf(1, currentLine.size - 1)
                // here we take into account the number of spaces that are left after
                // we evenly distributed extra spaces
                var remainder = extraSpace % maxOf(1, currentLine.size - 1)
                // for each remaining word in the line except the last word
                // (but if there's only one word, we add space after it as well)
                for (j in 0 until maxOf(1, currentLine.size - 1)) {
                    // first add evenly distributed spaces to the word
                    currentLine[j] = currentLine[j] + " ".repeat(numSpaces)
                    // and if we have remaining spaces after even distribution
                    if (remainder > 0) {
                        // add one space to current word and decrement the number
                        // of remaining spaces
                        currentLine[j] += " "
                        --remainder
                    }
                }
                // after we have distributed spaces between words, concatenate this line
                // and add it to the resulting list
                result.add(currentLine.joinToString(separator = ""))
                // reset line and length
                currentLine.clear()
                currentLineLength = 0
            }
            // case 2: line incomplete
            // this is a simple case, we need to add current word to the line and
            // add its length to the currentLineLength
            currentLine.add(words[i])
            currentLineLength += words[i].length
            ++i
        }

        // handle last line,
        val lastLine = currentLine.joinToString(separator = " ")
        val trailingSpace = maxWidth - lastLine.length
        result.add(lastLine + " ".repeat(trailingSpace))
        return result
    }

}