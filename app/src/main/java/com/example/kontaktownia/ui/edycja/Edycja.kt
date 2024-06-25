package com.example.kontaktownia.ui.edycja

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kontaktownia.R
import com.example.kontaktownia.edytuj
import com.example.kontaktownia.sprawdz
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent
import com.example.kontaktownia.zbierzdane
/**
 * Edycja
 *
 * @constructor Utworz pusta edycje
 */
class Edycja : Fragment() {
    // Definicja parametrow klasy
    private var imie: String? = null
    private var nazwisko: String? = null
    private var telefon: String? = null
    private var email: String? = null
    private var kontakt: PlaceholderContent.kontakt? = null
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
            miasto = it.getString("miasto")
            ulica = it.getString("ulica")
            praca = it.getBoolean("praca")
            println("Dane kontaktowe: $imie $nazwisko $telefon $email $miasto $ulica $praca")

            //utworzenie obiektu kontaktu
            if (!(imie.isNullOrEmpty() || nazwisko.isNullOrEmpty() || telefon.isNullOrEmpty() || email.isNullOrEmpty())) {
                kontakt = PlaceholderContent.kontakt(imie!!, nazwisko!!, telefon!!, email!!, miasto!!, ulica!!, praca)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // definicja widoków
        val view = inflater.inflate(R.layout.fragment_edycja, container, false)
        val imieV: EditText = view.findViewById(R.id.imieE)
        val nazwiskoV: EditText = view.findViewById(R.id.nazwiskoE)
        val telefonV: EditText = view.findViewById(R.id.telefonE)
        val emailV: EditText = view.findViewById(R.id.emailE)
        val edytuj: Button = view.findViewById(R.id.edytujkontakt)
        val miastoV: EditText = view.findViewById(R.id.miastoE)
        val ulicaV: EditText = view.findViewById(R.id.ulicaE)
        val pracaV: CheckBox = view.findViewById(R.id.pracaE)
        //ustawienie pól do edycji na wartości z bazy danych
        imieV.setText(imie)
        nazwiskoV.setText(nazwisko)
        telefonV.setText(telefon)
        emailV.setText(email)
        miastoV.setText(miasto)
        ulicaV.setText(ulica)
        pracaV.isChecked = praca

        edytuj.setOnClickListener {
            val pracachecked = pracaV.isChecked
            val dane = zbierzdane(imieV, nazwiskoV, telefonV, emailV, miastoV, ulicaV, pracaV)


            if (dane != null) {
                println("Dane sa poprawne")
                edytuj(kontakt, dane, view)
                //stworzenie bundla z danymi kontaktu
                val bundle = Bundle()
                bundle.putString("imie", dane.imie)
                bundle.putString("nazwisko", dane.nazwisko)
                bundle.putString("telefon", dane.telefon)
                bundle.putString("email", dane.email)
                bundle.putString("miasto", dane.miasto)
                bundle.putString("ulica", dane.ulica)
                bundle.putBoolean("praca", pracachecked)
                //powrot do podgladu kontaktu
                edytuj.findNavController().navigate(R.id.action_edycja_to_podgladKontaktu,bundle)
            } else {
                println("Dane sa niepoprawne")
            }

        }


        return view
    }

}