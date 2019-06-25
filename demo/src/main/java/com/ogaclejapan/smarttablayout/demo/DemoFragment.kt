package com.ogaclejapan.smarttablayout.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import androidx.fragment.app.Fragment

class DemoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = FragmentPagerItem.getPosition(arguments)
        val title = view.findViewById<View>(R.id.item_title) as TextView
        title.text = position.toString()
    }

}
