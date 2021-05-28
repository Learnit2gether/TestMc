package com.example.testmc.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testmc.data.model.ResultDto
import com.example.testmc.databinding.ItemPosterBinding


class MoviesAdapter(): PagingDataAdapter<ResultDto,MoviesAdapter.ViewHolder>(object : DiffUtil.ItemCallback<ResultDto>(){
    override fun areItemsTheSame(oldItem: ResultDto, newItem: ResultDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultDto, newItem: ResultDto): Boolean {
        return oldItem == newItem
    }

}) {

    inner class ViewHolder(val binding: ItemPosterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ResultDto?){
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPosterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
}