package no.eventu.assignment7.managers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {

        const val DATABASE_NAME = "movieDatabase"
        const val DATABASE_VERSION = 1

        const val ID = "_id"

        //Director
        const val TABLE_DIRECTOR = "DIRECTOR"
        const val DIRECTOR_NAME = "name"
        const val DIRECTOR_ID = "director_id"

        //Movies
        const val TABLE_MOVIE = "MOVIE"
        const val MOVIE_TITLE = "title"
        const val MOVIE_ID = "movie_id"

        //Actors
        const val TABLE_ACTOR = "ACTOR"
        const val ACTOR_NAME = "name"
        const val ACTOR_ID = "actor_id"


        const val TABLE_DIRECTOR_MOVIE = "DIRECTOR_MOVIE"
        const val TABLE_ACTOR_MOVE = "ACTOR_MOVIE"

        val JOIN_DIRECTOR_MOVIE = arrayOf(
            "$TABLE_DIRECTOR.$ID=$TABLE_DIRECTOR_MOVIE.$DIRECTOR_ID",
            "$TABLE_MOVIE.$ID=$TABLE_DIRECTOR_MOVIE.$MOVIE_ID"
        )

        val JOIN_ACTOR_MOVIE = arrayOf(
            "$TABLE_ACTOR.$ID=$TABLE_ACTOR_MOVE.$ACTOR_ID",
            "$TABLE_MOVIE.$ID=$TABLE_ACTOR_MOVE.$MOVIE_ID"
        )




    }


    /**
     * Create the tables
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """create table $TABLE_DIRECTOR(
            $ID integer primary key autoincrement,
            $DIRECTOR_NAME text unique not null
            );
            """.trimIndent()
        )
        db.execSQL(
            """create table $TABLE_ACTOR(
            $ID integer primary key autoincrement,
            $ACTOR_NAME text unique not null
            );""".trimIndent()
        )
        db.execSQL(
            """create table $TABLE_MOVIE(
            $ID integer primary key autoincrement,
            $MOVIE_TITLE text unique not null
            );""".trimIndent()
        )
        db.execSQL(
            """create table $TABLE_DIRECTOR_MOVIE(
            $ID integer primary key autoincrement,
            $MOVIE_ID numeric,
            $DIRECTOR_ID numeric,
            FOREIGN KEY($DIRECTOR_ID) REFERENCES $TABLE_DIRECTOR($ID),
            FOREIGN KEY($MOVIE_ID) REFERENCES $TABLE_MOVIE($ID)
            );""".trimIndent()
        )
        db.execSQL(
            """create table $TABLE_ACTOR_MOVE(
            $ID integer primary key autoincrement,
            $MOVIE_ID numeric,
            $ACTOR_ID numeric,
            FOREIGN KEY($ACTOR_ID) REFERENCES $TABLE_ACTOR($ID),
            FOREIGN KEY($MOVIE_ID) REFERENCES $TABLE_MOVIE($ID)
            );""".trimIndent()
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}
