package no.eventu.assignment5

import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.*



const val ENCODING = "UTF-8"

class HttpWrapper(private val URL: String) {
    init {
        CookieHandler.setDefault(CookieManager(null, CookiePolicy.ACCEPT_ALL)) //mmm tasty cookies
    }

    private fun openConnection(url: String): URLConnection {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.setRequestProperty("Accept-charset", ENCODING)
        return connection
    }


    fun post(parameters: Map<String, String>): String{
        val connection = openConnection(URL)

        connection.doOutput = true
        //return "Oppgi et tall mellom 1 og 10!"
        val outputType = "application/x-www-form-urlencoded; charset=$ENCODING"
        connection.setRequestProperty("Content-Type", outputType)
        connection.outputStream.use { outputStream ->
            val postString = encodeParameters(parameters)
            Log.e("postString:", postString)
            outputStream.write(postString.toByteArray(charset(ENCODING)))
        }
        //return "Oppgi et tall mellom 1 og 10!"
        connection.inputStream.use {
            inputStream -> return readResponse(inputStream, getCharSet(connection))
            //return "Oppgi et tall mellom 1 og 10!"
        }
    }

     fun get(parameters: Map<String, String>): String{
        val fullURL = URL + encodeParameters(parameters)
        Log.i("fullURL:", fullURL)
        val connection = openConnection(fullURL)
        connection.inputStream.use { response ->
            return readResponse(response, getCharSet(connection))
        }
    }

    private fun encodeParameters(parameters: Map<String, String>): String {
        var encodedString = "?"

        for ((key, value) in parameters){
            try {
                encodedString += URLEncoder.encode(key, ENCODING)
                encodedString += "="
                encodedString += URLEncoder.encode(value, ENCODING)
                encodedString += "&"
                Log.e("encodedString:", encodedString)
            }catch (e: java.lang.Exception){
                Log.e("encodeParameters():", e.toString())
            }
        }
        return encodedString
    }

    private fun readResponse(inputstream: InputStream, charset: String?): String{
        var body = ""
        try {
            BufferedReader(InputStreamReader(inputstream, charset)).use {
                bufferedReader ->
                var line: String?
                do{
                    line = bufferedReader.readLine()
                    body += "$line\n"
                } while (line != null)
            }
        }catch (e: Exception){
            Log.e("readResponse(): ",e.toString())
            body += "Error when reading from server\n"
        }
        Log.i("responsebody:", body)
        return body.dropLast(5) //Remove null
    }

    /**
     * Check to see if the connection uses a different charset/encoding that we do
     */
    private fun getCharSet(connection: URLConnection): String? {
        var charset: String? = ENCODING
        val contentType = connection.contentType
        val contentInfo = contentType.replace(" ", "").split(";").toTypedArray()
        for (param in contentInfo) {
            if (param.startsWith("charset=")) charset = param.split("=").toTypedArray()[1]
        }
        Log.i("getCharSet()", "contentType = $contentType")
        Log.i("getCharSet()", "Encoding/charset = $charset")
        return charset
    }
}