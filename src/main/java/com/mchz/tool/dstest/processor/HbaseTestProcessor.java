package com.mchz.tool.dstest.processor;

import com.mchz.tool.dstest.domain.DsConnection;
import com.mchz.tool.dstest.domain.auth.DsKerberosAuth;
import com.mchz.tool.dstest.domain.auth.DsUsernamePasswordAuth;
import com.mchz.tool.dstest.enums.DBType;

import java.util.Arrays;
import java.util.List;

/**
 * soc
 * 2021/2/22 10:23
 * Hbase测试连接
 *
 * @author lanhaifeng
 * @since
 **/
public class HbaseTestProcessor extends HiveProcessor {

	@Override
	public List<DBType> getSupportDbTypes() {
		return Arrays.asList(DBType.HBASE);
	}

	@Override
	protected boolean validateNoAuth(DsConnection dsConnection) {
		return validateUsernamePasswordAuth((DsUsernamePasswordAuth) dsConnection);
	}
}
