package communicator.entity.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import communicator.entity.exception.RequestStatusException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Morozov Oleksandr on 02.12.2016.
 * 414D
 */
@Slf4j
public class HttpClient <A> {

	final Class<A> typeParameterClass;

	private HttpClient(Class<A> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	public static <A> HttpClient<A> createHttpClient(Class<A> typeParameterClass) {
		return new HttpClient<A>(typeParameterClass);
	}

	public A sendPost(String url, Object o) throws IOException, RequestStatusException {

		try (CloseableHttpClient httpclient = HttpClientBuilder.create().setSSLHostnameVerifier(new NoopHostnameVerifier()).build()) {

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(o);
            log.info(json);

			HttpPost post = new HttpPost(url);
			log.info(url);
			post.setHeader("Content-Type", "application/json");
			StringEntity input = new StringEntity(json, "UTF-8");
			post.setEntity(input);
			try (CloseableHttpResponse response = httpclient.execute(post)) {

				HttpEntity ht = response.getEntity();

				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RequestStatusException( "Failed : HTTP error code : "+ response.getStatusLine().getStatusCode());
				}

				BufferedHttpEntity buf = new BufferedHttpEntity(ht);
				String responseContent = EntityUtils.toString(buf, StandardCharsets.UTF_8);
				log.info("response="+responseContent);
				A out = mapper.readValue(responseContent, typeParameterClass );


				return out;

				//	private final Class<BundleText> annotationClass = BundleText.class;

//				System.out.println("*********************");
//					System.out.println(responseContent);
//
//
//				if (response.getStatusLine().getStatusCode() != 200) {
//					throw new RuntimeException("Failed : HTTP error code : "
//							+ response.getStatusLine().getStatusCode());
//				}else {
//					BufferedReader br = new BufferedReader(
//							new InputStreamReader((response.getEntity().getContent())));
//
//					String output;
//					System.out.println("Output from Server .... \n");
//					while ((output = br.readLine()) != null) {
//						System.out.println(output);
//					}


				//	}


			}
		}

	}

}