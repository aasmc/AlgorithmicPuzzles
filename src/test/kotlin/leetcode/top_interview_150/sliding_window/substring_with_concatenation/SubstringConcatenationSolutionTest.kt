package leetcode.top_interview_150.sliding_window.substring_with_concatenation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SubstringConcatenationSolutionTest {

    private val sut = SubstringConcatenationSolution()
    @Test
    fun testFindIndices() {
        val input = "barfoothefoobarman"
        val words = arrayOf("foo", "bar")
        val res = sut.findSubstring(input, words)
        assertEquals(2, res.size)
        val sorted = res.sorted()
        assertEquals(0, sorted.first())
        assertEquals(9, sorted.last())

        val in2 = "wordgoodgoodgoodbestword"
        val words2 = arrayOf("word","good","best","word")
        assertEquals(0, sut.findSubstring(in2, words2).size)

        val in3 = "barfoofoobarthefoobarman"
        val words3 = arrayOf("bar","foo","the")

        val res3 = sut.findSubstring(in3, words3)
        val sorted3 = res3.sorted()
        assertEquals(3, sorted3.size)
        assertEquals(6, sorted3[0])
        assertEquals(9, sorted3[1])
        assertEquals(12, sorted3[2])

        val in4 = "wordgoodgoodgoodbestword"
        val words4 = arrayOf("word","good","best","good")
        val res4 = sut.findSubstring(in4, words4)
        assertEquals(1, res4.size)
        assertEquals(8, res4.first())

        val in5 = "bcabbcaabbccacacbabccacaababcbb"
        val words5 = arrayOf("c","b","a","c","a","a","a","b","c")
        val res5 = sut.findSubstring(in5, words5)
        val sorted5 = res5.sorted()
        assertEquals(6, sorted5.size)
        assertEquals(6, sorted5[0])
        assertEquals(16, sorted5[1])
        assertEquals(17, sorted5[2])
        assertEquals(18, sorted5[3])
        assertEquals(19, sorted5[4])
        assertEquals(20, sorted5[5])

        val in6 = "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel"
        val words6 = arrayOf("dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb")
        val res6 = sut.findSubstring(in6, words6)
        res6.forEach {
            print("$it ")
        }
        println()
    }

}