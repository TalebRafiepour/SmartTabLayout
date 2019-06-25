package com.ogaclejapan.smarttablayout.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager

class DemoRtlActivity : AppCompatActivity() {

    private val demo: Demo
        get() = Demo.valueOf(intent.getStringExtra(KEY_DEMO))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rtl)

        val demo = demo

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle(demo.titleResId)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val tab = findViewById<View>(R.id.tab) as ViewGroup
        tab.addView(LayoutInflater.from(this).inflate(demo.layoutResId, tab, false))

        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        val viewPagerTab = findViewById<View>(R.id.viewpagertab) as SmartTabLayout
        demo.setup(viewPagerTab)

        val pages = FragmentPagerItems(this)
        for (titleResId in demo.tabs()) {
            pages.add(FragmentPagerItem.of(getString(titleResId), DemoFragment::class.java))
        }

        val adapter = FragmentPagerItemAdapter(
                supportFragmentManager, pages)

        viewPager.adapter = adapter
        viewPagerTab.setViewPager(viewPager)

    }

    companion object {

        private val KEY_DEMO = "demo"

        fun startActivity(context: Context, demo: Demo) {
            val intent = Intent(context, DemoRtlActivity::class.java)
            intent.putExtra(KEY_DEMO, demo.name)
            context.startActivity(intent)
        }
    }
}
