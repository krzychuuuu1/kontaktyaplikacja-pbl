package com.example.kontaktownia

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.Intent.ACTION_OPEN_DOCUMENT
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import com.example.kontaktownia.ui.AdapterWyswietlaniaListy
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.kontakt
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.wczytajkontakty
import java.io.File
/*
 * Plik z funkcjami do obslugi kontaktow
 * Wiekszosc funkcji jest sluchaczami na przyciski lub funkcjami do obslugi danych
 */

/**
 * Dodaj
 *
 * @param kontakt
 * @param view
 * @return
 */
fun dodaj(kontakt: kontakt, view: View): Int { //Funkcja ktora wykonuje dodanie do pliku danego kontaktu
    val kontakty = mutableListOf<kontakt>() //definicja listy tymczasowej (buforu z pliku)
    val sciezka = view.context.filesDir.toString()+"/kontakty.txt" //sciezka do pliku
    if (File(sciezka).length() != 0L) {   //sprawdzenie czy plik nie jest pusty
        wczytajkontakty(sciezka).forEach {
            kontakty.add(it)
            println(it)
        }
    }

    if (!kontakty.contains(kontakt)) {  //sprawdzenie czy nie ma juz takiego kontaktu
        if(kontakty.add(kontakt)) {  //dodanie kontaktu do bufora
            val plik = File(sciezka) //otwarcie pliku
            val wyjscie = kontakty.joinToString(separator = "\n") { "${it.imie},${it.nazwisko},${it.telefon},${it.email},${it.ulica},${it.miasto},${it.praca}" } //konwersja bufora do stringa
            plik.writeText(wyjscie) //zapis do pliku
            return 1 //zwrocenie informacji o pomyslnym dodaniu
        }
        else {
            return 0 //zwrocenie informacji o niepowodzeniu dodania
        }
    }

    else {
        return 2 //zwrocenie informacji o niepowodzeniu dodania z powodu duplikatu
    }
}

/**
 * Sluchacz
 *
 * @param imie
 * @param nazwisko
 * @param telefon
 * @param email
 * @param knefel
 * @return
 */
fun sluchacz(imie: EditText, nazwisko:EditText, telefon: EditText, email: EditText, knefel: Button, miasto: EditText, ulica: EditText, praca: CheckBox): View.OnClickListener { //Sluchacz na przycisk
        return View.OnClickListener {
            val dane = zbierzdane(imie,nazwisko,telefon, email, miasto, ulica, praca) //Pobranie danych
            imie.setText("")
            nazwisko.setText("")
            telefon.setText("")
            email.setText("")
            miasto.setText("")
            ulica.setText("")
            praca.isChecked = false
            if (dane != null) { //Sprawdzenie czy dane sa poprawne
                //Wszystkie Toasty sa tylko do testow
                var text = "Dotarlem do zbierania danych Dane To: ${dane.imie} ${dane.nazwisko} ${dane.telefon} ${dane.email} ${dane.miasto} ${dane.ulica} ${dane.praca}"
                var toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                toast.show()
                if (dodaj(dane,knefel) == 1) {
                    text = "Dodalem do listy kontaktow ${knefel.context.filesDir}"
                    toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                    toast.show()
                }
                if (dodaj(dane,knefel) == 2) {
                    text = "Nie Dodalem do pliku poniewaz juz istnieje ten kontakt"
                    toast = Toast.makeText(knefel.context, text, Toast.LENGTH_SHORT)
                    toast.show()
                }
                else {
                    text = "Nie Dodalem do pliku"
                    toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                    toast.show()
                }

            }
            else {
                val text = "Wprowadz poprawnie Dane"
                val toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                toast.show()
            }

        }
    }

/**
 * Sprawdz
 *
 * @param imie
 * @param nazwisko
 * @param telefon
 * @param email
 * @return
 */
