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
		int packageC = 0;
		int failuers = 0;
		
		try (DatagramSocket datagramSocket = new DatagramSocket(80);) {
			datagramSocket.setSoTimeout(1000);

			byte[] buffer = new byte[1400];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			for(int n = 1; n <= 10000; n++) {
			try {
			datagramSocket.receive(packet);
			packageC++;
			System.out.println("got package:" + packageC);
			}
            catch (SocketTimeoutException e) {
                // timeout exception.
                System.out.println("Timeout reached!!! " + e);
                failuers++;
                
            }
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		System.out.println("revieved: " + packageC);
		System.out.println("missed: " + failuers);
	}


}
