package com.example.centeralperk.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.centeralperk.R
import com.example.centeralperk.databinding.ActivityMainBinding
import com.example.centeralperk.presentation.fragment.dialog.DialogLoaderFragment
import com.example.centeralperk.util.AppConstant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /** Data binding component */
    private lateinit var binding: ActivityMainBinding

    /** ViewModel dependency */
    private val viewModel: MainViewModel by viewModels()

    /** Loader dependency */
    @Inject
    lateinit var loader: DialogLoaderFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        /** SetObserver */
        setObserver()
    }

    /**
     * Set observer
     */
    private fun setObserver() {

        lifecycleScope.launch {
            viewModel.loaderState().collect { loaderState ->

                if (loaderState) {
                    /** Showing the loader */
                    loader.show(supportFragmentManager, AppConstant.LOADER_TAG)
                    viewModel.dialogBoxOpenState = true

                } else if (viewModel.dialogBoxOpenState) {

                    /** Dismissing the loader */
                    loader.dismiss()
                }
            }
        }
    }
}