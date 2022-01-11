package Controlador;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class httpCertifi {
	
		public static void validCert() {
			
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				
				public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
				
		        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
		        
		        public void checkServerTrusted(X509Certificate[] certs, String authType) { }

			} };

		        SSLContext sc = null;
		        
		try {
			
			sc = SSLContext.getInstance("SSL");
			
		} catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
			
			e1.printStackTrace();
			
		}
		        
		try {
		        	
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		
		} catch (KeyManagementException e) {
			
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		}
		        
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		HostnameVerifier allHostsValid = new HostnameVerifier() {
			
			public boolean verify(String hostname, SSLSession session) { return true; }
			
		};
		       
		        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		}
	
}
