package com.mchz.tool.dstest.processor;

import com.mchz.mcdatasource.core.DataBaseType;
import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.domain.auth.DsKerberosAuth;
import com.mchz.tool.dstest.domain.auth.DsLdapAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernameAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import com.mchz.tool.dstest.enums.DBAuthMode;
import com.mchz.tool.dstest.exception.NotSupportDbAuthException;
import com.mchz.tool.dstest.util.IpUtils;
import com.mchz.tool.dstest.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	protected DataBaseType toDataBaseType(DsConnection dsConnection){
		DBType dbType = dsConnection.getDbTypeDict();
		DBAuthMode dbAuthMode = dsConnection.getDBAuthMode();
		if(Objects.isNull(dbType)){
			return null;
		}
		for(DataBaseType dataBaseType : DataBaseType.values()){
			if(dataBaseType.id.equalsIgnoreCase(dbType.getDbTypeValue())){
				return dataBaseType;
			}
		}
		switch (dbType){
			case SQLSERVER:
				return DataBaseType.MSSQL;
			case POSTGRESQL:
				return DataBaseType.PGSQL;
			case DAMENG:
				return DataBaseType.DM;
			case HIVE:
				if(dbAuthMode == DBAuthMode.KERBEROS_AUTH){
					return DataBaseType.HIVE_FHD653;
				}
				return DataBaseType.HIVE;
		}

		return null;
	}
}
