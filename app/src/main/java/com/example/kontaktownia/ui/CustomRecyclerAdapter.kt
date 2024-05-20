package com.example.kontaktownia.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.kontakt
import com.example.kontaktownia.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CustomRecyclerAdapter(
    private val values: List<kontakt>
) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {
    val wartosci : MutableList<kontakt> = values.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wartosci[position]
        val nazwa = item.imie + item.nazwisko
        holder.contentView.text = nazwa
        holder.telefonView.text = item.telefon.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val telefonView: TextView = binding.telefon
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}