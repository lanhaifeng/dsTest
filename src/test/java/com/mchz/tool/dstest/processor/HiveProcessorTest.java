package com.mchz.tool.dstest.processor;

import com.mchz.mcdatasource.core.DatasourceConstant;
import com.mchz.tool.dstest.domain.auth.DsKerberosAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HiveProcessorTest {

	private HiveProcessor hiveProcessor = new HiveProcessor();
	private DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();
	private DsKerberosAuth dsKerberosAuth = new DsKerberosAuth();

	@Before
	public void setUp() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");
	}

	@Test
	public void validateUsernamePasswordAuth() {
		dsUsernamePasswordAuth.setAddress("192.168.239.1");
		dsUsernamePasswordAuth.setPort(10000);
		dsUsernamePasswordAuth.setUserName("hive");
//		dsUsernamePasswordAuth.setInstanceName("chail");
		dsUsernamePasswordAuth.setDbType(DBType.HIVE.getDbTypeValue());

		Assert.assertTrue("hive测试连接失败", hiveProcessor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}

	@Test
	public void validateKerberosAuth() {
		dsKerberosAuth.setAddress("192.168.51.84");
		dsKerberosAuth.setPort(21066);
		dsKerberosAuth.setUserName("chail@HADOOP.COM");
//		dsKerberosAuth.setInstanceName("default");
		dsKerberosAuth.setDbType(DBType.HIVE.getDbTypeValue());

		dsKerberosAuth.setPrincipal("hive/hadoop.hadoop.com@HADOOP.COM");
		dsKerberosAuth.setClientKeyTabFile(HiveProcessorTest.class.getClassLoader().getResource("hive/user.keytab").getFile());
		dsKerberosAuth.setConfigFile(HiveProcessorTest.class.getClassLoader().getResource("hive/krb5.conf").getFile());

		Assert.assertTrue("hive测试连接失败", hiveProcessor.validateKerberosAuth(dsKerberosAuth));
	}

	@Test
	public void validateKerberosAuth2() {
		dsKerberosAuth.setAddress("192.168.200.167");
		dsKerberosAuth.setPort(10000);
		dsKerberosAuth.setUserName("hive/cdh01@CDH167.COM");
		dsKerberosAuth.setPassword("hive");
		dsKerberosAuth.setInstanceName("ycc");
		//要使用DataBaseType.HIVE的插件驱动
		dsKerberosAuth.setDbType(DBType.HIVE.getDbTypeValue());

		dsKerberosAuth.setPrincipal("hive/cdh01@CDH167.COM");
		dsKerberosAuth.setClientKeyTabFile(HiveProcessorTest.class.getClassLoader().getResource("hive/cdh5_user.keytab").getFile());
		dsKerberosAuth.setConfigFile(HiveProcessorTest.class.getClassLoader().getResource("hive/cdh5_krb5.conf").getFile());

		Assert.assertTrue("hive测试连接失败", hiveProcessor.validateKerberosAuth(dsKerberosAuth));
	}
}