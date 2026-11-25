<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Orarend</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Tantárgy</th>
                        <th>Típus</th>
                        <th>Nap</th>
                        <th>Kezdés</th>
                        <th>Befejezés</th>
                        <th>Helyszín</th>
                        <th>Oktató</th>
                        <th>Szak</th>
                    </tr>
                    <xsl:for-each select="OREG3H_orarend/ora">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="targy"/></td>
                            <td><xsl:value-of select="@tipus"/></td>
                            <td><xsl:value-of select="idopont/nap"/></td>
                            <td><xsl:value-of select="idopont/tol"/></td>
                            <td><xsl:value-of select="idopont/ig"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>