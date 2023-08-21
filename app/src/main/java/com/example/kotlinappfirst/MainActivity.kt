package com.example.kotlinappfirst

import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinappfirst.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemsElement: List<String>
    private lateinit var itemsPosition: List<String>


    private fun validateForm(): Boolean {
        val element = binding.tilElement.editText?.text.toString()
        val position = binding.tilPosition.editText?.text.toString()
        val tooltipText = binding.tilTooltipText.editText?.text.toString()
        val textSize = binding.tilTextSize.editText?.text.toString()
        val padding = binding.tilPadding.editText?.text.toString()
        val cornerRadius = binding.tilCornerRadius.editText?.text.toString()
        val tooltipWidth = binding.tilTooltipWidth.editText?.text.toString()
        val arrowHeight = binding.tilArrowHeight.editText?.text.toString()
        val arrowWidth = binding.tilArrowWidth.editText?.text.toString()
        val backgroundColor = binding.btnBackgroundColor.text
        val textColor = binding.btnTextColor.text

        if (element !in itemsElement || position !in itemsPosition || tooltipText.isBlank() ||
            textSize.isBlank() || padding.isBlank() || cornerRadius.isBlank() ||
            tooltipWidth.isBlank() || arrowHeight.isBlank() || arrowWidth.isBlank() ||
            !backgroundColor.startsWith('#') || !textColor.startsWith('#')
        ) {
            return false
        }

        return true
    }
}