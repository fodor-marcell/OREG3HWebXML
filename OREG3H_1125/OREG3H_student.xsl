<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
<xsl:template match="/">
    <html>
        <body>
            <h2>Student List</h2>
            <table border="1">
                <tr bgcolor="#9acd32">
                    <th>ID</th>
                    <th>Vezetéknév</th>
                    <th>Keresztnev</th>
                    <th>Becenev</th>
                    <th>Kor</th>
                </tr>
                <xsl:for-each select="class/student">
                    <tr>
                        <td><xsl:value-of select="@id"/></td>
                        <td><xsl:value-of select="keresztnev"/></td>
                        <td><xsl:value-of select="vezeteknev"/></td>
                        <td><xsl:value-of select="becenev"/></td>
                        <td><xsl:value-of select="kor"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
    </html>
</xsl:template>
</xsl:stylesheet>