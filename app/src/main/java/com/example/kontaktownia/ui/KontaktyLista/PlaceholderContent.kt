package com.example.kontaktownia.ui.KontaktyLista

import java.io.File

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    data class kontakt(val imie: String, val nazwisko: String, val telefon: String, val email: String)

    fun wczytajkontakty(sciezka: String): MutableList<kontakt> { //Funkcja wczytuje kontakty z pliku
        val plik = File(sciezka) //Otwieranie pliku
        val kontakty = mutableListOf<kontakt>() //Stworzenie listy kontaktow
        with(plik) {
            if (!exists()) { //Sprawdzenie czy plik istnieje
                if (!createNewFile()) { //Tworzenie pliku gdy go nie ma
                    error("Nie udalo sie stworzyc pliku")
                }
            }
        }
        if(plik.canRead() && plik.canWrite()) { //Sprawdzenie czy plik jest odczytywalny i zapisywalny
            println("Plik jest odczytywalny i zapisywalny")
        }
        else { //Nie da sie odczytac pliku
            error("Nie da sie odczytac pliku")
        }
        if (plik.length() == 0L) { //Sprawdzenie czy plik jest pusty
            println("Plik jest pusty")

        }

        else {
            if(plik.length() > 0L) { //Sprawdzenie czy plik nie jest pusty
                println("jestem tu")

                plik.forEachLine { //Wczytywanie linijek z pliku
                    val (imie, nazwisko, telefon, email) = it.split(",", limit = 4)
                    kontakty.add(kontakt(imie, nazwisko, telefon, email)) //Dodawanie kontaktow do listy
                    println("${imie}, ${nazwisko}, ${telefon}, $email") //Wypisanie kontaktow do konsoli
                }

            }



        }

        val set:MutableSet<kontakt> = kontakty.toMutableSet().toSortedSet(compareBy { it.imie }) //Konwersja listy kontaktow na set (Dzieki temu usuwamy duplikaty)
        return set.toMutableList() //Konwersja seta na liste
    }











}