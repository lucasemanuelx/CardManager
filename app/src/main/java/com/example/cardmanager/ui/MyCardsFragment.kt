package com.example.cardmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardmanager.R
import com.example.cardmanager.recyclerView.CreditCard
import com.example.cardmanager.recyclerView.ItemAdapter
import com.example.cardmanager.databinding.FragmentMyCardsBinding

class MyCardsFragment : Fragment() {

    private lateinit var binding: FragmentMyCardsBinding
    private lateinit var adapter: ItemAdapter
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newCardList: ArrayList<CreditCard>
    lateinit var cardImages: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyCardsBinding.inflate(inflater, container, false)
        newRecyclerView = binding.recyclerView

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialized()
        val layoutManager = LinearLayoutManager(context)
        newRecyclerView.layoutManager = layoutManager
        adapter = ItemAdapter(newCardList)
        newRecyclerView.adapter = adapter

    }

    private fun dataInitialized() {
        newCardList = arrayListOf<CreditCard>()

        cardImages = arrayOf(
            R.drawable.cart_o_preto,
            R.drawable.cart_o_roxo,
            R.drawable.cart_o_verde
        )

        for (i in cardImages.indices) {
            val card = CreditCard(cardImages[i])
            newCardList.add(card)
        }
        newRecyclerView.adapter = ItemAdapter(newCardList)
    }
}