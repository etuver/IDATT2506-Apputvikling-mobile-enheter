package no.eventu.server

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

private const val PORT = 12345
class Server(private val serverLog: TextView) {

    fun start(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                addToServerLog("Starter tjener...")
                //ui ="Starter tjener..."
                ServerSocket(PORT).use { serverSocket: ServerSocket ->
                    addToServerLog("ServerSocket opprettet, venter på klient...")
                    //ui = "ServerSocket opprettet, venter på klient..."

                    serverSocket.accept().use { clientSocket: Socket ->
                        addToServerLog("En Klient koblet seg til: \n $clientSocket")
                        //ui = "En Klient koblet seg til: \n$clientSocket"

                        sendToClient(clientSocket, "server", "Velkommen klient!")

                        readFromClient(clientSocket)
                    }
                }
            } catch (e: IOException){
                Log.e("Start(): ", e.toString())
                addToServerLog("error in start(): " + e.toString())
                //ui = e.message

            }


        }
    }

    private fun readFromClient(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val username = reader.readLine()
        val message = reader.readLine()
        addToServerLog("From Client: $username : $message")
        sendToClient(socket, username, message)
        //ui = "Klienten sier:\n$message"
    }

    private fun sendToClient(socket: Socket, username: String, message: String) {
        val writer = PrintWriter(socket.getOutputStream(), true)
        writer.println(username)
        writer.println(message)
        addToServerLog("To Clients: $message")

        //ui = "Sendte følgende til klienten:\n$message"
    }

    private fun addToServerLog(string: String){
        MainScope().launch {
            val oldString = serverLog.text.toString()
            serverLog.text = oldString + "\n" + string + "\n"
        }
    }

    private var ui: String? = ""
        set(str){
            MainScope().launch {
                serverLog.text =  str + "\n"
                field = str
            }
        }
}
