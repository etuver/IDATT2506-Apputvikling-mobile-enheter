package no.eventu.assignment7.managers

import android.util.Log
import no.eventu.assignment7.Movie
import java.io.BufferedReader
import java.io.FileReader

class FileManager {


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
}