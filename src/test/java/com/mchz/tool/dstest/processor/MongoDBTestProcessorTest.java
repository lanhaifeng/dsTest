package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import org.junit.Assert;
import org.junit.Test;

public class MongoDBTestProcessorTest {

	private MongoDBTestProcessor mongoProcessor = new MongoDBTestProcessor();
	private DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();

	@Test
	public void validateNoAuth() {
		dsUsernamePasswordAuth.setAddress("192.168.202.2");
		dsUsernamePasswordAuth.setPort(27017);

		Assert.assertTrue("mongo测试服务失败", mongoProcessor.testService(dsUsernamePasswordAuth.getAddress(), dsUsernamePasswordAuth.getPort()));
		Assert.assertTrue("mongo测试连接失败", mongoProcessor.validateNoAuth(dsUsernamePasswordAuth));
	}

	@Test
	public void validateUsernamePasswordAuth() {
		dsUsernamePasswordAuth.setAddress("192.168.202.2");
		dsUsernamePasswordAuth.setPort(27017);
		dsUsernamePasswordAuth.setUserName("test");
		dsUsernamePasswordAuth.setPassword("test");
		dsUsernamePasswordAuth.setInstanceName("test");

		Assert.assertTrue("mongo测试连接失败", mongoProcessor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}
}