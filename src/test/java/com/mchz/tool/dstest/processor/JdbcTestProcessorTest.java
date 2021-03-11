package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.DsTestDelegate;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class JdbcTestProcessorTest {
	JdbcTestProcessor jdbcTestProcessor = new JdbcTestProcessor();
	private DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();

	@Test
	public void testOracle1() {
		auth.setAddress("192.168.240.227");
		auth.setPort(1521);
		auth.setUserName("c##sh");
		auth.setPassword("sh");
		auth.setInstanceName("ORCL");
		auth.setDbType(DBType.ORACLE.getDbTypeValue());

		Assert.assertTrue("测试oracle服务失败", jdbcTestProcessor.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试oracle连接失败", jdbcTestProcessor.validateUsernamePasswordAuth(auth));

	}

	@Test
	public void testOracle2() {
		auth.setAddress("192.168.202.13");
		auth.setPort(1521);
		auth.setInstanceName("ora9i");
		auth.setDbType(DBType.ORACLE.getDbTypeValue());

		auth.setUserName("system");
		auth.setPassword("oracle");

		Assert.assertTrue("测试oracle服务失败", jdbcTestProcessor.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试oracle连接失败", jdbcTestProcessor.validateUsernamePasswordAuth(auth));
	}

	@Test
	public void testKingbase1() {
		auth.setAddress("192.168.202.60");
		auth.setPort(54321);
		auth.setInstanceName("TEST");
		auth.setDbType(DBType.KINGBASE.getDbTypeValue());

		auth.setUserName("SYSTEM");
		auth.setPassword("system");

		Assert.assertTrue("测试Kingbase服务失败", jdbcTestProcessor.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试Kingbase连接失败", jdbcTestProcessor.validateUsernamePasswordAuth(auth));
	}

	@Test
	public void testMariadb() {
		auth.setAddress("192.168.202.128");
		auth.setPort(3306);
		auth.setDbType(DBType.MARIADB.getDbTypeValue());

		auth.setUserName("root");
		auth.setPassword("hzmcdba");
		auth.setInstanceName("test");

		Assert.assertTrue("测试Mariadb服务失败", jdbcTestProcessor.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试Mariadb连接失败", jdbcTestProcessor.validateUsernamePasswordAuth(auth));
	}

	@Test
	@Ignore
	public void testGbaseConnection() {
		auth.setAddress("192.168.238.214");
		auth.setPort(5258);
		auth.setInstanceName("ylhzmc");
		auth.setDbType(DBType.GBASE.getDbTypeValue());

		auth.setUserName("sysdba");
		auth.setPassword("gbase");

		Assert.assertTrue("测试gbase服务失败", jdbcTestProcessor.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试gbase连接失败", jdbcTestProcessor.validateUsernamePasswordAuth(auth));
	}
}