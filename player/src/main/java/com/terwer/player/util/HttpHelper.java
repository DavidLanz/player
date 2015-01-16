package com.terwer.player.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 * Http������ ������httpclient
 * 
 * @author Tangyouwei
 *
 */
public class HttpHelper {

	/**
	 * ����ַ
	 */
	private static String baseUrl;

	/**
	 * CloseableHttpClient
	 */
	private static CloseableHttpClient httpClient = null;
	/**
	 * RequestConfig
	 */
	private static RequestConfig requestConfig = null;
	/**
	 * HttpClientContext
	 */
	private static HttpClientContext httpClientContext = null;

	/**
	 * @return the baseUrl
	 */
	public static String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public static void setBaseUrl(String baseUrl) {
		System.out.println("��ʼ����baseUrl...");
		HttpHelper.baseUrl = baseUrl;
	}

	public static void setHttpclient(CloseableHttpClient httpClient) {
		System.out.println("��ʼ����httpClient...");
		HttpHelper.httpClient = httpClient;
	}



	public static void setRequestConfig(RequestConfig requestConfig) {
		System.out.println("��ʼ����requestConfig...");
		HttpHelper.requestConfig = requestConfig;
	}

	public static void setHttpClientContext(HttpClientContext httpClientContext) {
		System.out.println("��ʼ����httpClientContext...");
		HttpHelper.httpClientContext = httpClientContext;
	}

	/**
	 * ��̬����飬��ʼ��Ĭ�ϵ�http����
	 */
	static {
		System.out
				.println("��ʼ���þ�̬�����,��ʼ��Ĭ�ϵ�httpclient��requestconfig��httpClientContext����...");
		// Create an HttpClient with the given custom dependencies and
		// configuration.
		setHttpclient(HttpClients.custom()
		// .setConnectionManager(connManager)
		// .setDefaultCookieStore(cookieStore)
		// .setDefaultCredentialsProvider(credentialsProvider)
		// .setProxy(new HttpHost("myproxy", 8080))
		// .setDefaultRequestConfig(defaultRequestConfig)
				.build());
		setRequestConfig(RequestConfig.custom().setSocketTimeout(5000)
				.setConnectTimeout(5000).setConnectionRequestTimeout(5000)
				// .setProxy(new HttpHost("myotherproxy", 8080))
				.build());

		setHttpClientContext(HttpClientContext.create());
		// Contextual attributes set the local httpClientContext level will take
		// precedence over those set at the client level.
		// httpClientContext.setCookieStore(cookieStore);
		// httpClientContext.setCredentialsProvider(credentialsProvider);
		System.out
				.println("httpclient��requestconfig��httpClientContext��ʼ�����...");
	}

	/**
	 * ��ȡCookie
	 * 
	 * @return
	 */
	public static CookieStore getCookie() {
		return null;
	}

	/**
	 * ��ȡ��Ӧ���� 15-01-16 By ����� (jdk 1.7.0_71)
	 * @param requestBase ���Դ���HttpGet��HttpPost��HttpPut��HttpDelete����
	 * @param queryString ��ѯ����
	 * @param postData post���ݣ�������HttpPost����ʱ����Ҫ��
	 * @return ��Ӧ���
	 */
	public static String getContent(HttpRequestBase requestBase,
			String queryString, String postData) {
		String content=getContext(requestBase,queryString,postData).toString();
		return content;
	}

	
	/**
	 * ��ȡ��Ӧ���� 15-01-16 By ����� (jdk 1.7.0_71)
	 * @param requestBase ���Դ���HttpGet��HttpPost��HttpPut��HttpDelete����
	 * @param queryString ��ѯ����
	 * @param postData post���ݣ�������HttpPost����ʱ����Ҫ��
	 * @return HttpClientContext
	 */
	public static HttpClientContext getContext(HttpRequestBase requestBase,
			String queryString, String postData) {
		if (null == baseUrl) {
			System.out.println("http����ַ��δ���ã���ʹ��HttpHelper.setBaseUrl(\"xxx\")���ã�");
			return null;
		}
		CloseableHttpResponse response = null;
		try {
			//��������
			final URI uri = new URI(baseUrl);
			requestBase.setURI(uri);
			requestBase.setConfig(requestConfig);
			System.out.println("��ǰ���󷽷���" + requestBase.getMethod());
			System.out.println("��ʼִ������,��ǰ�����ַ��" + requestBase.getURI());
			
			//��������
			response = httpClient.execute(requestBase, httpClientContext);
			System.out.println("��ʼ������Ӧ...");

			//������Ӧ
			HttpEntity entity = response.getEntity();
			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: "
						+ entity.getContentLength());
			}
			System.out.println("----------------------------------------");

			// Once the request has been executed the local httpClientContext
			// can
			// be used to examine updated state and various objects affected
			// by the request execution.

			// Last executed request
			//httpClientContext.getRequest().getAllHeaders();
			// Execution route
			//httpClientContext.getHttpRoute();
			// Target auth state
			//httpClientContext.getTargetAuthState();
			// Proxy auth state
			//httpClientContext.getTargetAuthState();
			// Cookie origin
			//httpClientContext.getCookieOrigin();
			// Cookie spec used
			//httpClientContext.getCookieSpec();
			// User security token
			//httpClientContext.getUserToken();
			 return httpClientContext;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		finally {
			try {
				httpClient.close();
				response.close();
				System.out.println("������ɣ������ѹرա�");
			} catch (IOException e) {
				System.out.println("�ر������쳣��"+e.getMessage());
			}
			
		}
	}
	
	

}
