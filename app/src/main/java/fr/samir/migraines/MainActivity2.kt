package fr.samir.migraines

//  https://l-art-de-creer-son-application-android.com/comment-gerer-plusieurs-activity-en-kotlin/
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val messageDesc = intent.getStringExtra("EXTRAT_MESSAGE_DESC")
        val  afficheDesc = findViewById<TextView>(R.id.editDesc).apply {
            text = messageDesc
        }

        val messageDate = intent.getStringExtra("EXTRAT_MESSAGE_DATE")
        val affichedate = findViewById<TextView>(R.id.afficheDate).apply {
            text = messageDate
        }

        val messageAins = intent.getStringExtra("EXTRAT_MESSAGE_SpinAins")
        val afficheAins = findViewById<TextView>(R.id.editAins).apply {
            text = messageAins
        }

        val messageItens = intent.getStringExtra("EXTRAT_MESSAGE_R_INTENS")
        val afficheInetens = findViewById<TextView>(R.id.editIntensite).apply {
            text = messageItens
        }

        val Retour = findViewById<Button>(R.id.btnRTN)
        val monIntentRTN : Intent = Intent(this,MainActivity::class.java)

        Retour.setOnClickListener {
            startActivity(monIntentRTN)
        }
    }
}