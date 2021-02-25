1.导入客户端依赖

```
<dependency>
	<groupId>com.mchz</groupId>
	<artifactId>datasource-cli</artifactId>
	<version>1.3.0-SNAPSHOT</version>
</dependency>
```



2.使用

2.0数据源包地址

```
http://file.mchz.com.cn/projects/mcdatasource/mcdatasource-package-1.3.0.4-soc-patch-bin.zip
```

下载解压该压缩文件到指定路径`/data`

2.1设置环境变量

```
setProperty(DatasourceConstant.MCDATASOURCE_HOME,  "/data/mcdatasource");
```

2.2获取连接

```
    /**
     * 测试连接
     *
     * 测试成功---> 没有抛出异常
     * 测试失败 ---> 抛出异常
     *
     *
     * @throws Exception
     */
    @Test
    public void hiveConnectTest() throws Exception {
        DatasourceDatabaseCli datasourceDatabaseCli = new DatasourceDatabaseCli(DataBaseType.HIVE.id,
                "192.168.239.1", "chail", "10000", "hive", "");
        datasourceDatabaseCli.connect(true);
    }
```

3.常用驱动包
```xml
        <!-- oracle driver -->
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>ojdbc6</artifactId>
            <version>11.2.0.4.0-atlassian-hosted</version>
        </dependency>

        <!-- sqlserver driver -->
        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>sqljdbc4</artifactId>
            <version>4.0</version>
        </dependency>

        <!-- mysql driver -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.42</version>
        </dependency>

        <!-- dameng driver -->
        <dependency>
            <groupId>com.dameng</groupId>
            <artifactId>Dm7JdbcDriver17</artifactId>
            <version>7.6.0.77</version>
        </dependency>

        <!-- jtds driver -->
        <dependency>
            <groupId>net.sourceforge.jtds</groupId>
            <artifactId>jtds</artifactId>
            <version>1.3.1</version>
        </dependency>

        <!-- oscar driver -->
        <dependency>
            <groupId>com.esen.jdbc</groupId>
            <artifactId>oscarJDBC</artifactId>
            <version>1.0</version>
        </dependency>

        <!-- db2 driver -->
        <dependency>
            <groupId>com.ibm.db2.jcc</groupId>
            <artifactId>db2jcc4</artifactId>
            <version>10.1</version>
        </dependency>

        <!-- hive driver -->
        <dependency>
            <groupId>org.apache.hive</groupId>
            <artifactId>hive-jdbc</artifactId>
            <version>1.2.2</version>
        </dependency>

        <!-- postgresql driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.4.jre7</version>
        </dependency>

        <!-- mongo driver -->
        <dependency>
            <groupId>org.mongodb</groupId>
            <artifactId>mongo-java-driver</artifactId>
            <version>2.13.0</version>
        </dependency>

        <!-- informix driver -->
        <dependency>
            <groupId>com.ibm.informix</groupId>
            <artifactId>jdbc</artifactId>
            <version>4.50.3</version>
        </dependency>

        <!-- kingbase driver -->
        <dependency>
            <groupId>com.kingbase8</groupId>
            <artifactId>jdbc</artifactId>
            <version>1.0</version>
        </dependency>

        <!-- K-DB driver -->
        <dependency>
            <groupId>com.inspur</groupId>
            <artifactId>inspur-jdbc</artifactId>
            <version>11</version>
        </dependency>

        <!-- Gbase driver -->
        <dependency>
            <groupId>com.gbase</groupId>
            <artifactId>jdbc</artifactId>
            <version>8.3.81.53</version>
            <classifier>bin</classifier>
        </dependency>

        <!-- redis -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.9.0</version>
        </dependency>

        <!-- elasticsearch -->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>transport</artifactId>
            <version>7.7.0</version>
        </dependency>

        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>7.7.0</version>
        </dependency>
        
        <!-- sybase driver -->
        <dependency>
            <groupId>com.sybase</groupId>
            <artifactId>jdbc4</artifactId>
            <version>16.0</version>
        </dependency>
        
        <!-- hbase driver -->
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-common</artifactId>
            <version>2.6.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-auth</artifactId>
            <version>2.6.0</version>
        </dependency>


        <dependency>
            <groupId>org.apache.hbase</groupId>
            <artifactId>hbase-client</artifactId>
            <version>1.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hbase</groupId>
            <artifactId>hbase-protocol</artifactId>
            <version>1.3.1</version>
        </dependency>
```