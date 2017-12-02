package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client {
	
	private final static int PORT = 80;
	private final static int N = 100;
	private final static byte[] BUFFER = new byte[1400];
	
	public static void main(String[] args) {
		InetAddress receiverAddress = null;
//		try {
//			receiverAddress = InetAddress.getByName("87.159.59.105");
//		} catch (UnknownHostException e1) {
//			System.out.println("Wrong Host!");
//			e1.printStackTrace();
//		}
//		
//		try(DatagramSocket datagramSocket = new DatagramSocket();) {
//
//		DatagramPacket packet = new DatagramPacket(
//		        BUFFER, BUFFER.length, receiverAddress, PORT);
//		int n = 1;
//		long starttime = new Date().getTime();
//		while (n <= N) {
//			datagramSocket.send(packet);
//			System.out.println("Sended package with the number:" + n);
//			
//			if(n % 10 == 0) {
//				Thread.sleep(500);
//				System.out.println("verzögert");
//			}
//			n++;
//		}
//		long endtime = new Date().getTime();
//		calculate(starttime, endtime);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try(Socket socket = new Socket(receiverAddress, PORT)) {
			receiverAddress = InetAddress.getByName("localhost");
			DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
			int n = 1;
			long starttime = new Date().getTime();
			while (n <= N) {
				dOut.writeInt(BUFFER.length);
				dOut.write(BUFFER); 
				System.out.println("Sended package with the number:" + n);
				
				if(n % 10 == 0) {
					Thread.sleep(500);
					System.out.println("verzögert");
				}
				n++;
			}
		} catch (IOException e) {
			System.out.println("Connection failed or lost");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				

	}
	
	private static void calculate(long start, long end) {
		long rate = (((1400*N)*8)/1000)/((end - start)/1000);
		System.out.println("Senderate: " + rate + " kbit/s");
	}
}
