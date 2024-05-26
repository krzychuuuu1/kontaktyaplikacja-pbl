package com.example.kontaktownia.ui

import android.os.Bundle
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kontaktownia.R
import com.example.kontaktownia.databinding.FragmentItemBinding
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.kontakt
import com.example.kontaktownia.ui.podgladKontaktu.podgladKontaktu

class AdapterWyswietlaniaListy(
    private val values: List<kontakt>,
    private val szukanie: Boolean,
    private val coszukac: String
) : RecyclerView.Adapter<AdapterWyswietlaniaListy.ViewHolder>() {


    var wartosci = values.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (szukanie) {
            parent.post {
                wartosci = values.filter {
                    it.imie.contains(coszukac, true) ||
                            it.nazwisko.contains(coszukac, true) ||
                            it.telefon.contains(coszukac, true) ||
                            it.email.contains(coszukac, true)
                }.toMutableList()
                println("Wyszukanie:")
                println(wartosci)
                notifyDataSetChanged()

                if (wartosci.isEmpty()) {
                    wartosci.add(kontakt("Brak kontaktow z takimi danymi", "", "", ""))
                }
            }
        }
        else {
            wartosci = values.toMutableList()
        }
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
        val nazwa = item.imie + " " + item.nazwisko

        holder.contentView.text = nazwa
        holder.telefonView.text = item.telefon
        holder.itemView.setOnClickListener {
            if (holder.telefonView.text != "Brak kontaktow z takimi danymi") {
                val bundle = Bundle()
                bundle.putString("imie", item.imie)
                bundle.putString("nazwisko", item.nazwisko)
                bundle.putString("telefon", item.telefon)
                bundle.putString("email", item.email)
                holder.itemView.findNavController().navigate(R.id.action_navigation_kontakty_to_podgladKontaktu, bundle)



            }
        }
    }

    override fun getItemCount(): Int = wartosci.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val telefonView: TextView = binding.telefon
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}