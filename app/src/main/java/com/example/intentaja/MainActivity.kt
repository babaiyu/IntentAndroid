package com.example.intentaja

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView

    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithData: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithData.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dialNumber)
        btnDialPhone.setOnClickListener(this)

        val btnMoveResult: Button = findViewById(R.id.btn_move_activity_result)
        btnMoveResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val moveIntent = Intent(this@MainActivity, MoveWithData::class.java)
                moveIntent.putExtra(MoveWithData.EXTRA_NAME, "Bayu Permana Putra")
                moveIntent.putExtra(MoveWithData.EXTRA_AGE, 19)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Bayu Permana Putra",
                    19,
                    "bayuputra9a@gmail.com",
                    "Kab. Semarang"
                )

                val moveIntent = Intent(this@MainActivity, MoveWithObject::class.java)
                moveIntent.putExtra(MoveWithObject.EXTRA_PERSON, person)
                startActivity(moveIntent)
            }

            R.id.btn_dialNumber -> {
                val phoneNumber = "+6285801874452"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_activity_result -> {
                val moveForResult = Intent(this@MainActivity, MoveResult::class.java)
                startActivityForResult(moveForResult, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveResult.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveResult.EXTRA_SELECTED_VALUE, 0)
                tv_result.text = "Result: $selectedValue"
            }
        }
    }


}
