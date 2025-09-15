package com.example.lab_week_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var coffeeTitle: TextView? = null
    private var coffeeDesc: TextView? = null

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
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coffeeTitle = view.findViewById(R.id.coffee_title)
        coffeeDesc = view.findViewById(R.id.coffee_desc)
    }

    fun setCoffeeData(id: Int) {
        when (id) {
            R.id.affogato -> {
                coffeeTitle?.text = getString(R.string.affogato_title)
                coffeeDesc?.text = getString(R.string.affogato_desc)
            }
            R.id.americano -> {
                coffeeTitle?.text = getString(R.string.americano_title)
                coffeeDesc?.text = getString(R.string.americano_desc)
            }
            R.id.latte -> {
                coffeeTitle?.text = getString(R.string.latte_title)
                coffeeDesc?.text = getString(R.string.latte_desc)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
