package fr.samir.migraines

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.annotation.MainThread
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Date = findViewById<Button>(R.id.btndate)
        val SelctDate = findViewById<TextView>(R.id.dateCrise)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        Date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                SelctDate.setText("" + dayOfMonth + " / " + month + " / " + year)
            }, year, month, day)
            dpd.show()
        }


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
    }
}