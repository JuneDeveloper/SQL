package com.example.sql

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity : AppCompatActivity() {

    private val db = DBHelper(this,null)
    private val roleList = listOf("Инженер","Мастер","Токарь","Фрезер","Акушер","Окулист","Директор")
    private var role:String? = null

    private lateinit var toolbarTB:Toolbar
    private lateinit var editNameET:EditText
    private lateinit var editPhoneET:EditText
    private lateinit var spinnerRoleSP:Spinner
    private lateinit var saveBTN:Button
    private lateinit var getBTN:Button
    private lateinit var deleteBTN:Button
    private lateinit var outputNameTV:TextView
    private lateinit var outputPhoneTV:TextView
    private lateinit var outputRoleTV:TextView

    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        init()

        spinner()

        saveBTN()

        getBTN()

        deleteBTN()
    }

    private fun deleteBTN() {
        deleteBTN.setOnClickListener {
            db.removeAll()
            outputNameTV.text = ""
            outputPhoneTV.text = ""
            outputRoleTV.text = ""
            Toast.makeText(this, "База данных очищена", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("Range")
    private fun getBTN() {
        getBTN.setOnClickListener {
            val cursor = db.getInfo()
            if (cursor != null && cursor.moveToFirst()) {
                cursor.moveToFirst()
                outputNameTV.append(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ID)) + "\n")
                outputPhoneTV.append(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PHONE)) + "\n" )
                outputRoleTV.append(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ROLE)) + "\n" )
            }
            while (cursor!!.moveToNext()) {
                outputNameTV.append(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ID)) + "\n")
                outputPhoneTV.append(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PHONE)) + "\n" )
                outputRoleTV.append(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ROLE)) + "\n" )
            }
            cursor.close()
        }
    }

    private fun saveBTN() {
        saveBTN.setOnClickListener {
            val name = editNameET.text.toString()
            val phone = editPhoneET.text.toString()
            db.save(name, phone, role.toString())
            Toast.makeText(this,
                "Сотрудник: $name//$phone//$role добавлен в базу данных", Toast.LENGTH_LONG).show()
            editNameET.text.clear()
            editPhoneET.text.clear()
        }
    }

    private fun spinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roleList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRoleSP.adapter = adapter
        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    role = p0?.getItemAtPosition(p2) as String
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        spinnerRoleSP.onItemSelectedListener = itemSelectedListener
    }

    private fun init() {
        editNameET = findViewById(R.id.editNameET)
        editPhoneET = findViewById(R.id.editPhoneET)
        spinnerRoleSP = findViewById(R.id.spinnerRoleSP)
        saveBTN = findViewById(R.id.saveBTN)
        getBTN = findViewById(R.id.getBTN)
        deleteBTN = findViewById(R.id.deleteBTN)
        outputNameTV = findViewById(R.id.outputNameTV)
        outputPhoneTV = findViewById(R.id.outputPhoneTV)
        outputRoleTV = findViewById(R.id.outputRoleTV)
        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.exitItem -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}