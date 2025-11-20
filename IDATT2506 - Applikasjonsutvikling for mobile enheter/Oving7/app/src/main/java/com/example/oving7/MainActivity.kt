package com.example.oving7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.graphics.Color
import androidx.preference.PreferenceManager
import com.example.oving7.data.Movie
import com.example.oving7.managers.MovieDatabaseManager
import com.example.oving7.managers.FileManager
import com.example.oving7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: MovieDatabaseManager
    private lateinit var fileManager: FileManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MovieDatabaseManager(this)
        fileManager = FileManager(this)

        // Les filmdata fra res/raw/movies.txt
        val lines = fileManager.readRawFile(R.raw.movies)
        val movies = lines.map {
            val parts = it.split(";")
            Movie(parts[0], parts[1], parts[2].split(","))
        }

        for (movie in movies) db.insertMovie(movie)

        fileManager.writeToInternalFile("movies_copy1.txt", lines.joinToString("\n"))

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun showResults(list: List<String>) {
        val res = StringBuffer("")
        for (s in list) res.append("$s\n")
        binding.textView.text = res.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.query_all_movies -> showResults(db.getAllMovies())
            R.id.query_all_directors -> showResults(db.getAllDirectors())
            R.id.query_movies_nolan -> showResults(db.getMoviesByDirector("Christopher Nolan"))
            R.id.query_actors_inception -> showResults(db.getActorsByMovie("Inception"))
            R.id.query_movies_leo -> showResults(db.getMoviesByActor("Leonardo DiCaprio"))
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        when (prefs.getString("bg_color", "white")) {
            "blue" -> binding.root.setBackgroundColor(Color.BLUE)
            "green" -> binding.root.setBackgroundColor(Color.GREEN)
            else -> binding.root.setBackgroundColor(Color.WHITE)
        }

    }
}