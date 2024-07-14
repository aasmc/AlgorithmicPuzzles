package leetcode.top_interview_150.graph_bfs.min_genetic_mutation

class MinGeneticMutationSolution {

    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        if (startGene == endGene) {
            return 0
        }
        if (bank.isEmpty()) {
            return -1
        }
        val visited = hashSetOf<String>()
        val geneToMutationsCount = ArrayDeque<Pair<String, Int>>()
        geneToMutationsCount.addLast(startGene to 0)
        visited.add(startGene)

        while (geneToMutationsCount.isNotEmpty()) {
            val (currentGene, currentMutations) = geneToMutationsCount.removeFirst()
            if (currentGene == endGene) {
                return currentMutations
            }
            bank.forEach { gene ->
                if (gene !in visited && canMutate(currentGene, gene)) {
                    visited.add(gene)
                    geneToMutationsCount.addLast(gene to currentMutations + 1)
                }
            }
        }
        return -1
    }

    private fun canMutate(gene: String, mut: String): Boolean {
        var cnt = 0
        for (i in gene.indices) {
            if (gene[i] != mut[i]) {
                ++cnt
            }
        }
        return cnt == 1
    }

}