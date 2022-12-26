/**
 * 
 */
package jazmin.test.driver.http;

import jazmin.driver.http.HttpClientDriver;

/**
 * @author yama
 *
 */
public class NingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		HttpClientDriver d=new HttpClientDriver();
		d.setAcceptAnyCertificate(true);
		d.init();
		d.start();
		String s=d.get("https://127.0.0.1:8443/").execute().get().getResponseBody();
		System.out.println(s);
	}

}
