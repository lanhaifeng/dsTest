package com.mchz.tool.dstest;

import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class DsTestDelegateTest {


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

		auth.setUserName("gbasedbt");
		auth.setPassword("gbasedbt");

		Assert.assertTrue("测试gbase服务失败", dsTestDelegate.testService(auth.getAddress(), auth.getPort()));
		Assert.assertTrue("测试gbase连接失败", dsTestDelegate.testConnection(auth.getDbTypeDict(), auth));
	}

}