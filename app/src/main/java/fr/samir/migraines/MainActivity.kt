package fr.samir.migraines

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fr.samir.migraines.databinding.ActivityMainBinding
import java.util.*

//Sauv page "PG" quand on quite l'appli
const val PREF_DATE ="Date"
const val PREF_INTENS = "intensité"
const val PREF_AINS = "ains"
const val PREF_TRIP = "triptan"
const val PREF_TDF = "traitement de fond"
const val PREF_OBSERV = "observation"
const val PREF_NAME = "migraine_prefs"

class MainActivity : AppCompatActivity() {

    //new
    private lateinit var binding: ActivityMainBinding
    //new fin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //new
        binding = ActivityMainBinding.inflate(layoutInflater)
        //new fin
        setContentView(R.layout.activity_main)
        //edit ob
        val observ = findViewById<EditText>(R.id.desc)

        //date + calendrier
        val date = findViewById<Button>(R.id.btndate)
        val selctDate = findViewById<TextView>(R.id.dateCrise)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        //Recup date acctuel par defaut
        selctDate.setText("$day" + "/" + (month + 1) + "/" + "$year")
        //Fin recup date acctuel
        //Afficher calendrier
        date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    selctDate.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        // fin date + calendrier

        // Gerer les types de medocs
        val ains = resources.getStringArray(R.array.itmAINS)
        val triptan = resources.getStringArray(R.array.itmTRIPTAN)
        val tdf = resources.getStringArray(R.array.itmTDF)
        //liste
        val spinAins = findViewById<Spinner>(R.id.spinAins)
        val spinTrip = findViewById<Spinner>(R.id.spinTrip)
        val spinTDF = findViewById<Spinner>(R.id.spinTDF)

        if (spinAins !=null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, ains)
            spinAins.adapter = adapter
            spinAins.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, ains[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent:AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        }
        if (spinTrip !=null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, triptan)
            spinTrip.adapter = adapter
            spinTrip.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, triptan[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent:AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        if (spinTDF !=null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, tdf)
            spinTDF.adapter = adapter
            spinTDF.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, ains[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent:AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        //Intensité btn
        val  afficheIntensite = findViewById<TextView>(R.id.txtIntens)
        afficheIntensite.setText("Aucun")
        val btnIntense = findViewById<Button>(R.id.btnIntens)
        btnIntense.setOnClickListener {
            intensite()
        }
        //valid
        val btnValid = findViewById<Button>(R.id.btnValid)
        btnValid.setOnClickListener {
            callActivity()
        }

        //Déclaration d'une variable pour Sauvegarder mon activity
        //Observation
        val observPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val observPreference = observPreferences?.getString(PREF_OBSERV, "Valeur Par Defaut ")
        observ.setText(observPreference)

        //Intensité
        val intensPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val intensPreference = intensPreferences?.getString(PREF_INTENS, "Aucune")
        afficheIntensite.setText(intensPreference)

        //Ains
        val ainsPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val ainsPreference = ainsPreferences?.getString(PREF_AINS, "")
        val adapterAins = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, ains)
        spinAins.adapter = adapterAins
        val spinnerPositionAins: Int = adapterAins.getPosition(ainsPreference)
        spinAins.setSelection(spinnerPositionAins)

        //Trip
        val tripPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val tripPreference = tripPreferences?.getString(PREF_TRIP, "")
        val adapterTrip = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, triptan)
        spinTrip.adapter = adapterTrip
        val spinnerPositionTrip: Int = adapterTrip.getPosition(tripPreference)
        spinTrip.setSelection(spinnerPositionTrip)

        //Tdf
        val tdfPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val tdfPreference = tdfPreferences?.getString(PREF_TDF, "")
        val adapterTdf = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, tdf)
        spinTDF.adapter = adapterTdf
        val spinnerPositionTdf: Int = adapterTdf.getPosition(tdfPreference)
        spinTDF.setSelection(spinnerPositionTdf)
        // Fin déclaration d'une variable pour Sauvegarder mon activity


    }
    //Gérer les intensités
    private lateinit var selectIntense:String
    private var nivIndex:Int = 0
    val maListeIntensite = arrayOf("Modérée", "Intense", "Très intense", "Insuportable")

    private fun intensite(){
        val  afficheIntensite = findViewById<TextView>(R.id.txtIntens)
        selectIntense = maListeIntensite[nivIndex]
        MaterialAlertDialogBuilder(this)
            .setTitle("Choisir un niveau")
            .setSingleChoiceItems(maListeIntensite, nivIndex){ dialog_, which ->
                nivIndex = which
                selectIntense = maListeIntensite[which]
            }
            .setPositiveButton("Ok") { dialog_, which ->
                Toast.makeText(this, "$selectIntense Selected", Toast.LENGTH_SHORT).show()
                afficheIntensite.setText("$selectIntense")
            }
            .setNegativeButton("Non") {dialog_, which ->
                dialog_.dismiss()
            }.show()
    }

    //Envoyé vers new Activity
    private fun callActivity() {
        //Description
        val editDesc = findViewById<EditText>(R.id.desc)
        val messageDesc = editDesc.text.toString()
        //Date
        val editDate = findViewById<TextView>(R.id.dateCrise)
        val messageDate = editDate.text.toString()
        //Ains
        val editAins = findViewById<Spinner>(R.id.spinAins)
        val messageAins = editAins.selectedItem.toString()
        //Triptan
        val editTrip = findViewById<Spinner>(R.id.spinTrip)
        val messageTrip = editTrip.selectedItem.toString()
        //Traitement de fond
        val editTdf = findViewById<Spinner>(R.id.spinTDF)
        val messageTdf = editTdf.selectedItem.toString()
        //Intensité
        val editIntens = findViewById<TextView>(R.id.txtIntens)
        val messageIntens = editIntens.text.toString()

        //Variable Sauvegarder pg
        val observPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val intensPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        //val datePreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val ainsPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val tripPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val tdfPreferences : SharedPreferences? = this?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val intent = Intent(this, MainActivity2::class.java).also {
            it.putExtra("EXTRA_MESSAGE_DESC",messageDesc)
            it.putExtra("EXTRA_MESSAGE_DATE",messageDate)
            it.putExtra("EXTRA_MESSAGE_SpinAINS",messageAins)
            it.putExtra("EXTRA_MESSAGE_SpinTRIP",messageTrip)
            it.putExtra("EXTRA_MESSAGE_SpinTDF",messageTdf)
            it.putExtra("EXTRA_MESSAGE_R_INTENS",messageIntens)
            startActivity(it)
        }
        //utilisation d'objet pour Sauvegarder Pages
        //Observation
        if (observPreferences != null) {
            with (observPreferences.edit()) {
                putString(PREF_OBSERV, messageDesc)
                apply()
            }
        }
        //Intensité
        if (intensPreferences != null) {
            with (intensPreferences.edit()) {
                putString(PREF_INTENS, messageIntens)
                apply()
            }
        }
        //Ains
        if (ainsPreferences != null) {
            with (ainsPreferences.edit()) {
                putString(PREF_AINS, messageAins)
                apply()
            }
        }
       //Trip
        if (tripPreferences != null) {
            with (tripPreferences.edit()) {
                putString(PREF_TRIP, messageTrip)
                apply()
            }
        }
        //Tdf
        if (tdfPreferences != null) {
            with (tdfPreferences.edit()) {
                putString(PREF_TDF, messageTdf)
                apply()
            }
        }
    }
}