package com.mchz.tool.dstest.enums;


import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * soc
 * 2021/1/20 18:08
 * 数据库类型枚举
 *
 * @author lanhaifeng
 * @since
 **/
public enum DBType {

	ORACLE("oracle", "Oracle", "oracle.jdbc.xa.client.OracleXADataSource", "jdbc:oracle:thin:@[ip]:[port]/[instanceName]"),
	MYSQL("mysql", "MySQL", "com.mysql.jdbc.Driver", "jdbc:mysql://[ip]:[port]/[instanceName]"),
	MARIADB("mariadb", "Mariadb", "com.mysql.jdbc.Driver", "jdbc:mysql://[ip]:[port]/[instanceName]"),

	SQLSERVER("sqlserver", "SQL Server", "net.sourceforge.jtds.jdbcx.JtdsDataSource", "jdbc:jtds:sqlserver://[ip]:[port];DatabaseName=[instanceName]"),
	POSTGRESQL("postgresql", "PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://[ip]:[port]/[instanceName]"),
	GREENPLUM("greenplum", "Greenplum", "org.postgresql.Driver", "jdbc:postgresql://[ip]:[port]/[instanceName]"),
	HIGHGODB("highgodb", "HighgoDB", "org.postgresql.Driver", "jdbc:postgresql://[ip]:[port]/[instanceName]"),
	DB2("db2", "DB2", "com.ibm.db2.jcc.DB2DataSource", "jdbc:db2://[ip]:[port]/[instanceName]"),

	OSCAR("oscar", "Oscar", "com.oscar.Driver", "jdbc:oscar://[ip]:[port]/[instanceName]"),
	INFORMIX("informix", "Informix", "com.informix.jdbc.IfxDriver", "jdbc:informix-sqli://[ip]:[port]/[instanceName]:INFORMIXSERVER=[serviceName]"),
	HIVE("hive", "Hive", "org.apache.hive.jdbc.HiveDriver", ""),
	KINGBASE("kingbase", "KingBase", "com.kingbase8.Driver", "jdbc:kingbase8://[ip]:[port]/[instanceName]"),

	SYBASE("sybase", "Sybase", "net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sybase://[ip]:[port]/[instanceName]"),
	DAMENG("dameng", "DaMeng", "dm.jdbc.driver.DmDriver", "jdbc:dm://[ip]:[port]/[instanceName]"),

	K_DB("kdb", "K-DB", "com.inspur.jdbc.KdDriver", "jdbc:inspur:thin:@[ip]:[port]:[instanceName]"),
	GBASE("gbase", "GBase", "com.gbase.jdbc.Driver", "jdbc:gbase://[ip]:[port]/[instanceName]"),
	REDIS("redis", "Redis", "", ""),

	HBASE("hbase", "Hbase", "", ""),
	ELASTICSEARCH("elasticsearch", "ElasticSearch", "", ""),
	MONGODB("mongodb", "MongoDB", "", ""),

	RDS_MYSQL("rds_mysql", "阿里云RDS-MySQL", "com.mysql.jdbc.Driver", "jdbc:mysql://[ip]:[port]/[instanceName]"),
	RDS_POSTGRESQL("rds_postgresql", "阿里云RDS-PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://[ip]:[port]/[instanceName]"),
	RDS_SQLSERVER("rds_sqlserver", "阿里云RDS-SQL Server", "net.sourceforge.jtds.jdbcx.JtdsDataSource", "jdbc:jtds:sqlserver://[ip]:[port];DatabaseName=[instanceName]"),

	;

	//数据库类型英文值
	private String dbTypeText;
	//数据库类型标准化英文值 - 字段命名上有歧义，实际这个字段才是显示用的
	private String dbTypeValue;
	//数据库驱动class类
	private String driverClass;

	//jdbc url template
	private String jdbcUrlTemplate;


	DBType(String dbTypeText, String dbTypeValue, String driverClass, String jdbcUrlTemplate) {
		this.dbTypeText = dbTypeText;
		this.dbTypeValue = dbTypeValue;
		this.driverClass = driverClass;
		this.jdbcUrlTemplate = jdbcUrlTemplate;
	}

