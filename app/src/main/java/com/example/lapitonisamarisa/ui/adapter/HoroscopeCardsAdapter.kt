package com.example.lapitonisamarisa.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lapitonisamarisa.R
import com.example.lapitonisamarisa.data.model.HoroscopeSignsModel

class HoroscopeCardsAdapter(
    private val signosZodiaco: List<HoroscopeSignsModel>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<HoroscopeCardsViewHolder>() {

    lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeCardsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ctx = parent.context
        return HoroscopeCardsViewHolder(
            layoutInflater.inflate(
                R.layout.cards_zodiaco,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HoroscopeCardsViewHolder, position: Int) {
        holder.render(signosZodiaco[position], onClickListener)
    }

    override fun getItemCount(): Int = signosZodiaco.size

    class OnClickListener(val clickListener: (signSelected: HoroscopeSignsModel) -> Unit) {
        fun onClick(signSelected: HoroscopeSignsModel) = clickListener(signSelected)
    }
}