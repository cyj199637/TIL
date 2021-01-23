package e.network;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketClientSample {
    public static void main(String[] args) {
        SocketClientSample sample = new SocketClientSample();
        // sample.sendSocketSample();
        sample.sendAndReceiveSocketData();
    }

    public void sendSocketSample() {
        for (int loop=0; loop<3; loop++) {
            sendSocketData("I liked java at "+new Date());
        }
        sendSocketData("EXIT");
    }

    public void sendSocketData(String data) {
        Socket socket = null;
        try {
            System.out.println("Client:Connecting");
            socket = new Socket("127.0.0.1", 9999);
            System.out.println("Client:Connect status = "+socket.isConnected());
            Thread.sleep(1000);
            OutputStream stream = socket.getOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(stream);
            byte[] bytes = data.getBytes();
            out.write(bytes);
            System.out.println("Client:Sent data");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendAndReceiveSocketData() {
        Socket socket=null;
		try {
			System.out.println("Client:Connecting");
			socket=new Socket("127.0.0.1",9999); // 1)
			System.out.println("Client:Connect status="+socket.isConnected());
			Thread.sleep(1000);
			byte[] readByte=new byte[256];
			InputStream stream=socket.getInputStream();
			BufferedInputStream in= new BufferedInputStream(stream);
			in.read(readByte);
			System.out.println("Client:received data="+new String(readByte).trim());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(socket!=null) {
				try {
					socket.close(); // 3)
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }
}