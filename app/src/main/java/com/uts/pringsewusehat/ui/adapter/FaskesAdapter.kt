package com.uts.pringsewusehat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uts.pringsewusehat.R
import com.uts.pringsewusehat.data.model.Faskes
import com.uts.pringsewusehat.databinding.ItemFaskesBinding

class FaskesAdapter(
    private var faskesList: List<Faskes>,
    private val onItemClick: (Faskes) -> Unit
) : RecyclerView.Adapter<FaskesAdapter.FaskesViewHolder>() {

    inner class FaskesViewHolder(private val binding: ItemFaskesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(faskes: Faskes) {
            val context = binding.root.context

            binding.tvFaskesName.text = faskes.name
            binding.tvCategoryChip.text = faskes.category
            binding.tvRating.text = context.getString(R.string.rating_format, faskes.rating)
            binding.tvOperationalHours.text = faskes.operationalHours
            binding.tvFaskesAddress.text = faskes.address
            binding.tvFaskesPhone.text = faskes.phone

            when (faskes.category) {
                "Rumah Sakit" -> {
                    binding.tvCategoryChip.setTextColor(
                        ContextCompat.getColor(context, R.color.chip_hospital_text)
                    )
                }
                "Puskesmas" -> {
                    binding.tvCategoryChip.setTextColor(
                        ContextCompat.getColor(context, R.color.chip_puskesmas_text)
                    )
                }
                else -> {
                    binding.tvCategoryChip.setTextColor(
                        ContextCompat.getColor(context, R.color.chip_klinik_text)
                    )
                }
            }

            binding.btnViewDetail.setOnClickListener {
                onItemClick(faskes)
            }
            binding.root.setOnClickListener {
                onItemClick(faskes)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaskesViewHolder {
        val binding = ItemFaskesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FaskesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FaskesViewHolder, position: Int) {
        holder.bind(faskesList[position])
    }

    override fun getItemCount(): Int = faskesList.size

    fun updateData(newList: List<Faskes>) {
        this.faskesList = newList
        notifyDataSetChanged()
    }
}
