package e.network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerSample {
    public static void main(String[] args) {
        SocketServerSample sample = new SocketServerSample();
        // sample.startServer();
        sample.startReplyServer();
    }

    public void startServer() {
        ServerSocket server = null;
        Socket client = null;

        try {
            server = new ServerSocket(9999);
            while (true) {
                System.out.println("Server:Waiting for request.");
                client = server.accept();
                System.out.println("Server:Accepted.");
                InputStream stream = client.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                String data = null;
                StringBuilder receivedData = new StringBuilder();
                while ((data = in.readLine()) != null) {
                    receivedData.append(data);
                }
                System.out.println("Received data: "+receivedData);
                in.close();
                stream.close();
                client.close();
                if (receivedData != null && "EXIT".equals(receivedData.toString())) {
                    System.out.println("Stop SocketServer");
                    break;
                }
                System.out.println("----------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startReplyServer() {
        ServerSocket server=null;
		Socket client=null;
		try {
			server=new ServerSocket(9999);
            System.out.println("Server:Waiting for request.");
            client=server.accept();
            System.out.println("Server:Accepted.");
            OutputStream stream=client.getOutputStream();

            BufferedOutputStream out=new BufferedOutputStream(stream);
            out.write("OK".getBytes());
            out.close();
            stream.close();
            client.close();
            System.out.println("----------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(server!=null) {
				try {
					server.close(); // 5)
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }
}