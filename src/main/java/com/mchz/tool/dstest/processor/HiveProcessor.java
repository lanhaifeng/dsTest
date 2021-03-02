package com.mchz.tool.dstest.processor;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.file.FileReader;
import com.mchz.datasource.cli.DatasourceDatabaseCli;
import com.mchz.mcdatasource.core.DataBaseType;
import com.mchz.mcdatasource.core.DatasourceConstant;
import com.mchz.tool.dstest.domain.auth.DsKerberosAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * soc
 * 2021/2/22 19:34
 * hive方式测试连接
 *
 * @author lanhaifeng
 * @since
 **/
public class HiveProcessor extends AbstractDsTestProcessor {

	@Override
	public List<DBType> getSupportDbTypes() {
		return Arrays.asList(DBType.HIVE);
	}


	@Override
	protected boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth) {
		try {
			Properties properties = new Properties();
			DataBaseType dataBaseType = toDataBaseType(dsUsernamePasswordAuth);
			if (dsUsernamePasswordAuth instanceof DsKerberosAuth) {
				DsKerberosAuth dsKerberosAuth = (DsKerberosAuth) dsUsernamePasswordAuth;
				if(StringUtils.isNotBlank(dsKerberosAuth.getPrincipal())){
					properties.setProperty(DatasourceConstant.KEY_DB_HIVE_PRINCIPAL, dsKerberosAuth.getPrincipal());
				}

				String keytab = Base64.encode(new FileReader(dsKerberosAuth.getClientKeyTabFile()).readBytes());
				String krb5 = Base64.encode(new FileReader(dsKerberosAuth.getConfigFile()).readBytes());
				properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_KEYTAB_CONTENT, keytab);
				properties.setProperty(DatasourceConstant.KEY_DB_KRB5_CONTENT, krb5);
				properties.setProperty(DatasourceConstant.KEY_DB_LOGIN_PRINCIPAL, dsUsernamePasswordAuth.getUserName());
				if(dataBaseType == DataBaseType.HIVE){
					dataBaseType = DataBaseType.HIVE_FHD653;
				}
			}
			DatasourceDatabaseCli hive = new DatasourceDatabaseCli(dataBaseType.pluginId, dsUsernamePasswordAuth.getAddress(),
					dsUsernamePasswordAuth.getInstanceName(), String.valueOf(dsUsernamePasswordAuth.getPort()),
					dsUsernamePasswordAuth.getUserName(), dsUsernamePasswordAuth.getPassword(), true, properties);
			hive.connect(true);

			return true;
		} catch (Exception e) {
			logger.error("hive测试连接错误：" + ExceptionUtils.getFullStackTrace(e));

			return false;
		}
	}

	@Override
	protected boolean validateKerberosAuth(DsKerberosAuth dsKerberosAuth) {
		return validateUsernamePasswordAuth(dsKerberosAuth);
	}

}
