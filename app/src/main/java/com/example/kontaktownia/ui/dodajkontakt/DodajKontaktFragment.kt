package com.example.kontaktownia.ui.dodajkontakt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
import com.example.kontaktownia.databinding.FragmentDodajBinding
import com.example.kontaktownia.sluchacz

/**
 * Dodaj kontakt fragment
 *
 * @constructor Utworz pusty fragment DOdawania kontaktu
 */
class DodajKontaktFragment : Fragment(){

    private var _binding: FragmentDodajBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDodajBinding.inflate(inflater, container, false)
        //definicja zmiennych (w celu skrocenia zapisu)
        val root: View = binding.root
        val imie: EditText = binding.imie
        val nazwisko: EditText = binding.nazwisko
        val telefon: EditText = binding.telefon
        val knefel: Button = binding.dodajkontakt
        val email: EditText = binding.email
        val miasto: EditText = binding.miasto
        val ulica: EditText = binding.ulica
        val praca: CheckBox = binding.praca
        //Dodanie sluchacza do przycisku dodajkontakt
        knefel.setOnClickListener(sluchacz(imie,nazwisko,telefon,email,knefel,miasto,ulica,praca))




        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}