package com.example.kontaktownia.ui.dodajkontakt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kontaktownia.databinding.FragmentDodajBinding
import com.example.kontaktownia.sluchacz

class NotificationsFragment : Fragment(){

    private var _binding: FragmentDodajBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentDodajBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val imie: EditText = binding.imie
        val nazwisko: EditText = binding.nazwisko
        val telefon: EditText = binding.telefon
        val knefel: Button = binding.dodajkontakt
        knefel.setOnClickListener(sluchacz(imie,nazwisko,telefon,knefel))




        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}