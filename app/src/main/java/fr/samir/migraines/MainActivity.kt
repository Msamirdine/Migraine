package fr.samir.migraines

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import fr.samir.migraines.databinding.ActivityMainBinding
import java.util.*


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

        val Date = findViewById<Button>(R.id.btndate)
        val SelctDate = findViewById<TextView>(R.id.dateCrise)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        "Recup la date acctuel par defaut "
        SelctDate.setText("$day"+ "/" + (month +1) + "/"  + "$year")

        Date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                SelctDate.setText(""+ dayOfMonth +  "/" + (monthOfYear +1) + "/"  + year)
            }, year, month, day)
            dpd.show()
        }

        //Gerer les intensités
        val rg =findViewById<RadioGroup>(R.id.radioGroup)

        rg.setOnCheckedChangeListener{group, isChecked ->
            if (isChecked == R.id.radioButton_Modérée)
            Toast.makeText(this,isChecked.toString(),Toast.LENGTH_SHORT).show()

            if (isChecked == R.id.radioButton_intense)
                Toast.makeText(this,isChecked.toString(),Toast.LENGTH_SHORT).show()

            if (isChecked == R.id.radioButton_TresIntense)
                Toast.makeText(this,isChecked.toString(),Toast.LENGTH_SHORT).show()

            if (isChecked == R.id.radioButton_insupportable)
                Toast.makeText(this,isChecked.toString(),Toast.LENGTH_SHORT).show()
        }


        // Gerer les types de medocs
        val AINS = resources.getStringArray(R.array.itmAINS)
        val TRIPTAN = resources.getStringArray(R.array.itmTRIPTAN)
        val TDF = resources.getStringArray(R.array.itmTDF)

        val spinAins = findViewById<Spinner>(R.id.spinAins)
        val spinTrip = findViewById<Spinner>(R.id.spinTrip)
        val spinTDF = findViewById<Spinner>(R.id.spinTDF)

        if (spinAins !=null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, AINS)
            spinAins.adapter = adapter
            spinAins.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, AINS[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent:AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        }

        if (spinTrip !=null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, TRIPTAN)
            spinTrip.adapter = adapter
            spinTrip.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, TRIPTAN[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent:AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        if (spinTDF !=null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, TDF)
            spinTDF.adapter = adapter
            spinTDF.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, AINS[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent:AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        //valid
        val btnValid = findViewById<Button>(R.id.btnValid)
        //val intent = Intent(this, MainActivity2::class.java)

        btnValid.setOnClickListener {
            //startActivity(intent)
            callActivity()
        }
    }
    private fun callActivity() {
        val editDesc = findViewById<EditText>(R.id.desc)
        val editDate = findViewById<TextView>(R.id.dateCrise)
        val editAins = findViewById<Spinner>(R.id.spinAins)
        val editIntens = findViewById<RadioGroup>(R.id.radioGroup)

        val messageDesc = editDesc.text.toString()
        val messageDate = editDate.text.toString()
        val messageAins = editAins.onItemSelectedListener.toString()
        val  messageIntens = editIntens.checkedRadioButtonId.toString()

        val intent = Intent(this, MainActivity2::class.java).also {
            it.putExtra("EXTRAT_MESSAGE_DESC",messageDesc)
            it.putExtra("EXTRAT_MESSAGE_DATE",messageDate)
            it.putExtra("EXTRAT_MESSAGE_SpinAins",messageAins)
            it.putExtra("EXTRAT_MESSAGE_R_INTENS",messageIntens)
            startActivity(it)
        }
    }
}
