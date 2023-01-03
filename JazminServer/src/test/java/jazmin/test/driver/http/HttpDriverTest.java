/**
 * 
 */
package jazmin.test.driver.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import jazmin.core.Jazmin;
import jazmin.driver.http.HttpClientDriver;
import jazmin.server.console.ConsoleServer;

/**
 * @author yama
 * 11 Feb, 2015
 */
public class HttpDriverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClientDriver hd=new HttpClientDriver();
		hd.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		Jazmin.addDriver(hd);
		ConsoleServer cs=new ConsoleServer();
		cs.setPort(2222);
		Jazmin.addServer(cs);
		Jazmin.start();
		//
		for(int i=0;i<10;i++){
			//hd.get("http://www.163.com").execute((rsp,e)->{
				
			hd.post("http://vaadin.com/download/release/7.3/7.3.8/vaadin-all-7.3.8.zip").execute((rsp,e)->{
				System.out.println("done");
			});
		}
	}

}
