import java.util.*;

public class LRU implements PageReplacementAlgorithm {
    private LinkedHashMap<Integer, Integer> pageMap = new LinkedHashMap<>(10, 0.75f, true); // Mantém a ordem de acesso

    public void execute(int instruction, Memory memory) {
        Integer pageIndex = findPageIndex(instruction, memory);

        if (pageIndex != null) {
            memory.RAM[pageIndex].R = 1;
        } else {
            if (pageMap.isEmpty()) {
                for (int i = 0; i < memory.RAM.length; i++) {
                    pageMap.put(memory.RAM[i].N, i);
                }
            }

            Integer lruPage = pageMap.keySet().iterator().next();
            pageIndex = pageMap.remove(lruPage);

            replacePage(pageIndex, instruction, memory);

            pageMap.put(memory.RAM[pageIndex].N, pageIndex);
        }
    }

    private Integer findPageIndex(int instruction, Memory memory) {
        for (int i = 0; i < memory.RAM.length; i++) {
            if (memory.RAM[i].I == instruction) {
                return i;
            }
        }
        return null;
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
