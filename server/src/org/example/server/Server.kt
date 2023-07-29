package org.example.server

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket
import java.util.concurrent.CompletableFuture

class Server(private val port: Int) : Runnable {

    private lateinit var dataOut: DataOutputStream
    private lateinit var dataIn: DataInputStream
    private lateinit var serverSocket: ServerSocket

    override fun run() {
        CompletableFuture.runAsync {
           serverSocket = ServerSocket(port)
            while (true) {
                val socket = serverSocket.accept()
                CompletableFuture.runAsync {
                    ClientHandler(socket, this).apply {
                        run()
                    }
                }

            }
        }.join()

    }
    fun shutdown() {
        serverSocket.close();
    }

}
