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

        val imageId = intArrayOf(R.drawable.nz_flag, R.drawable.currencies)
        val name = arrayOf("Josko", "Ferko")
        val lastMessage = arrayOf("Hello prva sprava", "Hello druha sprava")
        val lastmsgTime = arrayOf("8:45", "10:20")
        val phoneNo = arrayOf("0905", "0904")
        val country = arrayOf("Slovensko", "Cesko")

        userArrayList = ArrayList()

        for (i in name.indices) {
            val user = Detail(name[i], lastMessage[i], lastmsgTime[i], phoneNo[i], country[i], imageId[i])
            userArrayList.add(user)
        }

        binding.listview.isClickable = true
       // binding.listview.adapter = MyAdapter(this, userArrayList)
        binding.listview.setOnItemClickListener { parent, view, position,id ->

            val name = name[position]
            val phone = phoneNo[position]
            val country = country[position]
            val imageId = imageId[position]

         /*   val i = Intent(this, DetailActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("phone", phone)
            i.putExtra("country", country)
            i.putExtra("imageId", imageId)
            startActivity(i)*/
        }
    }

}