package com.example.oving7.managers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.oving7.data.Movie

const val DB_NAME = "MovieDB"
const val DB_VERSION = 1
const val TABLE_MOVIE = "MOVIE"
const val TABLE_ACTOR = "ACTOR"
const val TABLE_MOVIE_ACTOR = "MOVIE_ACTOR"
const val COL_ID = "_id"
const val COL_TITLE = "title"
const val COL_DIRECTOR = "director"
const val COL_NAME = "name"
const val COL_MOVIE_ID = "movie_id"
const val COL_ACTOR_ID = "actor_id"

class MovieDatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_MOVIE (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_TITLE TEXT UNIQUE,
                $COL_DIRECTOR TEXT
            )
        """)
        db.execSQL("""
            CREATE TABLE $TABLE_ACTOR (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT UNIQUE
            )
        """)
        db.execSQL("""
            CREATE TABLE $TABLE_MOVIE_ACTOR (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_MOVIE_ID INTEGER,
                $COL_ACTOR_ID INTEGER,
                FOREIGN KEY($COL_MOVIE_ID) REFERENCES $TABLE_MOVIE($COL_ID),
                FOREIGN KEY($COL_ACTOR_ID) REFERENCES $TABLE_ACTOR($COL_ID),
                UNIQUE($COL_MOVIE_ID, $COL_ACTOR_ID)
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MOVIE_ACTOR")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ACTOR")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MOVIE")
        onCreate(db)
    }

    fun insertMovie(movie: Movie) {
        writableDatabase.use { db ->
            val movieValues = ContentValues().apply {
                put(COL_TITLE, movie.title)
                put(COL_DIRECTOR, movie.director)
            }
            val movieId = db.insertWithOnConflict(TABLE_MOVIE, null, movieValues, SQLiteDatabase.CONFLICT_IGNORE)

            for (actor in movie.actors) {
                val actorValues = ContentValues().apply { put(COL_NAME, actor) }
                val actorId = db.insertWithOnConflict(TABLE_ACTOR, null, actorValues, SQLiteDatabase.CONFLICT_IGNORE)

                db.execSQL("""
                    INSERT INTO $TABLE_MOVIE_ACTOR ($COL_MOVIE_ID, $COL_ACTOR_ID)
                    SELECT m.$COL_ID, a.$COL_ID FROM $TABLE_MOVIE m, $TABLE_ACTOR a
                    WHERE m.$COL_TITLE=? AND a.$COL_NAME=?
                        AND NOT EXISTS (
                            SELECT 1 FROM $TABLE_MOVIE_ACTOR ma
                            WHERE ma.$COL_MOVIE_ID = m.$COL_ID AND ma.$COL_ACTOR_ID = a.$COL_ID
                        );
                """, arrayOf(movie.title, actor))
            }
        }
    }

    fun getMoviesByDirector(director: String): List<String> {
        val result = mutableListOf<String>()
        readableDatabase.use { db ->
            val cursor = db.query(
                TABLE_MOVIE,
                arrayOf(COL_TITLE),
                "$COL_DIRECTOR=?",
                arrayOf(director),
                null, null, null
            )
            while (cursor.moveToNext()) {
                result.add(cursor.getString(0))
            }
            cursor.close()
        }
        return result
    }

    fun getAllMovies(): List<String> {
        val result = mutableListOf<String>()
        readableDatabase.use { db ->
            val cursor = db.rawQuery("SELECT title, director FROM MOVIE", null)
            while (cursor.moveToNext()) {
                result.add("${cursor.getString(0)} (${cursor.getString(1)})")
            }
            cursor.close()
        }
        return result
    }

    fun getAllDirectors(): List<String> {
        val result = mutableListOf<String>()
        readableDatabase.use { db ->
            val cursor = db.rawQuery("SELECT DISTINCT director FROM MOVIE", null)
            while (cursor.moveToNext()) {
                result.add(cursor.getString(0))
            }
            cursor.close()
        }
        return result
    }

    fun getActorsByMovie(title: String): List<String> {
        val resultSet = linkedSetOf<String>()
        val query = """
        SELECT DISTINCT ACTOR.$COL_NAME FROM $TABLE_ACTOR AS ACTOR
        JOIN $TABLE_MOVIE_ACTOR AS MA ON ACTOR.$COL_ID = MA.$COL_ACTOR_ID
        JOIN $TABLE_MOVIE AS MOV ON MOV.$COL_ID = MA.$COL_MOVIE_ID
        WHERE MOV.$COL_TITLE = ?
    """
        readableDatabase.use { db ->
            db.rawQuery(query, arrayOf(title)).use { cursor ->
                while (cursor.moveToNext()) {
                    resultSet.add(cursor.getString(0).trim())
                }
            }
        }
        return resultSet.toList()
    }

    fun getMoviesByActor(actor: String): List<String> {
        val resultSet = linkedSetOf<String>()
        val query = """
        SELECT DISTINCT MOVIE.$COL_TITLE FROM $TABLE_MOVIE AS MOVIE
        JOIN $TABLE_MOVIE_ACTOR AS MA ON MOVIE.$COL_ID = MA.$COL_MOVIE_ID
        JOIN $TABLE_ACTOR AS ACTOR ON ACTOR.$COL_ID = MA.$COL_ACTOR_ID
        WHERE ACTOR.$COL_NAME = ?
    """
        readableDatabase.use { db ->
            db.rawQuery(query, arrayOf(actor)).use { cursor ->
                while (cursor.moveToNext()) {
                    resultSet.add(cursor.getString(0).trim())
                }
            }
        }
        return resultSet.toList()
    }
}