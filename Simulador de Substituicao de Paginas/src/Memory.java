import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Memory {
    Page[] RAM;
    Page[] SWAP;
    Random random = new Random();

    public Memory(int ramSize, int swapSize) {
        RAM = new Page[ramSize];
        SWAP = new Page[swapSize];
        initializeSwap();
        loadInitialRAM();
    }

    private void initializeSwap() {
        for (int i = 0; i < SWAP.length; i++) {
            SWAP[i] = new Page(i, i + 1, random.nextInt(50) + 1, 0, 0, random.nextInt(9900) + 100);
        }
    }

    private void loadInitialRAM() {
        boolean[] used = new boolean[SWAP.length];

        for (int i = 0; i < RAM.length; i++) {
            int index;
            do {
                index = random.nextInt(SWAP.length);
            } while (used[index]);
            used[index] = true;
            RAM[i] = SWAP[index];
        }
    }

    public void resetAccessBits() {
        for (Page page : RAM) {
            page.R = 0;
        }
    }

    public void saveMatricesToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("=== MATRIZ RAM (10x6) ===\n");
            writer.write("N\tI\tD\tR\tM\tT\n");

            for (Page page : RAM) {
                writer.write(String.format("%d\t%d\t%d\t%d\t%d\t%d\n",
                        page.N, page.I, page.D, page.R, page.M, page.T));
            }

            writer.write("\n=== MATRIZ SWAP (100x6) ===\n");
            writer.write("N\tI\tD\tR\tM\tT\n");

            for (Page page : SWAP) {
                writer.write(String.format("%d\t%d\t%d\t%d\t%d\t%d\n",
                        page.N, page.I, page.D, page.R, page.M, page.T));
            }

            System.out.println("Matrizes salvas em " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as matrizes no arquivo: " + e.getMessage());
        }
    }
}
