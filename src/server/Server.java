package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class Server {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try (DatagramSocket datagramSocket = new DatagramSocket(80);) {
			datagramSocket.setSoTimeout(5000);

			byte[] buffer = new byte[1400];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			int n = 0;
		while(n <= 1000) {
			try {
				n++;
			datagramSocket.receive(packet);
			System.out.println("Package erhalten");
			System.out.println("Sended package with the number:" + n);
			}
            catch (SocketTimeoutException e) {
                // timeout exception.
                System.out.println("Timeout reached!!! " + e);
            }
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}


}
