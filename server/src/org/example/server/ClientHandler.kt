package org.example.server

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import java.time.LocalDateTime

class ClientHandler(private val socket: Socket, private val server: Server) : Runnable {
    var dataOut: DataOutputStream = DataOutputStream(socket.getOutputStream())
    var dataIn: DataInputStream = DataInputStream(socket.getInputStream())
    override fun run() {
        while(true) {
            val message = dataIn.readUTF();
            println("server received: $message [${LocalDateTime.now()}]")
            dataOut.writeUTF(message)
            dataOut.flush()
        }


    }
}
