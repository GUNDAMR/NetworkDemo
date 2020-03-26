package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) throws Exception {
        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final ServerSocket socket = new ServerSocket(8080);
        try {
            for (; ; ) {
                final Socket clientSocket = socket.accept();
                System.out.println(
                        "Accepted connection from " + clientSocket);
                executor.execute(() -> {
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
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
