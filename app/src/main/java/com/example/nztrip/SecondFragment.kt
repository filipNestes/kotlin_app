package com.example.nztrip

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.nztrip.databinding.FragmentSecondBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.RoundingMode


class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val currenciesViewModel = CoursesViewModel()

    private var myNumbers: ArrayList<Float>? = null; //premenna uloz vsetky cisla ktore uzivatel zadal

    private var myChar = ""
    private var currencyValue = ""
    private var result = 0f // vykoná počítanie a uloží výsledok
    private var isReset = true
    private var NZDCouse = 1.688614
    private var EURCouse = 0.592716

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        currenciesViewModel.getCurrencyData()

        // precita naposledy ulozenu hodnotu, ak nema, fallbackne na konstanty
        NZDCouse = getCurrency(this@SecondFragment.requireActivity(), "nzd").also { it.logE("nacitany kurz eur/nzd") } ?: 1.688614
        EURCouse = getCurrency(this@SecondFragment.requireActivity(), "eur").also { it.logE("nacitany kurz nzd/eur") } ?: 0.592716

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currenciesViewModel.currenciesLiveData.observe(this@SecondFragment.requireActivity()) { item ->

            item?.data?.eur?.value?.let { eur -> setCurrency(this@SecondFragment.requireActivity(), "eur", eur) }
            item?.data?.nzd?.value?.let { nzd -> setCurrency(this@SecondFragment.requireActivity(), "nzd", nzd) }

            binding.appToEur.setOnClickListener {

                if (isReset && myChar != "") {
                    val transferNzdToEur = currencyValue.toFloat() * (item?.data?.eur?.value ?: EURCouse)
                    val roundedEur = transferNzdToEur.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    binding.currencyResult.text = roundedEur.toString() + '€'
                    binding.currencyResult.setTextColor(Color.parseColor("#ffffff"))
                    binding.currencyResult.setBackgroundColor(Color.parseColor("#012169"))
                }
            }
            binding.appToNzd.setOnClickListener {

                if (isReset && myChar != "") {
                    val transferEurToNzd = currencyValue.toFloat() / (item?.data?.eur?.value ?: NZDCouse)
                    val roundedNzd = transferEurToNzd.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    binding.currencyResult.text = roundedNzd.toString() + '$'
                    binding.currencyResult.setTextColor(Color.parseColor("#ffffff"))
                    binding.currencyResult.setBackgroundColor(Color.parseColor("#012169"))
                }
            }
        }

        myNumbers = ArrayList()

        binding.appBtn0.setOnClickListener {
            setChar("0")
        }
        binding.appBtn1.setOnClickListener {
            setChar("1")
        }
        binding.appBtn2.setOnClickListener {
            setChar("2")
        }
        binding.appBtn3.setOnClickListener {
            setChar("3")
        }
        binding.appBtn4.setOnClickListener {
            setChar("4")
        }
        binding.appBtn5.setOnClickListener {
            setChar("5")
        }
        binding.appBtn6.setOnClickListener {
            setChar("6")
        }
        binding.appBtn7.setOnClickListener {
            setChar("7")
        }
        binding.appBtn8.setOnClickListener {
            setChar("8")
        }
        binding.appBtn9.setOnClickListener {
            setChar("9")
        }
        binding.appDecimalPoint.setOnClickListener {
            setChar(".")
        }

        binding.appBack.setOnClickListener {
            removeLastCharacter()
        }

        binding.appReset.setOnClickListener {
            isReset = true
            myChar = ""
            currencyValue = ""
            result = 0f
            myNumbers = ArrayList()
            binding.currencyValue.text = ""
            binding.currencyResult.text = ""
            binding.currencyResult.setTextColor(Color.parseColor("#000000"));
            binding.currencyResult.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    private fun setChar(num: String) {
        if (isReset) {
            myChar += num
            currencyValue += num
            binding.currencyValue.text = currencyValue
        }
    }

    private fun removeLastCharacter() {
        if (isReset) {
            currencyValue = currencyValue.dropLast(1)
            binding.currencyValue.text = currencyValue
        }
    }

    // uvolnenie pamate ak daný fragment zavriem
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}