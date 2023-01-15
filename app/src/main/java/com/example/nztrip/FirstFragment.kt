package com.example.nztrip

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nztrip.databinding.ActivityMainBinding
import com.example.nztrip.databinding.FragmentFirstBinding
import com.example.nztrip.databinding.FragmentSecondBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import java.lang.reflect.Array.setChar
import java.math.RoundingMode


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var userArrayList: ArrayList<Detail>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auckland = "Auckland je najväčšia metropolitná oblasť na Novom Zélande na severnom novozélandskom ostrove a najväčšia urbanizovaná oblast v krajine. Žije tu vyše 1,6 milióna obyvateľov, čo je takmer tretina populácie Nového Zélandu.\n" +
                "\n" +
                "Vytvára súmestie zložené z mesta Auckland City, North Shore City, a zastavanými oblasťami miest Waitakere a Manukau, s dištriktom Papakura a urbanizovanými časťami dištriktov Rodney a Franklin.\n"
        val cape = "Cape Reinga / Te Rerenga Wairua , [1] je najsevernejší výbežok polostrova Aupouri na severnom konci severu Ostrov Nového Zélandu. Cape Reinga je viac ako 100 km severne od najbližšieho mestečka Kaitaia . Štátna diaľnica 1 siaha až po mys, no do roku 2010 bola posledných 19 km neutesnená štrková cesta . Vhodné vozidlá môžu prejsť veľkú časť cesty aj cez pláž Ninety Mile Beach a koryto potoka Kauaeparaoa (Te Paki Stream)."
        val hobbiton = "The Hobbiton Movie Set je významné miesto používané pre filmovú trilógiu Pán prsteňov a filmovú trilógiu Hobit . Nachádza sa na rodinnej farme asi 8 kilometrov (5,0 míľ) západne od Hinuery a 10 kilometrov (6,2 míľ) juhozápadne od Matamata , vo Waikato na Novom Zélande a je teraz turistickou destináciou Tolkien , ktorá ponúka prehliadku súboru so sprievodcom."

        val imageId = intArrayOf(R.drawable.auckland, R.drawable.cape, R.drawable.hobbiton)
        val name = arrayOf("Auckland", "Cape Reinga", "Hobbiton")
        val lastMessage = arrayOf("Prílet do Aucklandu", "Najsevernejší bod", "Hobitia dedinka")
        val lastMsgTime = arrayOf("26.2", "27.2", "28.2")
        val detailContent = arrayOf(auckland, cape, hobbiton)

        userArrayList = ArrayList()

        for (i in name.indices) {
            val user = Detail(name[i], lastMessage[i], lastMsgTime[i], imageId[i], detailContent[i])
            userArrayList.add(user)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = MyAdapter(this@FirstFragment.requireActivity(), userArrayList)
        binding.listview.setOnItemClickListener { parent, view, position,id ->

            val name = name[position]
            val imageId = imageId[position]
            val detailContent = detailContent[position]

            val i = Intent(this@FirstFragment.requireActivity(), DetailActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("imageId", imageId)
            i.putExtra("detailContent", detailContent)
            startActivity(i)
        }
    }

}