fun sprawdz(imie: String, nazwisko: String, telefon:String, email:String, miasto: String, ulica: String, praca: Boolean): Boolean { //Funkcja do sprawdzania danych
    if(imie.isEmpty() || nazwisko.isEmpty() ) { //Sprawdzenie czy dane sa puste
        return false
    }
    if (!telefon.matches(Regex("\\d+")) && telefon != "Brak numeru") { //Sprawdzenie czy numer telefonu jest poprawny
        return false
    }
    return if(!email.matches(Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")) && email != "Brak maila") { //Sprawdzenie czy email jest poprawny
        false
    }
    else {  //Jezeli dane sa poprawne
        true
    }
}

/**
 * Zbierzdane
 *
 * @param imie
 * @param nazwisko
 * @param telefon
 * @param email
 * @return
 */
fun zbierzdane(imie: EditText, nazwisko: EditText, telefon: EditText, email: EditText, miasto: EditText, ulica: EditText, praca: CheckBox): kontakt? { //Funkcja do zbierania danych
    val imied = imie.text.toString() //Pobranie danych imienia
    val nazwiskod = nazwisko.text.toString() //Pobranie danych nazwiska
    var telefond = telefon.text.toString() //Pobranie danych telefonu
    var emaild = email.text.toString() //Pobranie danych email
    var miastod = miasto.text.toString() //Pobranie danych miasta
    var ulicad = ulica.text.toString() //Pobranie danych ulicy
    var pracad = praca.isChecked //Pobranie danych czy praca
    if (miasto.text.isEmpty()) { //Sprawdzenie czy miasto jest puste
        miastod = "Brak"
    }
    if (ulica.text.isEmpty()) { //Sprawdzenie czy ulica jest pusta
        ulicad = "Brak"
    }
    if (email.text.isEmpty()) { //Sprawdzenie czy email jest pusty
        emaild = "Brak maila"
    }
    if (telefon.text.isEmpty()) { //Sprawdzenie czy telefon jest pusty
        telefond = "Brak numeru"
    }
    if (!sprawdz(imied,nazwiskod,telefond, emaild, miastod, ulicad, pracad)) { //Sprawdzenie czy dane sa poprawne
        return null
    }
    return kontakt(imied, nazwiskod, telefond, emaild, ulicad, miastod, pracad) //Zwracanie danych
}

/**
 * Cyfra
 *
 * @param button
 * @param text
 */
@SuppressLint("SetTextI18n")
fun cyfra(button: List<Button>, text: TextView) { //Sluchacz na cyfry wybierania
    button.forEach{ it ->
        it.setOnClickListener {
            val to = it as Button
            val tekst = to.text.toString()
            text.setText(text.text.toString()+tekst)
        }
    }
}

/**
 * Dzwon
 *
 * @param button
 * @param text
 * @param view
 */
fun dzwon(button: ImageButton, text: String, view: View?) { //Funkcja do zadzwonienia na numer
    if (view != null) {
        if (text == "") {
            println("jest puste")
            button.setOnClickListener {
                val numer: String = view.findViewById<TextView>(R.id.editTextNumber).text.toString()
                println("Dzwonie do ale tak naprawde")
                println(numer)
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:${numer}")
                startActivity(button.context, intent, null)

            }
        }
        else {

            button.setOnClickListener {
                val numer: String = text
                println("Dzwonie do ale tak naprawde")
                println(text)
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:${numer}")
                startActivity(button.context, intent, null)

            }
        }

    }
   else {
        return
        }

}

/**
 * Szukanie
 *
 * @param button
 * @param text
 */
fun szukanie(button: ImageButton, text: EditText) {
    button.setOnClickListener {
        if(text.isVisible) {
            text.visibility = View.INVISIBLE


        }
        else {
            text.visibility = View.VISIBLE
            text.requestFocus()
            text.setFocusableInTouchMode(true)
            val imm = text.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(text, InputMethodManager.SHOW_FORCED)


        }
    }
}

/**
 * Edytuj
 *
 * @param kontakt
 * @param kontaktE
 * @param view
 */
fun edytuj (kontakt: kontakt?, kontaktE :kontakt, view: View) {
    val kontakty = wczytajkontakty(view.context.filesDir.toString()+"/kontakty.txt")
    if (kontakt != null) {
        if (kontakty.contains(kontakt)) {
            kontakty.remove(kontakt)
            kontakty.add(kontaktE)
            val plik = File(view.context.filesDir.toString()+"/kontakty.txt")
            println(kontaktE)
            val wyjscie = kontakty.joinToString(separator = "\n") { "${it.imie},${it.nazwisko},${it.telefon},${it.email},${it.ulica},${it.miasto},${it.praca}" }
            plik.writeText(wyjscie)
        }
        else {
            println("Nie ma takiego kontaktu")
        }
    }
    else {
        println("Nie otrzymalem kontaktu")
    }


}
//fun zdjecieZalbumu(view: View): String{
//    println("biore zdjecie")
////import image from gallery to file
//    val intent = Intent(ACTION_OPEN_DOCUMENT)
//    intent.type = "image/*"
//    startActivityForResult(view.context as MainActivity, intent, 0, null)
//    println(intent.data)
//    val zdjecie = intent.data.toString()
//    onActivityResult(0, 1, intent)
//   println(zdjecie)
//    return zdjecie
//}
//fun zapiszZdjecie(zdjecie: Uri, view: View) {
//    val plik = File.createTempFile("zdjecie", ".jpg")
//    //copy zdjecie to plik and save
//    zdjecie.copyTo(plik, true)
//    //save plik to local storage
//    plik.writeBytes(zdjecie.readBytes())
//}




