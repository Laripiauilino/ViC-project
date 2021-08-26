package com.larissa.integrativeproject.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.larissa.integrativeproject.R
import com.larissa.integrativeproject.databinding.ActivityGenericErrorBinding

class GenericErrorActivity : AppCompatActivity() {

    private var binding: ActivityGenericErrorBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_error)


        binding = ActivityGenericErrorBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnClose?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnTryAgain?.setOnClickListener {
            onBackPressed()
        }
    }
}
