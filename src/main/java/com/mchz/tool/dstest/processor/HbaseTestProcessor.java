package com.mchz.tool.dstest.processor;

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
public class HbaseTestProcessor extends AbstractDsTestProcessor {

	@Override
	public List<DBType> getSupportDbTypes() {
		return Arrays.asList(DBType.HBASE);
	}

	@Override
	public boolean validateUsernamePasswordAuth(DsUsernamePasswordAuth dsUsernamePasswordAuth) {
		return super.validateUsernamePasswordAuth(dsUsernamePasswordAuth);
	}

	@Override
	public boolean validateKerberosAuth(DsKerberosAuth dsKerberosAuth) {
		return super.validateKerberosAuth(dsKerberosAuth);
	}

}
