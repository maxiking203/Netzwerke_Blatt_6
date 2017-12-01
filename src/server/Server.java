package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Date;

public class Server {
	
	private final static int N = 1000;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int packageC = 0;
		int failuers = 0;
		long starttime = new Date().getTime();
		try (DatagramSocket datagramSocket = new DatagramSocket(80);) {
			datagramSocket.setSoTimeout(1000);

			byte[] buffer = new byte[1400];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			for(int n = 1; n <= N; n++) {
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
		long endtime = new Date().getTime();
		long rate = (((1400*N)*8)/1000)/((endtime - starttime)/1000);
		System.out.println("Empfangsraterate: " + rate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		System.out.println("revieved: " + packageC);
		System.out.println("missed: " + failuers);
	}


}
