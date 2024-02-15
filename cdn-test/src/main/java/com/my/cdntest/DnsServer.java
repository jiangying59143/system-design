package com.my.cdntest;

import java.net.*;
import java.util.HashMap;

public class DnsServer {
    private static HashMap<String, String> cdnConfig = new HashMap<>();

    static {
        // 配置CDN映射关系 边缘节点IP
        cdnConfig.put("web.cdn.test.c.me", "172.16.238.12");
    }

    public static void main(String[] args) {
        try {
            // 创建DNS服务器Socket
            DatagramSocket serverSocket = new DatagramSocket(1053);

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("CDN DNS 服务器启动...3");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String domain = new String(receivePacket.getData(), 0, receivePacket.getLength());

                String ip = cdnConfig.get(domain);
                if (ip != null) {
                    System.out.println(ip);
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    sendData = ip.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    System.out.println("from: " + ip + ":" + 1053);
                    System.out.println("to: " + IPAddress.getHostAddress() + ":" + port);
                    serverSocket.send(sendPacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

