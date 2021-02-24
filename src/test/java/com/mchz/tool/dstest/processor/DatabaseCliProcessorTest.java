package com.mchz.tool.dstest.processor;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.file.FileReader;
import com.mchz.datasource.cli.DatasourceDatabaseCli;
import com.mchz.mcdatasource.core.DataBaseType;
import com.mchz.mcdatasource.core.DatasourceConstant;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class DatabaseCliProcessorTest {

	private DatabaseCliProcessor databaseCliProcessor = new DatabaseCliProcessor();
	private DsUsernamePasswordAuth dsUsernamePasswordAuth = new DsUsernamePasswordAuth();

	@Before
	public void setUp() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");
	}

	@Test
	public void testMysql() {
		dsUsernamePasswordAuth.setAddress("192.168.230.156");
		dsUsernamePasswordAuth.setPort(13306);
		dsUsernamePasswordAuth.setUserName("socusr");
		dsUsernamePasswordAuth.setPassword("hzmc321#");
		dsUsernamePasswordAuth.setInstanceName("soc");
		dsUsernamePasswordAuth.setDbType(DBType.MYSQL.getDbTypeValue());

		Assert.assertTrue("测试mysql连接失败", databaseCliProcessor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}

	@Test
	public void testOracle() throws Exception {
		dsUsernamePasswordAuth.setAddress("192.168.202.13");
		dsUsernamePasswordAuth.setPort(1521);
		dsUsernamePasswordAuth.setUserName("system");
		dsUsernamePasswordAuth.setPassword("oracle");
		dsUsernamePasswordAuth.setInstanceName("ora9i");
		dsUsernamePasswordAuth.setDbType(DBType.ORACLE.getDbTypeValue());

		Assert.assertTrue("测试oracle连接失败", databaseCliProcessor.validateUsernamePasswordAuth(dsUsernamePasswordAuth));
	}

	@Test
	public void testGbase1() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");

		DatasourceDatabaseCli gbase1 = new DatasourceDatabaseCli(DataBaseType.GBASE8T.id,
				"192.168.238.214", "ylhzmc", "5258", "sysdba", "sysdba");
		gbase1.connect(true);
	}

	@Test
	public void testHive1() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");

		DatasourceDatabaseCli hive = new DatasourceDatabaseCli(DataBaseType.HIVE.id,
				"192.168.239.1", "chail", "10000", "hive", "");
		hive.connect(true);
	}

	@Test
	public void testHive2() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");

		DatasourceDatabaseCli hive = new DatasourceDatabaseCli(DataBaseType.HIVE.id,
				"192.168.239.1", "chail", "10000", "hive", "", new Properties());
		hive.connect(true);
	}

	public Properties kerberosContent() {
		Properties properties = new Properties();
		properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_PRINCIPAL, "chail@HADOOP.COM");
		properties.setProperty(DatasourceConstant.KEY_DB_HIVE_PRINCIPAL, "hive/hadoop.hadoop.com@HADOOP.COM");

		String keytab = Base64.encode(new FileReader(DatabaseCliProcessorTest.class.getClassLoader().getResource("user.keytab").getFile()).readBytes());
		String krb5 = Base64.encode(new FileReader(DatabaseCliProcessorTest.class.getClassLoader().getResource("krb5.conf").getFile()).readBytes());
		properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_KEYTAB_CONTENT, keytab);
		properties.setProperty(DatasourceConstant.KEY_DB_KRB5_CONTENT, krb5);
		return properties;
	}

	public Properties kerberosPath() {
		Properties properties = new Properties();
		properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_KEYTAB_PATH, DatabaseCliProcessorTest.class.getClassLoader().getResource("user.keytab").getPath());
		properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_PRINCIPAL, "chail@HADOOP.COM");
		properties.setProperty(DatasourceConstant.KEY_DB_KRB5_PATH, HiveProcessor.class.getClassLoader().getResource("krb5.conf").getFile());
		properties.setProperty(DatasourceConstant.KEY_DB_HIVE_PRINCIPAL, "hive/hadoop.hadoop.com@HADOOP.COM");
		return properties;
	}

	@Test
	public void testHive3() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");

		Properties properties = kerberosPath();
		DatasourceDatabaseCli hive = new DatasourceDatabaseCli(DataBaseType.HIVE_FHD653.pluginId, "192.168.51.84", "default", "21066", "", "", properties);
		hive.connect(true);
	}

	@Test
	public void testHive4() throws Exception {
		System.setProperty(DatasourceConstant.MCDATASOURCE_HOME, "F:\\mcdatasource");

		Properties properties = kerberosContent();
		DatasourceDatabaseCli hive = new DatasourceDatabaseCli(
				DataBaseType.HIVE_FHD653.pluginId, "192.168.51.84", "default", "21066", "hive", "hive", properties);
		hive.connect(true);
	}
}