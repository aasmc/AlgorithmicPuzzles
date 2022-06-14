package codewars

/**
 * Write a function that takes in a string of one or more words, and returns the same string,
 * but with all five or more letter words reversed (Just like the name of this Kata). Strings
 * passed in will consist of only letters and spaces. Spaces will be included only when more
 * than one word is present.
 *
 * Examples: spinWords( "Hey fellow warriors" ) => returns "Hey wollef sroirraw"
 * spinWords( "This is a test") => returns "This is a test"
 * spinWords( "This is another test" )=> returns "This is rehtona test"
 */
fun spinWords(sentence: String): String {
    var indexOfSpace = sentence.indexOf(" ")
    if (indexOfSpace == -1) {
        return if (sentence.length >= 5) {
            sentence.reversed()
        } else {
            sentence
        }
    }
    var start = 0
    val sb = StringBuilder()
    while (indexOfSpace != -1) {
        val substr = sentence.substring(start, indexOfSpace)
        if (substr.length >= 5) {
            sb.append(substr.reversed())
        } else {
            sb.append(substr)
        }
        start = indexOfSpace
        while (start < sentence.length && sentence[start] == ' ') {
            sb.append(' ')
            ++start
        }
        indexOfSpace = sentence.indexOf(" ", startIndex = start)
    }
    if (start < sentence.length) {
        val substr = sentence.substring(startIndex = start)
        if (substr.length >= 5) {
            sb.append(substr.reversed())
        } else {
            sb.append(substr)
        }
    }
    return sb.toString()
}
