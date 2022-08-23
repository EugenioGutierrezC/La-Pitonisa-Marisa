package com.example.lapitonisamarisa.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.lapitonisamarisa.R
import com.example.lapitonisamarisa.databinding.FragmentZodiacResultBinding
import com.example.lapitonisamarisa.ui.viewmodel.HoroscopeResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeResultFragment : Fragment() {

    private var _binding: FragmentZodiacResultBinding? = null
    private val binding get() = _binding!!

    private val args: HoroscopeResultFragmentArgs by navArgs()

    private val horoscopeResultViewModel: HoroscopeResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZodiacResultBinding.inflate(inflater, container, false)

        loadScreen()
        executeHoroscopeService()

        //TODO replace progressbar for shimmer https://github.com/facebook/shimmer-android
        horoscopeResultViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.loadingBar.isVisible = it
        })

        horoscopeResultViewModel.horoscopeLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { apiResponse ->
                binding.tvSignExplanation.text = apiResponse.horoscope
            }
        })

        horoscopeResultViewModel.popUpLaunch.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                /**
                 * TODO this alertDialog will be called several times in this app, must be Util function.
                 * TODO Depending of the String we recieve it will be one type or other error
                 */

                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.service_error))
                    .setMessage(getString(R.string.service_error_msg))
                    .setNegativeButton(getString(android.R.string.cancel), null)
                    .setPositiveButton(getString(R.string.try_again)) { _, _ ->
                        executeHoroscopeService()
                    }
                    .create().show()
            }
        })

        return binding.root
    }

    private fun loadScreen() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.sign
        Glide.with(binding.cvZodiac.context).load(args.url).into(binding.ivSigno)
    }

    private fun executeHoroscopeService() {
        horoscopeResultViewModel.getHoroscope(args.sign)
    }
}