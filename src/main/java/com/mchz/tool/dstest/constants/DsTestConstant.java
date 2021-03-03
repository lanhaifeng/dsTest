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

			);

	/**
	 * 使用DatabaseCliProcessor的数据库集合
	 */
	public static List<DBType> databaseCliDbTypes = Arrays.asList(
			DBType.GBASE, DBType.SYBASE, DBType.DAMENG, DBType.ORACLE,
			DBType.REDIS, DBType.MONGODB, DBType.ELASTICSEARCH,
			DBType.MARIADB, DBType.HIGHGODB, DBType.INFORMIX, DBType.OSCAR,
			DBType.KINGBASE, DBType.GREENPLUM, DBType.SQLSERVER, DBType.DB2, DBType.MYSQL, DBType.POSTGRESQL, DBType.K_DB,
			DBType.RDS_MYSQL, DBType.RDS_POSTGRESQL, DBType.RDS_SQLSERVER);
}
