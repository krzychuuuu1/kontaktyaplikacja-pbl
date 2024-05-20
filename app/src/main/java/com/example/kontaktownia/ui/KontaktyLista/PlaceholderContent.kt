package com.example.kontaktownia.ui.KontaktyLista

import java.io.File
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    data class kontakt(val imie: String, val nazwisko: String, val telefon: String)

    fun wczytajkontakty(sciezka: String): MutableList<kontakt> {
        val plik = File(sciezka)
        val kontakty = mutableListOf<kontakt>()
        with(plik) {
            if (!exists()) {
                if (!createNewFile()) {
                    error("Nie udalo sie stworzyc pliku")
                }
            }
        }
        if(plik.canRead() && plik.canWrite()) {
            println("Plik jest odczytywalny i zapisywalny")
        }
        else {
            error("Nie da sie odczytac pliku")
        }
        if (plik.length() == 0L) {
            error("Plik jest pusty")

        }

        else {
            if(plik.length() > 0L) {
                println("jestem tu")

                plik.forEachLine {
                    val (imie, nazwisko, telefon) = it.split(",", limit = 3)
                    kontakty.add(kontakt(imie, nazwisko, telefon))
                    println("${imie}, ${nazwisko}, ${telefon}")
                }

            }



        }

        val set:MutableSet<kontakt> = kontakty.toMutableSet()
        return set.toMutableList()
    }











}