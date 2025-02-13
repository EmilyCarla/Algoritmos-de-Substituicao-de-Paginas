import java.util.LinkedList;
import java.util.Queue;

public class FIFO_SC implements PageReplacementAlgorithm {
    Queue<Page> queue = new LinkedList<>();

    public void execute(int instruction, Memory memory) {
        while (!queue.isEmpty()) {
            Page page = queue.poll();
            if (page.R == 0) {
                replacePage(page, instruction, memory);
                return;
            } else {
                page.R = 0;
                queue.add(page);
            }
        }
        replacePage(queue.poll(), instruction, memory);
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
