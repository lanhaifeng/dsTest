package com.mchz.tool.dstest.exception;


import com.mchz.tool.dstest.enums.DBAuthMode;
import com.mchz.tool.dstest.enums.DBType;

/**
 * dsTest
 * 2021/2/4 19:16
 * 不支持当前认证方式
 *
 * @author lanhaifeng
 * @since
 **/
public class NotSupportDbAuthException extends RuntimeException {

    private static String messageTemplate = "%s不支持%s认证";

    public NotSupportDbAuthException(DBType dbType, DBAuthMode dbAuthMode){
        super(String.format(messageTemplate, dbType.getDbTypeValue(), dbAuthMode.getLabel()));
    }

    public NotSupportDbAuthException(Throwable cause, DBType dbType, DBAuthMode dbAuthMode) {
        super(String.format(messageTemplate, dbType.getDbTypeValue(), dbAuthMode.getLabel()), cause);
    }
}
