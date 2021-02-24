package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * soc
 * 2021/2/22 10:22
 * ElasticSearch测试连接
 *
 * @author lanhaifeng
 * @since
 **/
public class ElasticSearchTestProcessor extends AbstractDsTestProcessor {

	@Override
	public List<DBType> getSupportDbTypes() {
		return Arrays.asList(DBType.ELASTICSEARCH);
	}

	/**
	 * @param dsConnection
	 * @return
	 */
	@Override
	protected boolean validateNoAuth(DsConnection dsConnection) {
		return validate(dsConnection);
	}

	@Override
	protected boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth) {
		return validate(dsUsernamePasswordAuth);
	}

	private static boolean validate(DsConnection dsConnection) {
		RestHighLevelClient client = null;
		try {
			RestClientBuilder restClientBuilder = RestClient.builder(
					new HttpHost(dsConnection.getAddress(), dsConnection.getPort()));
			if(dsConnection instanceof DsUsernamePasswordAuth){
				String userName = ((DsUsernamePasswordAuth) dsConnection).getUserName();
				String password = ((DsUsernamePasswordAuth) dsConnection).getPassword();
				if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)){
					CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
					credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
					restClientBuilder.setHttpClientConfigCallback(httpClientBuilder -> {
						httpClientBuilder.disableAuthCaching();
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					});
				}
			}
			client = new RestHighLevelClient(restClientBuilder);

			return client.ping(RequestOptions.DEFAULT);
		} catch (Exception e) {
			logger.error("ElasticSearch测试连接错误：" + ExceptionUtils.getFullStackTrace(e));

			return false;
		}finally {
			if(Objects.nonNull(client)){
				try {
					client.close();
				} catch (IOException e) {
					logger.error("ElasticSearch关闭连接错误：" + ExceptionUtils.getFullStackTrace(e));
				}
			}
		}
	}
}
