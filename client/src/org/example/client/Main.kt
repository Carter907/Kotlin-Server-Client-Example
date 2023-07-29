package org.example.client

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val client = Client(8081);
        client.connect();
        client.sendData("hello world".toByteArray());
        val messageFromServer = String(client.getReceivedData())
        println("message from server: $messageFromServer")
        client.join();
    }
}