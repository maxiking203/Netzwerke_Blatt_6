package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	
	public static void main(String[] args) {
		try(DatagramSocket datagramSocket = new DatagramSocket();) {
			

		byte[] buffer = new byte[1400];
		InetAddress receiverAddress = InetAddress.getByName("10.179.15.132");

		DatagramPacket packet = new DatagramPacket(
		        buffer, buffer.length, receiverAddress, 80);
		int n = 0;
		while (n < 1000) {
			datagramSocket.send(packet);
			n++;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
