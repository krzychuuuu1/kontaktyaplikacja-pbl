package com.example.kontaktownia.ui.podgladKontaktu

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
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

//import com.example.kontaktownia.zdjecieZalbumu
import java.io.File

/**
 * Fragment do podgladu kontaktu
 */
class podgladKontaktu : Fragment() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val zdjecie = data?.data
            println(zdjecie)
            val plik = File.createTempFile("zdjecietmp", ".jpg")
            println(plik.canWrite())
            println(plik)
            val inputStream = zdjecie?.let { context?.contentResolver?.openInputStream(it) }
            inputStream?.copyTo(plik.outputStream(), 1024)
            plik.writeBytes(inputStream?.readBytes()!!)
        }
    }
    //definicja wartosci klasy
    private var imie: String? = null
    private var nazwisko: String? = null
    private var telefon: String? = null
    private var email: String? = null
    private var imienazwisko: String? = null
    private var miasto: String? = null
    private var ulica: String? = null
    private var praca: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //wczytanie przekazanych wartosci
            imie = it.getString("imie")
            nazwisko = it.getString("nazwisko")
            telefon = it.getString("telefon")
            email = it.getString("email")
            imienazwisko = imie.toString() + " " + nazwisko.toString()
            miasto = it.getString("miasto")
            ulica = it.getString("ulica")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //definicja widoków
        val view = inflater.inflate(R.layout.fragment_podglad_kontaktu, container, false)
        val nazwaV : TextView = view.findViewById(R.id.nazwap)
        val telefonV : TextView = view.findViewById(R.id.telefonp)
        val mailV : TextView = view.findViewById(R.id.Mail)
        val dzwonV: ImageButton = view.findViewById(R.id.dzwon)
        val miastoV: TextView = view.findViewById(R.id.Miasto)
        val ulicaV: TextView = view.findViewById(R.id.Ulica)


        //ustawienie wartosci pól na wartości z bazy danych
        if (miasto != null) {
            if(miasto != "Brak") {
                miastoV.visibility = View.VISIBLE
                view.findViewById<TextView>(R.id.text34).visibility = View.VISIBLE
                miastoV.text = miasto
            }
            else {
                miastoV.visibility = View.GONE
                view.findViewById<TextView>(R.id.text34).visibility = View.GONE
                miastoV.text = miasto
            }
        }
        if (ulica != null) {
            if(miasto != "Brak") {
                ulicaV.visibility = View.VISIBLE
                view.findViewById<TextView>(R.id.text35).visibility = View.VISIBLE
                ulicaV.text = ulica
            }

        else {
            ulicaV.visibility = View.GONE
            view.findViewById<TextView>(R.id.text35).visibility = View.GONE
            ulicaV.text = ulica
        }
        }


        if (imienazwisko != null) {
            nazwaV.text = imienazwisko
        }
        if (telefon != null) {
            telefonV.text = telefon
            telefonV.visibility = View.VISIBLE
            if(telefon != "Brak numeru") {
                dzwonV.visibility = View.VISIBLE
                dzwon(dzwonV, telefon!!, view)
            }
            else {
                dzwonV.visibility = View.GONE
            }


        }
        //ustawienie pola email na wartosc z bazy danych lub ukrycie jezeli brak takiej wartosci dla danego kontaktu
        if (email != "Brak maila") {
            mailV.visibility = View.VISIBLE
            mailV.text = email.toString()
        }
        else {
            mailV.visibility = View.GONE
            view.findViewById<TextView>(R.id.text33).visibility = View.GONE
        }
        val knefeledycja : ImageButton = view.findViewById(R.id.knefelEdycja)
        knefeledycja.setOnClickListener{
            //przekazanie wartosci do fragmentu edycji
            val bundle = Bundle()
            bundle.putString("imie", imie)
            bundle.putString("nazwisko", nazwisko)
            bundle.putString("telefon", telefon)
            bundle.putString("email", email)
            bundle.putString("miasto", miasto)
            bundle.putString("ulica", ulica)
            bundle.putBoolean("praca", praca)
            //przekierowanie do fragmentu edycji
            //
//           val zdjecie = zdjecieZalbumu(view)
//            println(zdjecie)
//            if (zdjecie != null) {
//                val plik = File.createTempFile("zdjecie", ".jpg")
//                //copy zdjecie to plik and save
//                zdjecie.copyTo(plik, true)
//                //save plik to local storage
//                plik.writeBytes(zdjecie.readBytes())
//
//
//            }
            knefeledycja.findNavController().navigate(R.id.action_podgladKontaktu_to_edycja, bundle)


        }


        return view
    }

}
