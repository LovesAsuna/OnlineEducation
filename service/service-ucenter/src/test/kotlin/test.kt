
import java.net.InetSocketAddress
import java.net.ServerSocket

fun main() {
   val socket = ServerSocket()
    socket.bind(InetSocketAddress("127.0.0.1", 3306))
    System.`in`.read()
    socket.close()
}
