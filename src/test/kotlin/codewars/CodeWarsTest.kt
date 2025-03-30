package codewars

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class CodeWarsTest {
    @Test
    fun test1() {
        assertEquals("emocleW", spinWords("Welcome"))
    }

    @Test
    fun test2() {
        assertEquals("Hey wollef sroirraw", spinWords("Hey fellow warriors"))
    }

    @Test
    fun test3() {
        assertEquals("This is a test", spinWords("This is a test"))
    }

    @Test
    fun test4() {
        assertEquals("This is rehtona test", spinWords("This is another test"))
    }

    @Test
    fun test5() {
        assertEquals("You are tsomla to the last test", spinWords("You are almost to the last test"))
    }

    @Test
    fun test6() {
        assertEquals("Just gniddik ereht is llits one more", spinWords("Just kidding there is still one more"))
    }

    private val rand: Random = Random()
    private val validCharacters: List<String> = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("")
    private val testsToRun: Int = 2000
    private val randomWordMaxLength: Int = 9
    private val randomSentenceMaxLength: Int = 15

    @Test
    fun exampleTests() {
        assertEquals("emocleW", spinWords("Welcome"))
        assertEquals("Hey wollef sroirraw", spinWords("Hey fellow warriors"))
        assertEquals("This is a test", spinWords("This is a test"))
        assertEquals("This is rehtona test", spinWords("This is another test"))
        assertEquals("This ecnetnes is a ecnetnes", spinWords("This sentence is a sentence"))
        assertEquals("You are tsomla to the last test", spinWords("You are almost to the last test"))
        assertEquals("Just gniddik ereht is llits one more", spinWords("Just kidding there is still one more"))
    }

    @Test
    fun hhgttgQuotes() {
        assertEquals("The spihs hung in the sky in much the same way that skcirb do not", spinWords("The ships hung in the sky in much the same way that bricks do not"))
        assertEquals("So long and sknaht for all the fish", spinWords("So long and thanks for all the fish"))
        assertEquals("A nommoc ekatsim that elpoep make when gniyrt to ngised gnihtemos yletelpmoc foorploof is to etamitserednu the ytiunegni of etelpmoc sloof", spinWords("A common mistake that people make when trying to design something completely foolproof is to underestimate the ingenuity of complete fools"))
        assertEquals("He was gnirats at the stnemurtsni with the air of one who is gniyrt to trevnoc tiehnerhaF to edargitnec in his head elihw his esuoh is gninrub down", spinWords("He was staring at the instruments with the air of one who is trying to convert Fahrenheit to centigrade in his head while his house is burning down"))
        assertEquals("It is a ekatsim to kniht you can evlos any rojam smelborp just with seotatop", spinWords("It is a mistake to think you can solve any major problems just with potatoes"))
        assertEquals("gnihtoN slevart retsaf than the deeps of thgil with the elbissop noitpecxe of bad news", spinWords("Nothing travels faster than the speed of light with the possible exception of bad news"))
    }

    @Test
    fun fireflyQuotes() {
        assertEquals("esruC your neddus but elbativeni layarteb", spinWords("Curse your sudden but inevitable betrayal"))
        assertEquals("yrevE man ereht go back edisni or we will blow a new retarc in this elttil moon", spinWords("Every man there go back inside or we will blow a new crater in this little moon"))
        assertEquals("Did gnihtemos just fly off my marrog ship", spinWords("Did something just fly off my gorram ship"))
        assertEquals("I do not eveileb ereht is a rewop in the esrev that can stop eelyaK from gnieb lufreehc", spinWords("I do not believe there is a power in the verse that can stop Kaylee from being cheerful"))
        assertEquals("Been a long time ecnis ecneitaP shot me and that was due to a yltcefrep etamitigel tcilfnoc of tseretni", spinWords("Been a long time since Patience shot me and that was due to a perfectly legitimate conflict of interest"))
        assertEquals("I am very yrros if she deppit off enoyna tuoba your ylgninnuc delaecnoc herd of cows", spinWords("I am very sorry if she tipped off anyone about your cunningly concealed herd of cows"))
    }

    @Test
    fun famousQuotes() {
        assertEquals("I love you the more in that I eveileb you had dekil me for my own sake and for gnihton else", spinWords("I love you the more in that I believe you had liked me for my own sake and for nothing else"))
        assertEquals("You tonnac ekahs sdnah with a dehcnelc fist", spinWords("You cannot shake hands with a clenched fist"))
        assertEquals("Let us ecifircas our yadot so that our nerdlihc can have a retteb worromot", spinWords("Let us sacrifice our today so that our children can have a better tomorrow"))
        assertEquals("All that we see or seem is but a maerd nihtiw a maerd", spinWords("All that we see or seem is but a dream within a dream"))
        assertEquals("erehT is no mrahc lauqe to ssenrednet of traeh", spinWords("There is no charm equal to tenderness of heart"))
        assertEquals("Good tnemgduj semoc from ecneirepxe and a lot of that semoc from bad tnemgduj", spinWords("Good judgment comes from experience and a lot of that comes from bad judgment"))
        assertEquals("The emerpus art of war is to eudbus the ymene tuohtiw gnithgif", spinWords("The supreme art of war is to subdue the enemy without fighting"))
    }

    @Test
    fun randomTests() {
        for(i in 0..testsToRun) {
            val sentence = generateSampleSentence()
            assertEquals(testingSpinWords(sentence), spinWords(sentence))
        }
    }

    private fun generateSampleSentence(length: Int = this.rand.nextInt(randomSentenceMaxLength) + 1): String = generateSequence {
        generateRandomWord(this.rand.nextInt(this.randomWordMaxLength) + 1)
    }.take(length).joinToString(" ")

    private fun generateRandomWord(length: Int): String = generateSequence {
        validCharacters.shuffled().first()
    }.take(length).joinToString("")

    private fun testingSpinWords(sentence: String): String = sentence.split(" ").joinToString(" ") {
        if (it.length >= 5) it.reversed() else it
    }
}