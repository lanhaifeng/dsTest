package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import org.junit.Assert;
import org.junit.Test;

public class RedisTestProcessorTest {

	@Test
	public void validateUsernamePasswordAuth() {
		RedisTestProcessor redisTestProcessor = new RedisTestProcessor();
		DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();
		dsUsernamePasswordAuth.setAddress("192.168.230.157");
		dsUsernamePasswordAuth.setPort(18379);
		dsUsernamePasswordAuth.setPassword("Zu5mIJN5AjWHpFv3jAiCPBOJzKnq0baiZ");

		Assert.assertTrue("redis测试连接失败", redisTestProcessor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}

	@Test
	public void validateNoAuth() {
		RedisTestProcessor redisTestProcessor = new RedisTestProcessor();
		DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();
		dsUsernamePasswordAuth.setAddress("192.168.40.12");
		dsUsernamePasswordAuth.setPort(16379);

		Assert.assertTrue("redis测试连接失败", redisTestProcessor.validateNoAuth(dsUsernamePasswordAuth));
	}
}