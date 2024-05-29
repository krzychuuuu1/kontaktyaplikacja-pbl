package com.example.kontaktownia.ui.KontaktyLista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kontaktownia.R
import com.example.kontaktownia.ui.AdapterWyswietlaniaListy
import com.example.kontaktownia.ui.KontaktyLista.PlaceholderContent.wczytajkontakty
import com.example.kontaktownia.MainActivity

/**
 * Fragment listy kontaktow
 *
 * @constructor Utworz fragment listy
 */
class ItemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // ustawienie adaptera listy kontaktow
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                //ustawienie adaptera na wyswietlanie wszyskich kontaktow
                adapter = AdapterWyswietlaniaListy(wczytajkontakty(context.filesDir.toString()+"/kontakty.txt"),false,"")


            }
        }
        val edittext= activity?.findViewById<EditText>(R.id.wyszukiwanie)
        //dodanie sluchacza dla pola wyszukiwaia
        edittext?.doOnTextChanged { text, start, before, count ->
            //zmiana adaptera listy kontaktow na nowy z filtrowanymi kontaktami
            (view as RecyclerView).adapter = AdapterWyswietlaniaListy(PlaceholderContent.wczytajkontakty(edittext.context.filesDir.toString()+"/kontakty.txt"),true,text.toString())

        }
        return view
    }

    companion object { //szczerze nie wiem po co to ale jak tego nie ma to umiera
        const val ARG_COLUMN_COUNT = "column-count"

    }
}