package com.example.centeralperk.presentation.fragment.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import com.example.centeralperk.R
import com.example.centeralperk.databinding.FragmentDialogLoaderBinding
import javax.inject.Inject

class DialogLoaderFragment @Inject constructor() : DialogFragment() {

    // Data binding component
    private lateinit var binding: FragmentDialogLoaderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Data binding
        binding = FragmentDialogLoaderBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Background transparent
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Dialog box outside click disable
        dialog?.setCanceledOnTouchOutside(false)

        // Image rotate
        imageRotate()
    }

    /**
     * Infinite Image rotate
     */
    @SuppressLint("ResourceType")
    private fun imageRotate() {

        val imageRotate = AnimationUtils.loadAnimation(context, R.drawable.image_rotate)
        binding.ivLoader.startAnimation(imageRotate)
    }
}