public class NRU implements PageReplacementAlgorithm {
    public void execute(int instruction, Memory memory) {
        for (int i = 0; i < memory.RAM.length; i++) {
            if (memory.RAM[i].R == 0 && memory.RAM[i].M == 0) {
                replacePage(i, instruction, memory);
                return;
            }
        }
        replacePage(0, instruction, memory);
    }

    private void replacePage(int index, int instruction, Memory memory) {
        memory.RAM[index] = findPageInSwap(instruction, memory);
    }

    private Page findPageInSwap(int instruction, Memory memory) {
        for (Page page : memory.SWAP) {
            if (page.I == instruction) {
                return page;
            }
        }
        return null;
    }
}
