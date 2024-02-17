package com.my.cdntest;

import java.io.IOException;
import java.net.*;
import java.util.*;
import org.xbill.DNS.*;
import org.xbill.DNS.Record;

public class DnsServer {
    private static HashMap<String, byte[]> cdnConfig = new HashMap<>();
    private static byte[] ipAddress = new byte[]{(byte) 172, (byte) 16, (byte) 238, (byte) 12};


    static {
        // 配置CDN映射关系 边缘节点IP
        cdnConfig.put("web.cdn.test.c.ying", ipAddress);
    }

    public static void main(String[] args) {
        byte[] ipAddress = new byte[]{127, 0, 0, 1};
        try {
            // 创建DNS服务器Socket
            DatagramSocket serverSocket = new DatagramSocket(53);

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("CDN DNS 服务器启动 ------");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String domain = parseDnsQueryDomain(receivePacket.getData());
                System.out.println(domain);
                byte[] ip = cdnConfig.get(domain);

                if (ip != null) {
                    System.out.println(Arrays.toString(ip));
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    sendData = getResponse(receiveData, ip);

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    System.out.println("to: " + IPAddress.getHostAddress() + ":" + port);
                    serverSocket.send(sendPacket);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] getResponse(byte[] receiveData, byte[] ip) throws IOException {
        Message request = new Message(receiveData);
        Message response = new Message(request.getHeader().getID());
        response.getHeader().setFlag(Flags.QR);
        response.addRecord(request.getQuestion(), Section.QUESTION);

        // 在DNS响应中插入自定义IP地址
        Record customRecord = new ARecord(request.getQuestion().getName(), DClass.IN, 3600, InetAddress.getByAddress(ip));
        response.addRecord(customRecord, Section.ANSWER);
        return response.toWire();
    }

    private static String parseDnsQueryDomain(byte[] array) {
        // 从第13字节开始，遵循[域名长度][域名][域名长度][域名]...0x00的规律
        // 故按照上述规律，从第13字节开始，将域名的所有组成部分获取出来并拼接
        List<String> domainParts = new ArrayList<>();
        int lengthIndex = 12;
        do {
            int partLength = array[lengthIndex] & 0xFF;
            String s = new String(Arrays.copyOfRange(array, lengthIndex + 1, lengthIndex + partLength + 2)).trim();
            domainParts.add(s);
            lengthIndex = lengthIndex + partLength + 1;
        } while ((array[lengthIndex] & 0xFF) != 0);
        return String.join(".", domainParts);
    }

}