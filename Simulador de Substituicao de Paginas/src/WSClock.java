import java.util.Random;

public class WSClock implements PageReplacementAlgorithm {
    Random random = new Random();

    public void execute(int instruction, Memory memory) {
        int EP = random.nextInt(9900) + 100;
        for (Page page : memory.RAM) {
            if (page.T >= EP) {
                replacePage(page, instruction, memory);
                return;
            }
        }
        replacePage(memory.RAM[0], instruction, memory);
    }

    private void replacePage(Page page, int instruction, Memory memory) {
        memory.RAM[0] = findPageInSwap(instruction, memory);
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
