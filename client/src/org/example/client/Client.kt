package org.example.client

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.Socket
import java.net.UnknownHostException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.LinkedTransferQueue
import java.util.concurrent.TransferQueue

class Client(private val port: Int) {
    private lateinit var socket: Socket
    private lateinit var dataIn: DataInputStream
    private lateinit var dataOut: DataOutputStream
    private lateinit var clientFuture: CompletableFuture<Void>;
    var dataMedium = LinkedBlockingQueue<String>(1);
    fun connect() {
        try {

            socket = Socket(InetAddress.getLocalHost(), port)

            dataIn = DataInputStream(socket.getInputStream())
            dataOut = DataOutputStream(socket.getOutputStream())
            clientFuture = CompletableFuture.runAsync {
                while (true) {
                    dataOut.writeUTF(dataMedium.take());
                    dataOut.flush()

                    dataMedium.put(dataIn.readUTF());

                }
            }


        } catch (e: UnknownHostException) {
            System.err.println("unknown host: ")
        }
    }

    fun sendData(message: ByteArray) {
        this.dataMedium.put(String(message));
    }

    fun shutdown() {
        dataIn.close()
        dataOut.close()
        socket.close()
    }

    fun join() {
        clientFuture.join()
    }

    fun getReceivedData(): ByteArray {
        return dataMedium.take().toByteArray();
    }

}
