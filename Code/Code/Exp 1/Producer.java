import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Interprocess communication in Java using a memory-mapped file
 */
public class Producer {
    public static void main(String args[]) throws IOException, InterruptedException {
        RandomAccessFile rd = new RandomAccessFile("C:/Users/omgha/OneDrive/Desktop/2024/Distributed Computing/mapped.txt", "rw");
        FileChannel fc = rd.getChannel();
        MappedByteBuffer mem = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 10; i++) {
            mem.put((byte) i);
            System.out.println("Process 1: " + (byte) i);
            Thread.sleep(1); // time to allow CPU cache refreshed
        }

        // Close resources
        fc.close();
        rd.close();
    }
}