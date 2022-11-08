package no.eventu.assignment7.managers

import android.app.Activity
import android.util.Log
import no.eventu.assignment7.Movie
import no.eventu.assignment7.service.Database
import java.io.*

class FileManager(private val activity: Activity) {
    private val filename: String = "newLocalFile.txt"
    private val dir: File = activity.filesDir
    private val file: File = File(dir, filename)

    //private val FILEPATH = "/res/raw/movies.txt"
    //private val file: File = File(FILEPATH)



    fun readMoviesFromTxt(fileId: Int): ArrayList<Movie>{
        val moviesList = ArrayList<Movie>()

        try {
            val inputStream: InputStream = activity.resources.openRawResource(fileId)
            BufferedReader(InputStreamReader(inputStream)).use {
                reader ->
                //Log.e("yes", fileId.toString())
                var title = reader.readLine()
                var director = reader.readLine()
                var actors = reader.readLine().split(',')
                reader.readLine()
                while (title != null){
                    moviesList.add(Movie(title, director, actors))
                    title = reader.readLine()
                    if (title != null){
                        director = reader.readLine()
                        actors = reader.readLine().split(',')
                    }
                    reader.readLine()
                }
            }

        } catch (e: IOException){
            Log.e("Error reading file: ",e.toString())
        }
        return moviesList
    }

    private fun write(str: String) {
        PrintWriter(file).use { writer ->
            writer.println(str)
        }
    }

    fun readLine(): String? {
        BufferedReader(FileReader(file)).use { reader ->
            return reader.readLine()
        }
    }

    fun writeDbToTextFile(database: Database){
        val string = StringBuffer("")
        //println("print" + database.getDirectorFromMovie("12 Angry men"))
        for (movie in database.allMovies){
            string.append(movie + "\n")
            println("movie: " + movie)
            //val dir = database.getDirectorFromMovie(movie)
            //val actors = database.getActorsFromMovie(title)
            //println("dir: " +dir)
            //println("director: "+ database.getDirectorFromMovie(movie).size)

            //for (director in dir){
            //    string.append(director + "\n")
            //}
            //string.append(dir + "\n")
            for (director in database.getDirectorFromMovie(movie)){
                string.append(director +"\n")
                println("director: " + director)
            }
            for (actor in database.getActorsFromMovie(movie)){
                string.append(actor)
                if (actor != database.getActorsFromMovie(movie).last()){
                    string.append(", ")
                }
            }
            string.append("\n\n")
        }
        write(string.toString())
    }


}














 // ----------- OLD for csv ----------

    /**
     *

    fun csvReader(): List<Movie>{
        val FILEPATH = "/res/raw/movies.csv"
        //val reader =  inputStream.bufferedReader()
        val reader = BufferedReader(FileReader(FILEPATH))
        val header = reader.readLine()
        var movies= ArrayList<Movie>()
        while (reader.readLine() != null){
            val line = reader.readLine().split(',')
            val title = line[0]
            val director = line[1]
            var actors = ArrayList<String>()
            for (i in 2..line.size){
                actors.add(line[i])
            }
            movies.add(Movie(title, director, actors))

        }
        for (movie in movies){
            Log.e("movie", movie.title +" " + movie.director)
        }
        return movies

    }
*/