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
	
	private final static int PORT = 8080;
	private final static int N = 2055;
	private final static byte[] BUFFER = new byte[1400];
	private static InetAddress receiverAddress;
	private static long starttime;
	private static long endtime;
	
	public static void main(String[] args) {
		
		
		try {
			receiverAddress = InetAddress.getByName("192.168.2.179");
			System.out.println("SERVER IP SET");
		} catch (UnknownHostException e1) {
			System.out.println("Wrong Host!");
			e1.printStackTrace();
		}
		
		System.out.println("//******UDP*****//");
		try(DatagramSocket datagramSocket = new DatagramSocket();) {
		DatagramPacket packet = new DatagramPacket(
		        BUFFER, BUFFER.length, receiverAddress, PORT);
		int n = 1;
		starttime = new Date().getTime();
		while (n <= N) {
			datagramSocket.send(packet);
			System.out.println("Sended package with the number:" + n);
			
			if(n % 150 == 0) {
				Thread.sleep(100);
				System.out.println("verzögert");
			}
			n++;
		}
		endtime = new Date().getTime();
		calculate(starttime, endtime);
		datagramSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("//*****UDP_END*****//");
		
		System.out.println("//******TCP*****//");
		try(Socket socket = new Socket(receiverAddress, PORT)) {
			DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
			int n = 1;
			starttime = new Date().getTime();
			while (n <= N) {
				dOut.writeInt(BUFFER.length);
				dOut.write(BUFFER); 
				System.out.println("Sended package with the number:" + n);
				
				if(n % 200 == 0) {
					Thread.sleep(1000);
					System.out.println("verzögert");
				}
				n++;
			}
			endtime = new Date().getTime();
			calculate(starttime, endtime);
		} catch (IOException e) {
			System.out.println("Connection failed or lost");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("//*****TCP_END*****//");
				
				

	}
	
	private static void calculate(long start, long end) {
		long rate = (((1400*N)*8)/1000)/((end - start)/1000);
		System.out.println("Senderate: " + rate + " kbit/s");
	}
}
