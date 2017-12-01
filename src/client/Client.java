package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	
	public static void main(String[] args) {
		try(DatagramSocket datagramSocket = new DatagramSocket();) {
			

		byte[] buffer = new byte[1400];
		InetAddress receiverAddress = InetAddress.getByName("192.168.2.179");

		DatagramPacket packet = new DatagramPacket(
		        buffer, buffer.length, receiverAddress, 80);
		int n = 1;
		while (n <= 10000) {
			datagramSocket.send(packet);
			System.out.println("Sended package with the number:" + n);
			
			if(n % 10 == 0) {
				Thread.sleep(100);
			}
			n++;
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
