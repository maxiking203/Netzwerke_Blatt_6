package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Client {
	
	private final static int PORT = 8080;
	private final static byte[] BUFFER = new byte[1400];
	private static InetAddress receiverAddress;
	private static long starttime;
	private static long endtime;
	
	public static void main(String[] args) {
		
		//Einlesen der ParameterUDP
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Use UDP or TCP?");
		String protocol = scanner.nextLine();
		System.out.println("insert IP of the Server:");
		String ip = scanner.nextLine();
		System.out.println("Enter Package amount:");
		int amount = Integer.parseInt(scanner.nextLine());
		System.out.println("insert n (Number of Package after which the client will sleep):");
		int n = Integer.parseInt(scanner.nextLine());
		System.out.println("insert k (time in ms which the client will slepp):");
		int k = Integer.parseInt(scanner.nextLine());
		/////
		
		/////SET HOST Ip/////
		try {
			receiverAddress = InetAddress.getByName(ip);
			System.out.println("SERVER IP SET");
		} catch (UnknownHostException e1) {
			System.out.println("Wrong Host!");
			e1.printStackTrace();
		}
		/////
		
		
		////UDP////
		if(protocol.equals("UDP") || protocol.equals("udp") || protocol.equals("UDP")) {
			System.out.println("//******UDP*****//");
			try(DatagramSocket datagramSocket = new DatagramSocket();) {
			DatagramPacket packet = new DatagramPacket(
			        BUFFER, BUFFER.length, receiverAddress, PORT);
			int i = 1;
			starttime = new Date().getTime();
			while (i <= amount) {
				datagramSocket.send(packet);
				System.out.println("Sended package with the number:" + i);
				
				if(i % n == 0) {
					Thread.sleep(k);
					System.out.println("verzögert");
				}
				i++;
			}
			endtime = new Date().getTime();
			calculate(starttime, endtime, amount);
			datagramSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("//*****UDP_END*****//");
		}
		
		
		/////TCP/////
		if(protocol.equals("TCP") || protocol.equals("tcp") || protocol.equals("Tcp")) {
			System.out.println("//******TCP*****//");
			try(Socket socket = new Socket(receiverAddress, PORT)) {
				DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
				int i = 1;
				starttime = new Date().getTime();
				while (i <= amount) {
					dOut.writeInt(BUFFER.length);
					dOut.write(BUFFER); 
					System.out.println("Sended package with the number:" + i);
					
					if(i % n == 0) {
						Thread.sleep(k);
						System.out.println("verzögert");
					}
					i++;
				}
				endtime = new Date().getTime();
				calculate(starttime, endtime, amount);
			} catch (IOException e) {
				System.out.println("Connection failed or lost");
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("//*****TCP_END*****//");
		}
				
				

	}
	
	private static void calculate(long start, long end , int amount) {
		float time = end - start;
		time = time/1000;
		float rate = (((1400*amount)*8)/1000)/time;
		System.out.println("Senderate: " + rate + " kbit/s");
	}
}
