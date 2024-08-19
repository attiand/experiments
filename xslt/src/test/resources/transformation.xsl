<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output indent="no" method="xml" version="1.0" encoding="UTF-8" standalone="yes"/>

    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!--

    <xsl:template match="online-trainings">
        <xsl:element name="trainings">
            <xsl:apply-templates select="@* | node()"/>
        </xsl:element>
    </xsl:template>
-->

</xsl:stylesheet>