package com.example.lapitonisamarisa.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lapitonisamarisa.data.model.HoroscopeSignsModel
import com.example.lapitonisamarisa.databinding.CardsZodiacoBinding

class HoroscopeCardsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = CardsZodiacoBinding.bind(view)

    fun render(
        signosZodiacoModel: HoroscopeSignsModel,
        onClickListener: HoroscopeCardsAdapter.OnClickListener
    ) {
        Glide.with(binding.cvZodiac.context).load(signosZodiacoModel.url).into(binding.ivSigno)
        binding.tvSigno.text = signosZodiacoModel.signName
        binding.cvZodiac.setOnClickListener {
            onClickListener.onClick(signosZodiacoModel)
        }
    }
}