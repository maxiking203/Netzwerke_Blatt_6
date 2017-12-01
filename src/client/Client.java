package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Client {
	
	private final static int N = 100;

	
	public static void main(String[] args) {
		try(DatagramSocket datagramSocket = new DatagramSocket();) {
			

		byte[] buffer = new byte[1400];
		InetAddress receiverAddress = InetAddress.getByName("87.159.59.105");

		DatagramPacket packet = new DatagramPacket(
		        buffer, buffer.length, receiverAddress, 80);
		int n = 1;
		long starttime = new Date().getTime();
		while (n <= N) {
			datagramSocket.send(packet);
			System.out.println("Sended package with the number:" + n);
			
			if(n % 10 == 0) {
				Thread.sleep(500);
				System.out.println("verzögert");
			}
			n++;
		}
		long endtime = new Date().getTime();
		long rate = (((1400*N)*8)/1000)/((endtime - starttime)/1000);
		System.out.println("Senderate: " + rate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
