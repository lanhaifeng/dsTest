package com.mchz.tool.dstest.constants;

import com.mchz.tool.dstest.enums.DBType;

import java.util.Arrays;
import java.util.List;

/**
 * soc
 * 2021/2/4 20:03
 * 常量类
 *
 * @author lanhaifeng
 * @since
 **/
public class DsTestConstant {

	/**
	 * 使用JdbcTestProcessor的数据库集合
	 */
	public static List<DBType> jdbcDbTypes = Arrays.asList(
			DBType.KINGBASE, DBType.MARIADB, DBType.SQLSERVER, DBType.GREENPLUM,
			DBType.GBASE,
			DBType.DB2,
			DBType.HIGHGODB, DBType.OSCAR, DBType.INFORMIX,
			DBType.RDS_MYSQL, DBType.RDS_POSTGRESQL, DBType.RDS_SQLSERVER
			);

	/**
	 * 使用DatabaseCliProcessor的数据库集合
	 */
	public static List<DBType> databaseCliDbTypes = Arrays.asList(DBType.SYBASE, DBType.DAMENG, DBType.ORACLE,
			DBType.MYSQL, DBType.POSTGRESQL, DBType.K_DB);
}
