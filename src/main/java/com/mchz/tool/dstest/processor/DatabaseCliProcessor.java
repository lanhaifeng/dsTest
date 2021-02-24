package com.mchz.tool.dstest.processor;

import com.mchz.datasource.cli.DatasourceDatabaseCli;
import com.mchz.mcdatasource.core.DataBaseType;
import com.mchz.tool.dstest.constants.DsTestConstant;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.List;

/**
 * soc
 * 2021/2/22 19:34
 * DatasourceDatabaseCli方式测试连接
 *
 * @author lanhaifeng
 * @since
 **/
public class DatabaseCliProcessor extends AbstractDsTestProcessor {

	@Override
	public List<DBType> getSupportDbTypes() {
		return DsTestConstant.databaseCliDbTypes;
	}

	@Override
	protected boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth) {
		try {
			DataBaseType dataBaseType = toDataBaseType(dsUsernamePasswordAuth);

			String dbName = dsUsernamePasswordAuth.getInstanceName();
			if(StringUtils.isBlank(dbName)){
				dbName = dsUsernamePasswordAuth.getServiceName();
			}
			DatasourceDatabaseCli datasourceDatabase =
					new DatasourceDatabaseCli(dataBaseType.pluginId,
							dsUsernamePasswordAuth.getAddress(), dbName, String.valueOf(dsUsernamePasswordAuth.getPort()),
							dsUsernamePasswordAuth.getUserName(), dsUsernamePasswordAuth.getPassword());
			datasourceDatabase.connect(true);
			return true;
		} catch (Exception e) {
			logger.error("测试连接错误：" + ExceptionUtils.getFullStackTrace(e));
			return false;
		}
	}
}
