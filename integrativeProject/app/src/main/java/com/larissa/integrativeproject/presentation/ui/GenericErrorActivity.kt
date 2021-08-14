package com.larissa.integrativeproject.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.R
import com.larissa.integrativeproject.databinding.ActivityGenericErrorBinding
import com.larissa.integrativeproject.databinding.ActivityMovieDetailsBinding
import com.larissa.integrativeproject.presentation.adapter.CastAdapter
import com.larissa.integrativeproject.presentation.adapter.GenreDetailsAdapter
import com.larissa.integrativeproject.presentation.viewmodel.MovieDetailsViewModel

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
