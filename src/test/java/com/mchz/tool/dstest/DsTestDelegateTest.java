package com.mchz.tool.dstest;

import com.mchz.mcdatasource.core.DatasourceConstant;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DsTestDelegateTest {

	@Before
	public void setUp() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");
	}

	@Test
	@Ignore
	public void testService() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();
		Boolean result = dsTestDelegate.testService("192.168.239.141", 8629);
		Assert.assertTrue("测试服务失败", result);
	}

	@Test
	@Ignore
	public void testMysqlConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();
		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.230.156");
		auth.setPort(13306);
		auth.setInstanceName("soc");
		auth.setDbType(DBType.MYSQL.getDbTypeValue());

		auth.setUserName("socusr");
		auth.setPassword("hzmc321#");

		Assert.assertTrue("测试mysql服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试mysql连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testKdbConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();
		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.239.141");
		auth.setPort(8629);
		auth.setInstanceName("soc");
		auth.setDbType(DBType.GBASE.getDbTypeValue());

		auth.setUserName("sys");
		auth.setPassword("kdb");

		Assert.assertTrue("测试kdb服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试kdb连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testGbaseConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.238.214");
		auth.setPort(5258);
		auth.setInstanceName("ylhzmc");
		auth.setDbType(DBType.GBASE.getDbTypeValue());

		auth.setUserName("sysdba");
		auth.setPassword("gbase");

		Assert.assertTrue("测试gbase服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试gbase连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

	@Test
	@Ignore
	public void testOracleConnection() {
		DsTestDelegate dsTestDelegate = new DsTestDelegate();

		DsUsernamePasswordAuth auth = new DsUsernamePasswordAuth();
		auth.setAddress("192.168.202.13");
		auth.setPort(1521);
		auth.setInstanceName("ora9i");
		auth.setDbType(DBType.ORACLE.getDbTypeValue());

		auth.setUserName("system");
		auth.setPassword("oracle");

		Assert.assertTrue("测试oracle服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试oracle连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}
}