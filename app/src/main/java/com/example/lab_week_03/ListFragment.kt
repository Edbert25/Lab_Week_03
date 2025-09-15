package com.example.lab_week_03

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var coffeeListener: CoffeeListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CoffeeListener) {
            coffeeListener = context
        } else {
            throw RuntimeException("Must implement CoffeeListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coffeeNames = listOf(
            getString(R.string.affogato_title),
            getString(R.string.americano_title),
            getString(R.string.latte_title)
        )
        val coffeeIds = listOf(
            DetailFragment.COFFEE_AFFOGATO,
            DetailFragment.COFFEE_AMERICANO,
            DetailFragment.COFFEE_LATTE
        )
        val listView = view.findViewById<ListView>(R.id.coffee_list)
        listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, coffeeNames)
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            coffeeListener.onSelected(coffeeIds[position])
        }
    }

    interface CoffeeListener {
        fun onSelected(coffeeId: Int)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
