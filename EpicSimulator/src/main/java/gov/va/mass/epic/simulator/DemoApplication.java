package gov.va.mass.epic.simulator;

import gov.va.mass.epic.simulator.service.epicDemoClient;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
public class DemoApplication {
	@PostConstruct
	public void initSsl(){
//		System.setProperty("javax.net.ssl.keyStore", DemoApplication.class.getClassLoader().getResource("localhost_self-signed.p12").getFile());

		System.setProperty("javax.net.ssl.trustStore", "D:\\codeBase\\EpicSimulator\\localhost_self-signed.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "adapter");

		System.setProperty("javax.net.ssl.trustStoreType", "pkcs12");		/*
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
			(hostname,sslSession) -> {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			});*/
	}
	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		epicDemoClient demoClient = new epicDemoClient();
		demoClient.postToAdapter();
	}
}
