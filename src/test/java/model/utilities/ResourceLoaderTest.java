package model.utilities;

import org.junit.Assert;
import org.junit.Test;

public class ResourceLoaderTest {

    @Test
    public void testLoadResource() throws Exception {
        String expectedHtml = getExpectedHtml();
        String actualHtml = ResourceLoader.loadResource("about.html");

        // Normalize line endings
        expectedHtml = expectedHtml.replace("\r\n", "\n");
        actualHtml = actualHtml.replace("\r\n", "\n");

        Assert.assertEquals(expectedHtml, actualHtml);
    }

    private String getExpectedHtml() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Upload Scanner</h1>

<h2>Description</h2>
<hr>
A Burp Suite Pro extension to do security tests for HTTP file uploads.<br>
For more information see https://github.com/modzero/mod0BurpUploadScanner/<br>

<h2>Contributions</h2>
<b>Authors:
    Anthony Hanel, @ahanel13, https://anthonyhanel.me</b><br><br>

<hr>
Based on the <a href="https://github.com/modzero/mod0BurpUploadScanner">Python Upload Scanner</a> written by:
<ul>
    <li>Tobias "floyd" Ospelt, @floyd_ch, https://www.floyd.ch</li>
    <li>modzero AG, @mod0, https://www.modzero.ch</li>
</ul>
</body>
</html>""";
    }
}