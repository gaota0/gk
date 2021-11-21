package com.gk.gateway.client;

/**
 * @author gaot
 * @date 2021/11/21
 */
public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        final Client client = new Client();
        client.connect("127.0.0.1",80);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            client.sendMsg(i + "");
        }
    }
}
