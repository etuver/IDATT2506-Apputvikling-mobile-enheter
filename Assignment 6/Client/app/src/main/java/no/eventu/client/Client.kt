package no.eventu.client

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

private const val SERVER_IP = "10.0.2.2"
private const val SERVER_PORT: Int = 12345

class Client(private val messageLog: TextView, username: String, message: String) {



    fun start(username: String, message: String){
        CoroutineScope(Dispatchers.IO).launch {
            //addToMessagesLog("Console: ","Kobler til tjener...")
            try {
                Socket(SERVER_IP, SERVER_PORT).use { socket: Socket ->
                    //addToMessagesLog("Console: ", "Koblet til tjener:\n$socket" )

                    delay(1000)

                    readFromServer(socket)

                    delay(1000)
                    sendToServer(socket, username , message)
                }
            } catch (e: IOException){
                e.printStackTrace()
                Log.e("Error connecting: ",e.message.toString())
                addToMessagesLog("Console: " ,"Feil ved tilkobling: $e")
            }
        }



    }



    private fun readFromServer(socket: Socket){
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        var username = reader.readLine()
        var message = reader.readLine()
        if (username.isNullOrBlank()){
            username = "ukjent"
        }
        if (message.isNullOrBlank()){
            message = "ukjent"
        }

        addToMessagesLog(username, message)
    }

    private fun sendToServer(socket: Socket, username: String, message: String){
        val writer = PrintWriter(socket.getOutputStream(), true)
        writer.println(username)
        writer.println(message)
        addToMessagesLog("me", message)
    }


    private fun addToMessagesLog(username:String, message: String){
        MainScope().launch {
            val newMessage = messageLog.text.toString() + "\n\nFrom user: "+username + "\nMessage: " + message
            messageLog.text = newMessage
        }
    }

}