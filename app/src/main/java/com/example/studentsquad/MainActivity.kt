package com.example.studentsquad

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        val buttonPhone = findViewById<Button>(R.id.buttonPhone)
        val buttonShape = findViewById<Button>(R.id.buttonShare)

        buttonOpen.setOnClickListener {
            val text = editText.text.toString().trim()

            if (text.isEmpty()){
                Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key_text", editText.text.toString())
            startActivity(intent)
        }

        buttonPhone.setOnClickListener {
            val phone = editText.text.toString().trim()

            if (phone.isEmpty()){
                Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException){
                Toast.makeText(this, "Приложение для звонков не найдено", Toast.LENGTH_SHORT).show()
            }
        }

        buttonShape.setOnClickListener {
            val text = editText.text.toString()

            if (text.isBlank()){
                Toast.makeText(this, "Введите текс", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            startActivity(Intent.createChooser(sendIntent, "Поделиться через..."))
        }
    }
}