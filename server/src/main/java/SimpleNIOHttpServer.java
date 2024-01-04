import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleNIOHttpServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    // 构造函数
    public SimpleNIOHttpServer(int port) throws IOException {
        // 打开 Selector
        selector = Selector.open();
        // 打开 ServerSocketChannel 并绑定到指定端口
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 将 ServerSocketChannel 注册到 Selector，监听 ACCEPT 事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    // 启动服务器
    public void start() throws IOException {
        System.out.println("Server started...");
        while (true) {
            // Selector 进行事件轮询
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if (key.isAcceptable()) {
                    // 处理 ACCEPT 事件
                    handleAcceptable(key);
                } else if (key.isReadable()) {
                    // 处理 READ 事件
                    handleReadable(key);
                }
            }
        }
    }

    // 处理 ACCEPT 事件
    private void handleAcceptable(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        // 接受客户端连接
        SocketChannel clientSocketChannel = serverSocketChannel.accept();
        clientSocketChannel.configureBlocking(false);
        // 将客户端 SocketChannel 注册到 Selector，监听 READ 事件
        clientSocketChannel.register(selector, SelectionKey.OP_READ);
    }

    // 处理 READ 事件
    private void handleReadable(SelectionKey key) {
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder sb = new StringBuilder();

            buffer.clear();
            while (clientSocketChannel.read(buffer) > 0) {
                buffer.flip();
                sb.append(new String(buffer.array(), 0, buffer.limit()));
            }
            System.out.println("receive msg: " + sb);
            String response = sb + " 200";
            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
            clientSocketChannel.write(responseBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(clientSocketChannel != null)clientSocketChannel.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 主方法
    public static void main(String[] args) {
        try {
            // 创建并启动服务器
            SimpleNIOHttpServer httpServer = new SimpleNIOHttpServer(18888);
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

