package e.network;

import java.nio.IntBuffer;

public class NIODetailSample {
    public static void main(String[] args) {
        NIODetailSample sample = new NIODetailSample();
        // sample.checkBuffer();
        sample.checkBufferStatus();
    }

    public void checkBuffer() {
        try {
            IntBuffer buffer = IntBuffer.allocate(1024);
            for (int loop=0; loop<100; loop++) {
                buffer.put(loop);
            }
            System.out.println("Buffer capacity = "+buffer.capacity());
            System.out.println("Buffer limit = "+buffer.limit());
            System.out.println("Buffer position = "+buffer.position());
            buffer.flip();
            System.out.println("Buffer flipped!!!");
            System.out.println("Buffer limit = "+buffer.limit());
            System.out.println("Buffer position = "+buffer.position());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printBufferStatus(String job, IntBuffer buffer) {
        System.out.println("Buffer "+job+" !!!");
        System.out.format("Buffer position = %d remaining %d limit = %d\n"
        , buffer.position(), buffer.remaining(), buffer.limit());
    }

    public void checkBufferStatus() {
        try {
            IntBuffer buffer = IntBuffer.allocate(1024);
            buffer.get();
            printBufferStatus("get", buffer);
            buffer.mark();
            printBufferStatus("mark", buffer);
            buffer.get();
            printBufferStatus("get", buffer);
            buffer.reset();
            printBufferStatus("reset", buffer);
            buffer.rewind();
            printBufferStatus("rewind", buffer);
            buffer.clear();
            printBufferStatus("clear", buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}