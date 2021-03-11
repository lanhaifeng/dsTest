package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import org.junit.Assert;
import org.junit.Test;

public class ElasticSearchTestProcessorTest {

	private ElasticSearchTestProcessor processor = new ElasticSearchTestProcessor();
	private DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();

	@Test
	public void validateNoAuth() {
		dsUsernamePasswordAuth.setAddress("192.168.40.12");
		dsUsernamePasswordAuth.setPort(9200);

		Assert.assertTrue("ElasticSearch测试连接失败", processor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));

	}

	@Test
	public void validateUsernamePasswordAuth() {
		dsUsernamePasswordAuth.setAddress("192.168.242.41");
		dsUsernamePasswordAuth.setPort(9200);
		dsUsernamePasswordAuth.setUserName("elastic");
		dsUsernamePasswordAuth.setPassword("pfiMylaEU0GDdT3acWFW");

		Assert.assertTrue("ElasticSearch测试连接失败", processor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}

	@Test
	public void validateUsernamePasswordAuth2() {
		dsUsernamePasswordAuth.setAddress("192.168.242.40");
		dsUsernamePasswordAuth.setPort(9900);
		dsUsernamePasswordAuth.setUserName("elastic");
		dsUsernamePasswordAuth.setPassword("pfiMylaEU0GDdT3acWFW");

		Assert.assertTrue("测试ElasticSearch服务失败", processor.testService(dsUsernamePasswordAuth.getAddress(), dsUsernamePasswordAuth.getPort()));
		Assert.assertTrue("ElasticSearch测试连接失败", processor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}
}