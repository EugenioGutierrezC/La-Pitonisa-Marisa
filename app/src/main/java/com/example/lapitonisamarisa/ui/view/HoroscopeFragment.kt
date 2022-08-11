package com.example.lapitonisamarisa.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lapitonisamarisa.R
import com.example.lapitonisamarisa.data.model.HoroscopeSignsModel
import com.example.lapitonisamarisa.databinding.FragmentZodiacoBinding
import com.example.lapitonisamarisa.ui.adapter.HoroscopeCardsAdapter
import com.example.lapitonisamarisa.ui.viewmodel.HoroscopeViewModel
import com.example.lapitonisamarisa.utils.HoroscopeDataSet.createDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private var _binding: FragmentZodiacoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HoroscopeCardsAdapter

    private val horoscopeViewModel: HoroscopeViewModel by viewModels()

    private lateinit var lastHoroscopeSelected: HoroscopeSignsModel

    //TODO Refactor those liveParams will go to HorosocopeResultFragment, aslo executeHoroscopeService.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZodiacoBinding.inflate(inflater, container, false)
        initRecyclerView()

        horoscopeViewModel.horoscopeLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                val a = HoroscopeFragmentDirections.actionZodiacoFragmentToZodiacResultFragment2(
                    it.horoscope,
                    lastHoroscopeSelected.signName,
                    lastHoroscopeSelected.url.toString()
                )
                Navigation.findNavController(binding.root).navigate(a)
            }
        })

        horoscopeViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.loadingBar.isVisible = it
        })

        horoscopeViewModel.popUpLaunch.observe(viewLifecycleOwner, Observer {
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
                        executeHoroscopeService(lastHoroscopeSelected)
                    }
                    .create().show()
            }
        })

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = GridLayoutManager(context, 3)
        adapter = HoroscopeCardsAdapter(
            createDataSet(context),
            HoroscopeCardsAdapter.OnClickListener { sign -> executeHoroscopeService(sign) }
        )
        binding.recycler.adapter = adapter
    }

    private fun executeHoroscopeService(sign: HoroscopeSignsModel) {
        lastHoroscopeSelected = sign
        horoscopeViewModel.getHoroscope(sign.signName)
    }
}