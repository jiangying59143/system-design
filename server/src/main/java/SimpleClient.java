import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimpleClient {
    private String serverResponse(String IP, int reqIndex){
        Socket socket = new Socket();
        StringBuilder sb = new StringBuilder();
        try {
            // 模拟客户端请求
            socket.connect(new InetSocketAddress(IP, 18888), 5000);
            OutputStream outputStream = socket.getOutputStream();
            String request = "request " + reqIndex;
            outputStream.write(request.getBytes());
            outputStream.flush();

            // 读取服务器的响应
            byte[] responseBytes = new byte[1024];
            int bytesRead = socket.getInputStream().read(responseBytes);
            String response = new String(responseBytes, 0, bytesRead);
            sb.append(request + " - " + response).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        SimpleClient simpleClient = new SimpleClient();
        List<Callable<String>> callableList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            int finalI = i;
            //172.21.231.91
            callableList.add(()->simpleClient.serverResponse( "localhost",finalI));
        }
        List<Future<String>> futureList = executorService.invokeAll(callableList);
        futureList.stream().forEach(future-> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        long endTime = System.currentTimeMillis();
        long sec = (endTime - startTime) / 1000;
        System.out.println(sec);
    }

}
