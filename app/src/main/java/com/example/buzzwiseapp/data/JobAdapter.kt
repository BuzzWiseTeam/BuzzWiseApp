package com.example.buzzwiseapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buzzwiseapp.R
import com.example.buzzwiseapp.data.response.DataItem
import com.example.buzzwiseapp.databinding.ItemJobBinding
import com.example.buzzwiseapp.ui.jobdetail.JobDetailActivity

class JobAdapter (private val listJob: List<DataItem>):
    RecyclerView.Adapter<JobAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listJob[position])
    }

    override fun getItemCount(): Int = listJob.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemJobBinding.bind(itemView)
        fun bind(data: DataItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.companyProfileImage)
                    .into(imgAvatar)
                tvSpecialist.text = data.requiredSkill
                tvCompany.text = data.companyName
                tvLocation.text = data.location
                itemView.setOnClickListener {
                    JobDetailActivity.start(
                        itemView.context,
                        data.companyProfileImage as String,
                        data.id as String,
                        Pair(imgAvatar,"ivItemPhoto" )
                    )
                }
            }

        }
    }
}