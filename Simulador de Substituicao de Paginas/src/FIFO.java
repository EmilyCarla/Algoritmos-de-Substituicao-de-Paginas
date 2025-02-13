import java.util.*;

public class FIFO implements PageReplacementAlgorithm {
    Queue<Integer> queue = new LinkedList<>();

    public FIFO() {
    }

    public void execute(int instruction, Memory memory) {
        if (queue.isEmpty()) {
            for (int i = 0; i < memory.RAM.length; i++) {
                queue.add(i);
            }
        }

        Integer index = queue.poll();
        if (index != null) {
            replacePage(index, instruction, memory);
            queue.add(index); // Reinsere na fila
        }
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
