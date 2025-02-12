import java.util.Random;

public class SimuladorPaginacao {
    public static void main(String[] args) {
        Memory memory = new Memory(10, 100);
        PageReplacementAlgorithm[] algorithms = {new NRU(), new FIFO(), new FIFO_SC(), new LRU(), new WSClock()};

        for (PageReplacementAlgorithm algorithm : algorithms) {
            String initialFilename = algorithm.getClass().getSimpleName() + "_matriz_inicial.txt";
            String finalFilename = algorithm.getClass().getSimpleName() + "_matriz_final.txt";

            memory.saveMatricesToFile(initialFilename);

            for (int i = 0; i < 1000; i++) {
                int instruction = new Random().nextInt(100) + 1;
                algorithm.execute(instruction, memory);

                if (i % 10 == 0) {
                    memory.resetAccessBits();
                }
            }

            memory.saveMatricesToFile(finalFilename);
        }
    }
}
