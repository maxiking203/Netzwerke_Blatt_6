package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Date;

public class Server {
	
	private final static int PORT = 8080;
	private final static int N = 2055;
	private final static byte[] BUFFER = new byte[1400];
	private static long starttime;
	private static long endtime;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("//******UDP*****//");
		int packageC = 0;
		int failuers = 0;
		try (DatagramSocket datagramSocket = new DatagramSocket(PORT);) {
			datagramSocket.setSoTimeout(5000);
			DatagramPacket packet = new DatagramPacket(BUFFER, BUFFER.length);
			starttime = new Date().getTime();
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
		calculate(starttime, endtime);
		datagramSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		System.out.println("revieved: " + packageC);
		System.out.println("missed: " + failuers);
		
		System.out.println("//*****UDP_END*****//");
		
		////TCP////
		
		System.out.println("//******TCP*****//");
		
		try(ServerSocket server = new ServerSocket(PORT)) {
			Socket socket = server.accept();
////			socket.connect(socket.getRemoteSocketAddress(), 1000);
			DataInputStream dIn = new DataInputStream(socket.getInputStream());
			starttime = new Date().getTime();
			for(int n = 1; n <= N; n++) {
				try {
				int length = dIn.readInt();                    // read length of incoming message
				if(length>0) {
				    dIn.readFully(BUFFER, 0, BUFFER.length); // read the message
				    System.out.println("got Package " + n);
				}
				}
				catch(SocketTimeoutException s) {
	                System.out.println("Timeout reached!!! " + s);
				}
			}
			endtime = new Date().getTime();
			calculate(starttime, endtime);
			System.out.println("Connection closed");
			socket.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("//*****TCP_END*****//");
	}
	
	private static void calculate(long start, long end) {
		long rate = (((1400*N)*8)/1000)/((end - start)/1000);
		System.out.println("Empfangsrate: " + rate + " kbit/s");
	}


}
