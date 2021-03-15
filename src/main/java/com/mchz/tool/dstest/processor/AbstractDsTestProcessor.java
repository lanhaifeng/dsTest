package com.mchz.tool.dstest.processor;

import com.mchz.mcdatasource.core.DataBaseType;
import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.domain.auth.DsKerberosAuth;
import com.mchz.tool.dstest.domain.auth.DsLdapAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernameAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBAuthMode;
import com.mchz.tool.dstest.enums.DBType;
import com.mchz.tool.dstest.exception.NotSupportDbAuthException;
import com.mchz.tool.dstest.util.IpUtils;
import com.mchz.tool.dstest.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * soc
 * 2021/2/4 17:23
 * 数据源测试抽象类
 *
 * @author lanhaifeng
 * @since
 **/
public abstract class AbstractDsTestProcessor implements DsTestProcessor {

	protected static Logger logger = LoggerFactory.getLogger(AbstractDsTestProcessor.class);

	@Override
	public boolean testService(String ip, Integer port) {
		return IpUtils.checkIpPort(ip, port);
	}

	@Override
	public boolean testConnection(DBAuthMode dsAuthMode, DsConnection dsConnection) {
		if(Objects.isNull(dsAuthMode) || Objects.isNull(dsConnection) || !ValidateUtils.validateResult(dsConnection)
				|| !support(dsConnection.getDbTypeDict())){
			return false;
		}
		switch (dsAuthMode){
			case NO_AUTH:
				return validateNoAuth(dsConnection);
			case USERNAME_AUTH:
				return dsConnection instanceof DsUsernameAuth && validateUsernameAuth((DsUsernameAuth)dsConnection);
			case USERNAME_PASSWORD_AUTH:
				return dsConnection instanceof DsUsernamePasswordAuth && validateUsernamePasswordAuth((DsUsernamePasswordAuth)dsConnection);
			case LDAP_AUTH:
				return dsConnection instanceof DsLdapAuth && validateLdapAuth((DsLdapAuth)dsConnection);
			case KERBEROS_AUTH:
				return dsConnection instanceof DsKerberosAuth && validateKerberosAuth((DsKerberosAuth)dsConnection);
		}
		return false;
	}

	@Override
	public boolean support(DBType dbType) {
		return Objects.nonNull(dbType) && Objects.nonNull(getSupportDbTypes()) && getSupportDbTypes().contains(dbType);
	}

	/**
	 * 2021/2/4 19:01
	 * 获取支持的数据库类型
	 *
	 * @param
	 * @author lanhaifeng
	 * @return java.util.List<com.mchz.tool.ds.enums.DBType>
	 */
	public abstract List<DBType> getSupportDbTypes();

	/**
	 * 2021/2/4 18:49
	 * 根据用户名测试连接
	 *
	 * @param dsUsernameAuth
	 * @author lanhaifeng
	 * @return boolean
	 */
	protected boolean validateUsernameAuth(DsUsernameAuth dsUsernameAuth){
		throw new NotSupportDbAuthException(dsUsernameAuth.getDbTypeDict(), DBAuthMode.USERNAME_AUTH);
	}

	/**
	 * 2021/2/4 18:49
	 * 根据用户名密码测试连接
	 *
	 * @param dsUsernamePasswordAuth
	 * @author lanhaifeng
	 * @return boolean
	 */
	protected boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth){
		throw new NotSupportDbAuthException(dsUsernamePasswordAuth.getDbTypeDict(), DBAuthMode.USERNAME_PASSWORD_AUTH);
	}

	/**
	 * 2021/2/4 18:49
	 * 根据ldap测试连接
	 *
	 * @param ldapAuth
	 * @author lanhaifeng
	 * @return boolean
	 */
	protected boolean validateLdapAuth(DsLdapAuth ldapAuth){
		throw new NotSupportDbAuthException(ldapAuth.getDbTypeDict(), DBAuthMode.LDAP_AUTH);
	}

	/**
	 * 2021/2/4 18:49
	 * 根据kerberos测试连接
	 *
	 * @param dsKerberosAuth
	 * @author lanhaifeng
	 * @return boolean
	 */
	protected boolean validateKerberosAuth(DsKerberosAuth dsKerberosAuth){
		throw new NotSupportDbAuthException(dsKerberosAuth.getDbTypeDict(), DBAuthMode.KERBEROS_AUTH);
	}

	/**
	 * 2021/2/22 9:43
	 * 无认证测试连接
	 *
	 * @param dsConnection
	 * @author lanhaifeng
	 * @return boolean
	 */
	protected boolean validateNoAuth(DsConnection dsConnection){
		throw new NotSupportDbAuthException(dsConnection.getDbTypeDict(), DBAuthMode.NO_AUTH);
	}

	protected List<DataBaseType> toDataBaseType(DsConnection dsConnection){
		DBType dbType = dsConnection.getDbTypeDict();
		if(Objects.isNull(dbType)){
			return null;
		}

		List<DataBaseType> dataBaseTypes = new ArrayList<>();
		switch (dbType) {
			case MYSQL:
				dataBaseTypes.add(DataBaseType.MYSQL);
				dataBaseTypes.add(DataBaseType.MYSQL_5);
				dataBaseTypes.add(DataBaseType.MYSQL_8);
				break;
			case SQLSERVER:
			case RDS_SQLSERVER:
				dataBaseTypes.add(DataBaseType.MSSQL);
				break;
			case POSTGRESQL:
				dataBaseTypes.add(DataBaseType.PGSQL);
				break;
			case DAMENG:
				dataBaseTypes.add(DataBaseType.DM);
				break;
			case HIVE:
				dataBaseTypes.add(DataBaseType.HIVE);
				dataBaseTypes.add(DataBaseType.HIVE_FHD653);
				dataBaseTypes.add(DataBaseType.HIVE_TDH6);
				dataBaseTypes.add(DataBaseType.HIVE_CDH634);
				dataBaseTypes.add(DataBaseType.HIVE_APACHE121);
				dataBaseTypes.add(DataBaseType.HIVE_HDP2650_121);
				break;
			case GBASE:
				dataBaseTypes.add(DataBaseType.GBASE8A);
				dataBaseTypes.add(DataBaseType.GBASE8T);
				break;
			case HIGHGODB:
				dataBaseTypes.add(DataBaseType.HIGHGO);
				break;
			case KINGBASE:
				dataBaseTypes.add(DataBaseType.KINGBASE8);
				dataBaseTypes.add(DataBaseType.KINGBASE);
				break;
			case RDS_MYSQL:
				dataBaseTypes.add(DataBaseType.RDS_MYSQL);
				break;
			case RDS_POSTGRESQL:
				dataBaseTypes.add(DataBaseType.RDS_PGSQL);
				break;
			default:
				DataBaseType dataBaseType = DataBaseType.getDataBaseTypyByDriverTypeAndVersion(dbType.getDbTypeValue());
				if (Objects.nonNull(dataBaseType)) {
					dataBaseTypes.add(dataBaseType);
				}
				break;
		}

		return dataBaseTypes;
	}
}
