package com.mchz.tool.dstest.enums;


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
//	MARIADB("mariadb", "Mariadb", "com.mysql.jdbc.Driver", "jdbc:mysql://[ip]:[port]/[instanceName]"),
	MARIADB("mariadb", "Mariadb", "org.mariadb.jdbc.Driver", "jdbc:mariadb://[ip]:[port]/[instanceName]"),

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

	K_DB("kdb", "KDB", "com.inspur.jdbc.KdDriver", "jdbc:inspur:thin:@[ip]:[port]:[instanceName]"),
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
