package com.example.kontaktownia.ui.wybieranie

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kontaktownia.cyfra
import com.example.kontaktownia.databinding.FragmentWybieranieBinding
import com.example.kontaktownia.dzwon

class WybieranieFragment : Fragment() {

    private var _binding: FragmentWybieranieBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentWybieranieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val knefle = listOf(binding.button1, binding.button2, binding.button3, binding.button4, binding.button5, binding.button6, binding.button7,binding.button8,binding.button9,binding.button10,binding.button11)
        cyfra(knefle, binding.editTextNumber)
        dzwon(binding.imageButton, binding.editTextNumber)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
