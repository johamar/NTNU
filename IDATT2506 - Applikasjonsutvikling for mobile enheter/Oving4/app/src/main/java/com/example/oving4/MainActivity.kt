package com.example.oving4

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), ItemListFragment.OnItemSelectedListener {

    val manUnited2008 = listOf(
        Player(
            name = "Edwin van der Sar",
            description = "Rutinerte sisteskanse, sterk på skuddstopping og rolig med ballen i beina.",
            imageResId = R.drawable.sar
        ),
        Player(
            name = "Wes Brown",
            description = "Hardtarbeidende forsvarsspiller, kjent for taklingsstyrke og lojalitet.",
            imageResId = R.drawable.wes
        ),
        Player(
            name = "Rio Ferdinand",
            description = "Elegant og rask midtstopper med god pasningsfot og lederegenskaper.",
            imageResId = R.drawable.rio
        ),
        Player(
            name = "Nemanja Vidić",
            description = "Aggressiv og kompromissløs forsvarer, sterk i lufta og duellspillet.",
            imageResId = R.drawable.nem
        ),
        Player(
            name = "Patrice Evra",
            description = "Offensiv back med fart og utholdenhet, sterk én-mot-én defensivt.",
            imageResId = R.drawable.evra
        ),
        Player(
            name = "Owen Hargreaves",
            description = "Allsidig midtbanespiller, god på dødballer, løpskraft og defensiv dekning.",
            imageResId = R.drawable.owen
        ),
        Player(
            name = "Paul Scholes",
            description = "Kreativ playmaker med presise pasninger og kjent for langskudd.",
            imageResId = R.drawable.paul
        ),
        Player(
            name = "Michael Carrick",
            description = "Roende midtbaneanker, dyktig på å lese spillet og kontrollere tempoet.",
            imageResId = R.drawable.mike
        ),
        Player(
            name = "Cristiano Ronaldo",
            description = "Lynrask, teknisk og målfarlig ving – vant Ballon d'Or i 2008.",
            imageResId = R.drawable.ron
        ),
        Player(
            name = "Wayne Rooney",
            description = "Arbeidsom spiss med enorm kraft, teknikk og målteft.",
            imageResId = R.drawable.waz
        ),
        Player(
            name = "Carlos Tévez",
            description = "Energisk angriper med driv, styrke og evne til å score viktige mål. 💩",
            imageResId = R.drawable.carl
        )
    )

    private lateinit var detailFragment: ItemDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        detailFragment = supportFragmentManager.findFragmentById(R.id.fragment_detail) as ItemDetailFragment
    }

    override fun onItemSelected(position: Int) {
        detailFragment.showItem(position)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_next -> detailFragment.showNext()
            R.id.menu_prev -> detailFragment.showPrevious()
        }
        return super.onOptionsItemSelected(item)
    }
}