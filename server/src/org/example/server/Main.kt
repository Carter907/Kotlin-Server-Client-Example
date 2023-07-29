package org.example.server

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val server = Server(8081)
        server.run();

    }
}