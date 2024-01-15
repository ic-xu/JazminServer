/**
 * 
 */
package jazmin.test.server.message;

import jazmin.server.msg.client.MessageClient;

/**
 * @author yama
 *
 */
public class MessageServerClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		MessageClient mc=new MessageClient();
		mc.connect("localhost",3001);

		int i=0;
		String[] a={"a","b"};
		while (true){
			mc.send(++i,"a",a);

			Thread.sleep(1000);
		}
	}

}
