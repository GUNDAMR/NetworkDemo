package com.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程echo server
 */
public class MultiThread {

    public static void main(String[] args) throws Exception {
        final ServerSocket socket = new ServerSocket(8080);
        try {
            for (; ; ) {
                final Socket clientSocket = socket.accept();
                System.out.println(
                        "Accepted connection from " + clientSocket);
                new Thread(() -> {
                    byte[] b = new byte[4 * 1024];
                    try (InputStream in = clientSocket.getInputStream();
                         OutputStream out = clientSocket.getOutputStream()) {
                        int len;
                        while ((len = in.read(b)) != -1) {
                            System.out.println("echo[" + Thread.currentThread().getId() + "]:" + new String(b));
                            out.write(b, 0, len);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            System.out.println("exit [" + Thread.currentThread().getId() + "]");
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