	public boolean equals(String dbtype) {
		return this.dbTypeValue.equalsIgnoreCase(dbtype);
	}

	public boolean equalsText(String dbtype) {
		return this.dbTypeText.equalsIgnoreCase(dbtype);
	}

	public boolean equalsAny(String dbtype) {
		return this.dbTypeValue.equalsIgnoreCase(dbtype) || this.dbTypeText.equalsIgnoreCase(dbtype);
	}

	public static DBType getDBType(String dbType) {
		for (DBType item : DBType.values()) {
			if (item.equalsAny(dbType)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * 2021/2/1 20:06
	 * 获取驱动url
	 *
	 * @param dbType    			数据库类型
	 * @param ip					ip或域名
	 * @param port					端口
	 * @param instanceName			实例名
	 * @param serviceName			服务名
	 * @author lanhaifeng
	 * @return java.lang.String
	 */
	public static String getUrl(String dbType, String ip, String port, String instanceName, String serviceName) {
		if (StringUtils.isEmpty(instanceName) || "null".equalsIgnoreCase(instanceName)) {
			instanceName = "";
		}

		if (StringUtils.isEmpty(serviceName) || "null".equalsIgnoreCase(serviceName)) {
			serviceName = "";
		}

		DBType dbTypeEnum = getDBType(dbType);
		if(Objects.isNull(dbTypeEnum)){
			return null;
		}

		switch (dbTypeEnum){
			case ORACLE:
				if (StringUtils.isNotEmpty(instanceName)) {
					return "jdbc:oracle:thin:@" + ip + ":" + port + ":" + instanceName;
				} else if (StringUtils.isNotEmpty(serviceName)) {
					return "jdbc:oracle:thin:@" + ip + ":" + port + "/" + serviceName;
				}
			case SQLSERVER:
				return "jdbc:jtds:sqlserver://" + ip + ":" + port + ";DatabaseName=" + instanceName;
			case DB2:
				return "jdbc:db2://" + ip + ":" + port + "/" + instanceName;
			case SYBASE:
				return "jdbc:sybase:Tds:" + ip + ":" + port + "/" + instanceName;
			case DAMENG:
				return "jdbc:dm://" + ip + ":" + port + "/" + instanceName;
			case POSTGRESQL:
			case GREENPLUM:
			case HIGHGODB:
				return "jdbc:postgresql://" + ip + ":" + port + "/" + instanceName;
			case MYSQL:
			case MARIADB:
				return "jdbc:mysql://" + ip + ":" + port + "/" + instanceName;
			case OSCAR:
				return "jdbc:oscar://" + ip + ":" + port + "/" + instanceName;
			case INFORMIX: //TODO
				return "jdbc:informix-sqli://" + ip + ":" + port + "/" + instanceName + "INFORMIXSERVER=" + serviceName;
			case HIVE:
				if(false){ //kerberos认证
//					return "jdbc:hive2://"+ ip + ":" + port +
//							"/\;principal=hive/hadoop.hadoop.com@HADOOP.COM -u hiveuser@HADOOP.COM -p ./data/kerberos/hiveuser.keytab -k -kf ./data/kerberos/krb5.conf";
				}else {
					return "jdbc:hive2://" + ip + ":" + port;
				}
			case KINGBASE:
				return "jdbc:kingbase8://" + ip + ":" + port + "/" + instanceName;

			case MONGODB: //特殊字符需要urlcode消除歧义
				return "mongodb://" + ip + ":" + port + "/" + instanceName;
//				return "mongodb://mongoTestAdmin:123456@192.168.202.2:27017/admin"


		}
		return null;
	}

	public String getDbTypeText() {
		return dbTypeText;
	}

	public String getDbTypeValue() {
		return dbTypeValue;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getValue(){
		return this.dbTypeValue;
	}

	public String getLabel(){
		return this.dbTypeText;
	}

	public String getJdbcUrlTemplate() {
		return jdbcUrlTemplate;
	}
}
