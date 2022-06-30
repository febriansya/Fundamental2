package com.example.fundamental2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.w3c.dom.Text

class MyActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var toActivity: Button
    private lateinit var texter: TextView


    private val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
//        cek apakah kondisi kembalian dalam keadaan tidak nul
        if (result.resultCode == MainActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MainActivity.EXTRA_SELECTED_VALUE, 0)
            texter.text = "hasil $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        toActivity = findViewById(R.id.button)
        texter = findViewById(R.id.result)
        toActivity.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.button) {
            val intent = Intent(this@MyActivity, MainActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}