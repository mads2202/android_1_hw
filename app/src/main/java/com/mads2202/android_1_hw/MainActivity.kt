package com.mads2202.android_1_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.mads2202.android_1_hw.services.CalculatorService

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val CALCULATIONS = "calculations"
    }

    private lateinit var mButtonZero: Button
    private lateinit var mButton1: Button
    private lateinit var mButton2: Button
    private lateinit var mButton3: Button
    private lateinit var mButton4: Button
    private lateinit var mButton5: Button
    private lateinit var mButton6: Button
    private lateinit var mButton7: Button
    private lateinit var mButton8: Button
    private lateinit var mButton9: Button
    private lateinit var mButtonEquals: Button
    private lateinit var mButtonDivision: Button
    private lateinit var mButtonMultiplication: Button
    private lateinit var mButtonMinus: Button
    private lateinit var mButtonPlus: Button
    private lateinit var mButtonClear: Button
    private lateinit var mButtonDelete: Button
    private lateinit var mButtonPoint: Button
    private lateinit var mCalculatorTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCalculatorViews()
        mCalculatorTextView.text = savedInstanceState?.getString(CALCULATIONS)

    }

    private fun initCalculatorViews() {
        mButtonZero = findViewById(R.id.button_zero)
        mButton1 = findViewById(R.id.button_one)
        mButton2 = findViewById(R.id.button_two)
        mButton3 = findViewById(R.id.button_three)
        mButton4 = findViewById(R.id.button_four)
        mButton5 = findViewById(R.id.button_five)
        mButton6 = findViewById(R.id.button_six)
        mButton7 = findViewById(R.id.button_seven)
        mButton8 = findViewById(R.id.button_eight)
        mButton9 = findViewById(R.id.button_nine)
        mButtonEquals = findViewById(R.id.button_equals)
        mButtonDivision = findViewById(R.id.button_division)
        mButtonMultiplication = findViewById(R.id.button_multiplication)
        mButtonMinus = findViewById(R.id.button_minus)
        mButtonPlus = findViewById(R.id.button_plus)
        mButtonClear = findViewById(R.id.button_clear)
        mButtonDelete = findViewById(R.id.button_delete)
        mButtonPoint = findViewById(R.id.button_point)
        mCalculatorTextView = findViewById(R.id.text_view_calculator)
        setButtonsOnClickListener()

    }

    private fun setButtonsOnClickListener() {
        mButtonZero.setOnClickListener(this)
        mButtonZero.setOnClickListener(this)
        mButton1.setOnClickListener(this)
        mButton2.setOnClickListener(this)
        mButton3.setOnClickListener(this)
        mButton4.setOnClickListener(this)
        mButton5.setOnClickListener(this)
        mButton6.setOnClickListener(this)
        mButton7.setOnClickListener(this)
        mButton8.setOnClickListener(this)
        mButton9.setOnClickListener(this)
        mButtonEquals.setOnClickListener {
            mCalculatorTextView.text = CalculatorService(mCalculatorTextView.text.toString()).startCalculations()
        }
        mButtonDivision.setOnClickListener(this)
        mButtonMultiplication.setOnClickListener(this)
        mButtonMinus.setOnClickListener(this)
        mButtonPlus.setOnClickListener(this)
        mButtonClear.setOnClickListener {
            mCalculatorTextView.text = ""
        }
        mButtonDelete.setOnClickListener {
            mCalculatorTextView.text = mCalculatorTextView.text.dropLast(1)
        }
        mButtonPoint.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        var calculations: String = mCalculatorTextView.text.toString()
        mCalculatorTextView.text = calculations.plus((v as Button).text)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(CALCULATIONS, mCalculatorTextView.text.toString())
    }

}