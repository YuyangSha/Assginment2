package com.example.instrumentrentalapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BorrowActivity : AppCompatActivity() {

    private lateinit var borrowConfirmButton: Button
    private lateinit var cancelButton: Button
    private lateinit var instrumentImageView: ImageView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrow)

        // 获取从 MainActivity 传递过来的乐器信息
        val instrument: Instrument? = intent.getParcelableExtra("instrument")

        // 确保乐器信息不为空
        if (instrument == null) {
            finish() // 没有传递乐器信息时，关闭 Activity
            return
        }

        // 显示乐器信息
        val nameTextView: TextView = findViewById(R.id.text_name_borrow)
        val priceTextView: TextView = findViewById(R.id.text_price_borrow)
        instrumentImageView = findViewById(R.id.instrument_image_view)
        descriptionTextView = findViewById(R.id.instrument_description)

        nameTextView.text = instrument.name
        priceTextView.text = "Price: ${instrument.price} credits"

        // 根据乐器设置图片和描述
        when (instrument.name) {
            "Guitar" -> {
                instrumentImageView.setImageResource(R.drawable.acoustic_guitar)
                descriptionTextView.text = "This acoustic guitar is perfect for beginner and intermediate players, offering rich sound and comfortable handling."
            }
            "Drums" -> {
                instrumentImageView.setImageResource(R.drawable.electric_drum_kit)
                descriptionTextView.text = "These electric drums are ideal for studio recording and live performances, featuring adjustable sensitivity and a compact design."
            }
            "Keyboard" -> {
                instrumentImageView.setImageResource(R.drawable.electric_keyboard)
                descriptionTextView.text = "A modern keyboard with multiple sound options and digital features, ideal for all levels of players."
            }
        }

        // 初始化按钮
        borrowConfirmButton = findViewById(R.id.button_confirm_borrow)
        cancelButton = findViewById(R.id.button_cancel)

        // 点击确认按钮，显示 Toast 并返回主界面
        borrowConfirmButton.setOnClickListener {
            Toast.makeText(this, "${instrument.name} borrowed!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 点击取消按钮，显示 Toast 并返回主界面
        cancelButton.setOnClickListener {
            Toast.makeText(this, "Borrow cancelled", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
