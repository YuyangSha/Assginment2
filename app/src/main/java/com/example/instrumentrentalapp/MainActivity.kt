package com.example.instrumentrentalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var priceTextView: TextView
    private lateinit var chipGroup: ChipGroup
    private lateinit var nextButton: Button
    private lateinit var borrowButton: Button
    private lateinit var creditEditText: EditText
    private var currentIndex = 0

    // 初始化乐器列表
    private val instruments = listOf(
        Instrument("Guitar", 100, 5.0f, "Electric"),
        Instrument("Drums", 150, 4.5f, "Acoustic"),
        Instrument("Keyboard", 120, 4.8f, "Electric")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化视图
        nameTextView = findViewById(R.id.text_name)
        ratingBar = findViewById(R.id.rating_bar)
        priceTextView = findViewById(R.id.text_price)
        chipGroup = findViewById(R.id.chip_group)
        nextButton = findViewById(R.id.button_next)
        borrowButton = findViewById(R.id.button_borrow)
        creditEditText = findViewById(R.id.edit_credit)

        // 显示第一个乐器
        displayInstrument()

        // "Next" 按钮用于展示下一个乐器
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % instruments.size
            displayInstrument()
        }

        // "Borrow" 按钮用于跳转到 BorrowActivity
        borrowButton.setOnClickListener {
            val inputCredits = creditEditText.text.toString().toIntOrNull()

            // 检查输入是否有效
            if (inputCredits == null || inputCredits < 0) {
                Toast.makeText(this, "Please enter a valid number of credits.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 检查是否有足够的积分
            if (inputCredits >= instruments[currentIndex].price) {
                val intent = Intent(this, BorrowActivity::class.java)
                intent.putExtra("instrument", instruments[currentIndex])
                startActivity(intent)
            } else {
                Toast.makeText(this, "Not enough credits to borrow this instrument.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 显示当前乐器的详细信息
    private fun displayInstrument() {
        val instrument = instruments[currentIndex]
        nameTextView.text = instrument.name
        ratingBar.rating = instrument.rating
        priceTextView.text = "Price: ${instrument.price} credits"

        // 更新 ChipGroup 中的 Chip 文本以凸显乐器的特点
        val electricChip = findViewById<Chip>(R.id.chip_electric)
        val acousticChip = findViewById<Chip>(R.id.chip_acoustic)

        // 根据乐器类型更新 ChipGroup
        chipGroup.clearCheck() // 清除之前选中的 Chip
        when (instrument.type) {
            "Electric" -> {
                chipGroup.check(R.id.chip_electric)
                electricChip.text = "Electric: Perfect for Rock"
                acousticChip.text = "Acoustic: Not Available"
            }
            "Acoustic" -> {
                chipGroup.check(R.id.chip_acoustic)
                acousticChip.text = "Acoustic: Great for Jazz"
                electricChip.text = "Electric: Not Available"
            }
        }
    }
}

