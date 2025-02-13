public class Clock implements PageReplacementAlgorithm {
    private int hand = 0; // Ponteiro que percorre as páginas

    public void execute(int instruction, Memory memory) {
        while (true) {
            Page page = memory.RAM[hand]; // Pega a página apontada pelo ponteiro

            if (page.R == 0) { // Se o bit de referência for 0, substituímos a página
                replacePage(page, instruction, memory);
                break; // Página substituída, sai do loop
            } else {
                // Se o bit de referência for 1, resetamos o bit e avançamos o ponteiro
                page.R = 0;
                hand = (hand + 1) % memory.RAM.length; // Avança o ponteiro circularmente
            }
        }
    }

    private void replacePage(Page page, int instruction, Memory memory) {
        memory.RAM[hand] = findPageInSwap(instruction, memory); // Substitui a página na RAM
    }

    private Page findPageInSwap(int instruction, Memory memory) {
        for (Page page : memory.SWAP) {
            if (page.I == instruction) {
                return page; // Retorna a página da SWAP que corresponde à instrução
            }
        }
        return null; // Se não encontrar, retorna null
    }
}
