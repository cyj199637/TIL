package e.network;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOSample {
    public static void main(String[] args) {
        NIOSample sample = new NIOSample();
        sample.basicWriteAndRead();
    }

    public void basicWriteAndRead() {
        String fileName = "C:\\Users\\cyj19\\WebProgramming\\godofjava\\Chapter27\\nio.txt";
        try {
            writeFile(fileName, "My first NIO sample.");
            readFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String fileName, String data) throws Exception {
        FileChannel channel = new FileOutputStream(fileName).getChannel();
        byte[] byteData = data.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(byteData);
        channel.write(buffer);
        channel.close();
    }

    public void readFile(String fileName) throws Exception {
        FileChannel channel = new FileInputStream(fileName).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char)buffer.get());
        }
        channel.close();
    }
}