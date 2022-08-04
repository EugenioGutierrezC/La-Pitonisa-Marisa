package com.example.lapitonisamarisa.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.lapitonisamarisa.databinding.FragmentZodiacResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeResultFragment : Fragment() {

    private var _binding: FragmentZodiacResultBinding? = null
    private val binding get() = _binding!!

    private val args: HoroscopeResultFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentZodiacResultBinding.inflate(inflater, container, false)
        loadScreen()
        return binding.root
    }

    private fun loadScreen() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.sign
        binding.tvSignExplanation.text = args.horoscope
        Glide.with(binding.cvZodiac.context).load(args.url).into(binding.ivSigno)
    }
}