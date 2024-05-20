package com.example.kontaktownia
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.kontakt
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.wczytajkontakty
import java.io.File

fun dodaj(kontakt: kontakt, view: View): Int {
    val kontakty = mutableListOf<kontakt>()
    val sciezka = view.context.filesDir.toString()+"/kontakty.txt"
    if (File(sciezka).length() != 0L) {
        wczytajkontakty(sciezka).forEach {
            kontakty.add(it)
            println(it)
        }
    }

    if (!kontakty.contains(kontakt)) {
        if(kontakty.add(kontakt)) {
            val plik = File(sciezka)
            val wyjscie = kontakty.joinToString(separator = "\n") { "${it.imie},${it.nazwisko},${it.telefon}" }
            println("*******************")
            println(wyjscie)
            println("*******************22")
            println(kontakty)
            println("*******************22222")
            plik.writeText(wyjscie)
            return 1
        }
        else {
            return 0
        }
    }

    else {
        return 2
    }
}
fun sluchacz(imie: EditText, nazwisko:EditText, telefon: EditText, knefel: Button): View.OnClickListener? {
        return View.OnClickListener {
            val dane = zbierzdane(imie,nazwisko,telefon)
            if (dane != null) {

                var text = "Dotarlem do zbierania danych Dane To: ${dane!!.imie} ${dane.nazwisko} ${dane.telefon}"
                var toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                toast.show()
                if (dodaj(dane,knefel) == 1) {
                    text = "Dodalem do pliku ${knefel.context.filesDir}"
                    toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
                    toast.show()
                }
                if (dodaj(dane,knefel) == 2) {
                    text = "Nie Dodalem do pliku poniewaz juz istnieje ten kontakt"
                    toast = Toast.makeText(knefel.context,text, Toast.LENGTH_SHORT)
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

fun sprawdz(imie: String, nazwisko: String, telefon:String): Boolean {
    if(imie.isEmpty() || nazwisko.isEmpty() || telefon.isEmpty()) {

        return false
    }
    if (!telefon.matches(Regex("\\d+"))) {
        return false
    }
    else {
        return true
    }
}
fun zbierzdane(imie: EditText, nazwisko: EditText, telefon: EditText): kontakt? {
    val imied = imie.text.toString()
    val nazwiskod = nazwisko.text.toString()
    val telefond = telefon.text.toString()
    if (!sprawdz(imied,nazwiskod,telefond)) {
        return null
    }
    return kontakt(imied, nazwiskod, telefond)
}