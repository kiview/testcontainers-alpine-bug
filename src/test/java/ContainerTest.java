/*
 *    testcontainers-alpine-bug - Project for demonstrating incompatibility of testcontainers lib with Alpine
 *    Copyright (C) 2017 Kevin Wittek
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software Foundation,
 *    Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.org.apache.http.client.methods.CloseableHttpResponse;
import org.testcontainers.shaded.org.apache.http.client.methods.HttpGet;
import org.testcontainers.shaded.org.apache.http.impl.client.CloseableHttpClient;
import org.testcontainers.shaded.org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class ContainerTest {

    @ClassRule
    public static GenericContainer whoami =
            new GenericContainer("emilevauge/whoami:latest")
                    .withExposedPorts(8080);




    @Test
    public void containerIsAccessible() throws IOException {
        // given
        int port = whoami.getMappedPort(8080) + 1;
        String containerUrl = "http://" + whoami.getContainerIpAddress() + ":" + port;
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(containerUrl);

        // when
        CloseableHttpResponse response = client.execute(httpGet);

        // then
        assert response.getStatusLine().getStatusCode() == 200;
    }


}
