/**
 * @author nayeemkamal & Brian Duemmer
 */

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SuppressWarnings("unused")
public class UDPSender extends Thread
{
	private static final int BUF_SIZE = 1024;
	private static byte[] recvBuf = new byte[BUF_SIZE];
	
	private static final String addr = "localhost";
	private static final int port = 7888;
	private static InetAddress serverIp;
	private static DatagramSocket sock;
	private static Dataframe data;
	private static ErrorLog logger;
	
	public UDPSender()  {
		
		try {
			data = new Dataframe();
			data.run();
			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
			sock = new DatagramSocket();
			serverIp = InetAddress.getByName(addr);
			
		} catch (Exception e) {
	        logger = new ErrorLog();
	        logger.log(e);
		}
	}
	/**
	 * 
	 * 
	 * @param dat String to be sent over lora
	 * @throws IOException
	 */
	private static void sendData(String dat) throws IOException {
		DatagramPacket pack = new DatagramPacket(dat.getBytes(), dat.length(), serverIp, port);
		sock.send(pack);
	}


	@Override
	public void run() {
		try {
		UDPSender.sendData(data.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
