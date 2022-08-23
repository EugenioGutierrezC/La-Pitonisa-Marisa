package com.example.lapitonisamarisa.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lapitonisamarisa.data.model.HoroscopeSignsModel
import com.example.lapitonisamarisa.databinding.FragmentZodiacoBinding
import com.example.lapitonisamarisa.ui.adapter.HoroscopeCardsAdapter
import com.example.lapitonisamarisa.utils.HoroscopeDataSet.createDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private var _binding: FragmentZodiacoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HoroscopeCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZodiacoBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = GridLayoutManager(context, 3)
        adapter = HoroscopeCardsAdapter(
            createDataSet(context),
            HoroscopeCardsAdapter.OnClickListener { sign -> goHoroscopeResultWithArgs(sign) }
        )
        binding.recycler.adapter = adapter
    }

    private fun goHoroscopeResultWithArgs(sign: HoroscopeSignsModel) {
        val args = HoroscopeFragmentDirections.actionZodiacoFragmentToZodiacResultFragment2(
            sign.signName,
            sign.url.toString()
        )
        Navigation.findNavController(binding.root).navigate(args)
    }
}