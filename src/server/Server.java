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
			datagramSocket.setSoTimeout(1000);

			byte[] buffer = new byte[1400];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			int packageC = 1;
			for(int n = 1; n <= 30000; n++) {
			try {
			datagramSocket.receive(packet);
			System.out.println("got package:" + packageC);
			packageC++;
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
