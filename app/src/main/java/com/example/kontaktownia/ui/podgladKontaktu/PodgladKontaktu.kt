package com.example.kontaktownia.ui.podgladKontaktu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kontaktownia.R
import com.example.kontaktownia.dzwon

/**
 * A simple [Fragment] subclass.
 * Use the [podgladKontaktu.newInstance] factory method to
 * create an instance of this fragment.
 */
class podgladKontaktu : Fragment() {
    private var imie: String? = null
    private var nazwisko: String? = null
    private var telefon: String? = null
    private var email: String? = null
    private var imienazwisko: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imie = it.getString("imie")
            nazwisko = it.getString("nazwisko")
            telefon = it.getString("telefon")
            email = it.getString("email")
            imienazwisko = imie.toString() + " " + nazwisko.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_podglad_kontaktu, container, false)
        val nazwaV : TextView = view.findViewById(R.id.nazwap)
        val telefonV : TextView = view.findViewById(R.id.telefonp)
        val mailV : TextView = view.findViewById(R.id.Mail)
        val dzwonV: ImageButton = view.findViewById(R.id.dzwon)
        if (imienazwisko != null) {
            nazwaV.text = imienazwisko
        }
        if (telefon != null) {
            telefonV.text = telefon
            telefonV.visibility = View.VISIBLE
            if(telefon != "Brak numeru") {
                dzwonV.visibility = View.VISIBLE
                dzwon(dzwonV, telefon!!)
            }
            else {
                dzwonV.visibility = View.GONE
            }


        }

        if (email != "Brak maila") {
            mailV.visibility = View.VISIBLE
            mailV.text = email.toString()
        }
        else {
            mailV.visibility = View.GONE
            view.findViewById<TextView>(R.id.text33).visibility = View.GONE
        }



        return view
    }

}
