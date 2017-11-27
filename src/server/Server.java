package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try (DatagramSocket datagramSocket = new DatagramSocket(80);) {


		byte[] buffer = new byte[10];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			datagramSocket.receive(packet);
			System.out.println("Package erhalten");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}


}
