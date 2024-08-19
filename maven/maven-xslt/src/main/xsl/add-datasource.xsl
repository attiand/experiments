<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()" />
        </xsl:copy>
    </xsl:template>

    <xsl:template match="//*[local-name()='datasources']/*[local-name()='datasource']">
        <xsl:copy copy-namespaces="no" >
            <xsl:apply-templates select="@*" />
            <xsl:apply-templates />
                <xa-datasource jndi-name="java:/jdbc/NYA" pool-name="NyA_Pool" enabled="true">
                    <xa-datasource-property name="ServerName">
                        nya-01.its.umu.se
                    </xa-datasource-property>
                    <xa-datasource-property name="PortNumber">
                        50000
                    </xa-datasource-property>
                    <xa-datasource-property name="databaseName">
                        MSAN_N11
                    </xa-datasource-property>
                    <xa-datasource-property name="currentSchema">
                        NYA
                    </xa-datasource-property>
                    <xa-datasource-property name="DriverType">
                        4
                    </xa-datasource-property>
                    <xa-datasource-property name="useJDBC4ColumnNameAndLabelSemantics">
                        2
                    </xa-datasource-property>
                    <xa-datasource-property name="enableExtendedIndicators">
                        2
                    </xa-datasource-property>
                    <xa-datasource-class>com.ibm.db2.jcc.DB2XADataSource</xa-datasource-class>
                    <driver>jcc.jar</driver>
                    <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                    <xa-pool>
                        <min-pool-size>5</min-pool-size>
                        <max-pool-size>40</max-pool-size>
                        <is-same-rm-override>false</is-same-rm-override>
                        <no-tx-separate-pools>true</no-tx-separate-pools>
                    </xa-pool>
                    <security>
                        <user-name>nyadbusr</user-name>
                        <password>4friskaviljor</password>
                    </security>
                    <timeout>
                        <set-tx-query-timeout>false</set-tx-query-timeout>
                        <blocking-timeout-millis>90000</blocking-timeout-millis>
                        <idle-timeout-minutes>30</idle-timeout-minutes>
                    </timeout>
                    <statement>
                        <prepared-statement-cache-size>200</prepared-statement-cache-size>
                        <share-prepared-statements>false</share-prepared-statements>
                    </statement>
                </xa-datasource>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>