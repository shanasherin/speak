package com.example.speak

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private val Request_code=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        voice.setOnClickListener {
            speak()
    }
}
    private fun speak()
    {

        val a= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        a.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        a.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        a.putExtra(RecognizerIntent.EXTRA_PROMPT,"speak something")

        try {

            startActivityForResult(a,Request_code)
        }catch (e: Exception)
        {
            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            Request_code->
            {
                if (resultCode== Activity.RESULT_OK &&null!=data)
                {
                    val result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    text1.text=result[0]
                }
            }
        }
    }
}
