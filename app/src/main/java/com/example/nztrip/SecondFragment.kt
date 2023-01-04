package com.example.nztrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nztrip.databinding.ActivityMainBinding
import com.example.nztrip.databinding.FragmentSecondBinding
import java.math.RoundingMode


class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private var cisla: ArrayList<Float>? = null; //premenna uloz vsetky cisla ktore uzivatel zadal
    private var znaky: ArrayList<String>? = null; 

    private var cislo = "";
    private var priklad = "";
    private var vysledok = 0f; // vykoná počítanie a uloží výsledok
    private var resetovanie = true;
    private var NZDCouse = 1.688614;
    private var EURCouse = 0.592716;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cisla = ArrayList()
        znaky = ArrayList()

        binding.appBtn0.setOnClickListener {
            zadajCislo("0")
        }
        binding.appBtn1.setOnClickListener {
            zadajCislo("1")
        }
        binding.appBtn2.setOnClickListener {
            zadajCislo("2")
        }
        binding.appBtn3.setOnClickListener {
            zadajCislo("3")
        }
        binding.appBtn4.setOnClickListener {
            zadajCislo("4")
        }
        binding.appBtn5.setOnClickListener {
            zadajCislo("5")
        }
        binding.appBtn6.setOnClickListener {
            zadajCislo("6")
        }
        binding.appBtn7.setOnClickListener {
            zadajCislo("7")
        }
        binding.appBtn8.setOnClickListener {
            zadajCislo("8")
        }
        binding.appBtn9.setOnClickListener {
            zadajCislo("9")
        }

        binding.appToEur.setOnClickListener {

            if (resetovanie && cislo != "") {
                val transferNzdToEur = priklad.toFloat() * EURCouse
                val roundedEur = transferNzdToEur.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                binding.vysledokTxt.text = roundedEur.toString()
            }
        }
        binding.appToNzd.setOnClickListener {

            if (resetovanie && cislo != "") {
                val transferEurToNzd = priklad.toFloat() * NZDCouse
                val roundedNzd = transferEurToNzd.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                binding.vysledokTxt.text = roundedNzd.toString()
            }
        }

        binding.appResult.setOnClickListener {
            if (resetovanie && cislo != "") {
                cisla?.add(cislo.toFloat())
                vypocitajPriklad()
            }
        }

        binding.appReset.setOnClickListener {
            resetovanie = true
            cislo = ""
            priklad = ""
            vysledok = 0f
            cisla = ArrayList()
            znaky = ArrayList()
            binding.prikladTxt.text = ""
            binding.vysledokTxt.text = ""
        }
    }

    private fun zadajCislo(num: String) {
        if (resetovanie) {
            cislo += num
            priklad += num
            binding.prikladTxt.text = priklad
        }
    }

    private fun zadajZnak(str: String) {
        if (resetovanie && cislo != "") {
            cisla?.add(cislo.toFloat())
            cislo = ""
            znaky?.add(str)
            priklad += str
            binding.prikladTxt.text = priklad
        }
    }

    private fun vypocitajPriklad () {
        if (cisla!!.size != 0 && znaky!!.size != 0 && cisla!!.size > znaky!!.size) {
            vysledok = cisla!![0]
            var i = 0
            while (i < znaky!!.size) {
                if (znaky!![i] == "toEur") {
                    vysledok += cisla!![i + 1]
                    i++
                }
                else if (znaky!![i] == "toNzd") {
                    vysledok -= cisla!![i + 1]
                    i++
                }
            }
        }
        binding.vysledokTxt.text = vysledok.toString()
        resetovanie = false
    }

    // uvolnenie pamate ak daný fragment zavriem
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}