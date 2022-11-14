package fr.samir.migraines

//  https://l-art-de-creer-son-application-android.com/comment-gerer-plusieurs-activity-en-kotlin/
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //retour
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Retour"

        //Recup Description
        val messageDesc = intent.getStringExtra("EXTRA_MESSAGE_DESC")
        val  afficheDesc = findViewById<TextView>(R.id.editDesc).apply {
            text = messageDesc
        }
        //Recup Date
        val messageDate = intent.getStringExtra("EXTRA_MESSAGE_DATE")
        val affichedate = findViewById<TextView>(R.id.afficheDate).apply {
            text = messageDate
        }
        //Recup Ains
        val messageAins = intent.getStringExtra("EXTRA_MESSAGE_SpinAINS")
        val afficheAins = findViewById<TextView>(R.id.editAins).apply {
            text = messageAins
        }
        //Recup Triptan
        val messageTrip = intent.getStringExtra("EXTRA_MESSAGE_SpinTRIP")
        val afficheTrip = findViewById<TextView>(R.id.editTrip).apply {
            text = messageTrip
        }
        //Recup Traitement de fond
        val messageTdf = intent.getStringExtra("EXTRA_MESSAGE_SpinTDF")
        val afficheTdf = findViewById<TextView>(R.id.editTdf).apply {
            text = messageTdf
        }
        //Recup Intensité
        val messageItens = intent.getStringExtra("EXTRA_MESSAGE_R_INTENS")
        val afficheInetens = findViewById<TextView>(R.id.editIntensite).apply {
            text = messageItens
        }

        //Modifier
        //Retour sur le 1er Activity
        val retour =  Intent(this, MainActivity::class.java)
        val btnModif = findViewById<Button>(R.id.btnModif)
        btnModif.setOnClickListener {
            onBackPressed()
        }
    }
}