package com.example.kotlinappfirst

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinappfirst.databinding.ActivityMainBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemsElement: List<String>
    private lateinit var itemsPosition: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        itemsElement = listOf(
            resources.getString(R.string.button_1),
            resources.getString(R.string.button_2),
            resources.getString(R.string.button_3),
            resources.getString(R.string.button_4),
            resources.getString(R.string.button_5),
        )
        val adapterElement = ArrayAdapter(this, R.layout.list_item, itemsElement)
        (binding.tilElement.editText as? AutoCompleteTextView)?.setAdapter(adapterElement)

        itemsPosition = listOf(
            resources.getString(R.string.bottom),
            resources.getString(R.string.top),
            resources.getString(R.string.left),
            resources.getString(R.string.right)
        )
        val adapterPosition = ArrayAdapter(this, R.layout.list_item, itemsPosition)
        (binding.tilPosition.editText as? AutoCompleteTextView)?.setAdapter(adapterPosition)



        binding.btnBackgroundColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle(resources.getString(R.string.background_colour_dialog_title))
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                    ColorEnvelopeListener { envelope, _ ->
                        val color = "#" + envelope.hexCode.toString()
                        binding.btnBackgroundColor.text = color
                        binding.btnTextColor.setBackgroundColor(Color.parseColor(color))
                        binding.btnBackgroundColor.setBackgroundColor(Color.parseColor(color))
                    })
                .setNegativeButton(
                    getString(R.string.cancel)
                ) { dialogInterface, _ -> dialogInterface.dismiss() }
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show()
        }

        binding.btnTextColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle(resources.getString(R.string.text_colour_dialog_title))
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                    ColorEnvelopeListener { envelope, _ ->
                        val color = "#" + envelope.hexCode.toString()
                        binding.btnTextColor.text = color
                        binding.btnTextColor.setTextColor(Color.parseColor(color))
                        binding.btnBackgroundColor.setTextColor(Color.parseColor(color))
                    })
                .setNegativeButton(
                    getString(R.string.cancel)
                ) { dialogInterface, _ -> dialogInterface.dismiss() }
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show()
        }

        binding.btnPreviewTooltip.setOnClickListener {
            nextActivity()
        }

        binding.btnFillDefault.setOnClickListener {
            fillDefault()
        }
    }

    private fun nextActivity() {
        if (validateForm()) {
            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra("element", binding.tilElement.editText?.text.toString())
            intent.putExtra("position", binding.tilPosition.editText?.text.toString())
            intent.putExtra("tooltip_text", binding.tilTooltipText.editText?.text.toString())
            intent.putExtra("text_size", binding.tilTextSize.editText?.text.toString())
            intent.putExtra("padding", binding.tilPadding.editText?.text.toString())
            intent.putExtra("corner_radius", binding.tilCornerRadius.editText?.text.toString())
            intent.putExtra("tooltip_width", binding.tilTooltipWidth.editText?.text.toString())
            intent.putExtra("arrow_height", binding.tilArrowHeight.editText?.text.toString())
            intent.putExtra("arrow_width", binding.tilArrowWidth.editText?.text.toString())
            intent.putExtra("background_color", binding.btnBackgroundColor.text)
            intent.putExtra("text_color", binding.btnTextColor.text)
            startActivity(intent)
        } else {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle(resources.getString(R.string.incomplete_form))
            alertDialogBuilder.setMessage(resources.getString(R.string.incomplete_form_message))
            alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun fillDefault() {
        (binding.tilElement.editText as? MaterialAutoCompleteTextView)?.setText(
            resources.getString(
                R.string.button_1
            ), false
        )
        (binding.tilPosition.editText as? MaterialAutoCompleteTextView)?.setText(
            resources.getString(
                R.string.bottom
            ), false
        )
        binding.tilTooltipText.editText?.setText(resources.getString(R.string.tooltip_text_goes_here))
        binding.tilTextSize.editText?.setText("15")
        binding.tilPadding.editText?.setText("25")
        binding.tilCornerRadius.editText?.setText("15")
        binding.tilTooltipWidth.editText?.setText("300")
        binding.tilArrowHeight.editText?.setText("25")
        binding.tilArrowWidth.editText?.setText("50")
        binding.btnTextColor.text = "#FFFFFF"
        binding.btnTextColor.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTextColor.setTextColor(Color.parseColor("#FFFFFF"))
        binding.btnBackgroundColor.text = "#000000"
        binding.btnBackgroundColor.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnBackgroundColor.setTextColor(Color.parseColor("#FFFFFF"))
    }

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