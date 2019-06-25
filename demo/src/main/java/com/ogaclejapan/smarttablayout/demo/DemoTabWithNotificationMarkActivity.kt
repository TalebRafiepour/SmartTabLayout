package com.ogaclejapan.smarttablayout.demo

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

import java.util.Random

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class DemoTabWithNotificationMarkActivity : AppCompatActivity(), SmartTabLayout.TabProvider {

    private val random = Random(System.currentTimeMillis())

    private val demo: Demo
        get() = Demo.valueOf(intent.getStringExtra(KEY_DEMO))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_tab_with_notification_mark)

        val demo = demo

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setTitle(demo.titleResId)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val tab = findViewById<View>(R.id.tab) as ViewGroup
        tab.addView(LayoutInflater.from(this).inflate(demo.layoutResId, tab, false))

        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        val viewPagerTab = findViewById<View>(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setCustomTabView(this)

        val pages = FragmentPagerItems(this)
        for (titleResId in demo.tabs()) {
            pages.add(FragmentPagerItem.of(getString(titleResId), DemoFragment::class.java))
        }

        val adapter = FragmentPagerItemAdapter(
                supportFragmentManager, pages)

        viewPager.adapter = adapter
        viewPagerTab.setViewPager(viewPager)

        viewPagerTab.setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val tab = viewPagerTab.getTabAt(position)
                val mark = tab.findViewById<View>(R.id.custom_tab_notification_mark)
                mark.visibility = View.GONE
            }
        })

        findViewById<View>(R.id.test).setOnClickListener {
            val position = Math.abs(random.nextInt()) % demo.tabs().size
            val tab = viewPagerTab.getTabAt(position)
            val mark = tab.findViewById<View>(R.id.custom_tab_notification_mark)
            mark.visibility = View.VISIBLE
        }

    }

    override fun createTabView(container: ViewGroup, position: Int, adapter: PagerAdapter): View {
        val inflater = LayoutInflater.from(container.context)
        val res = container.context.resources
        val tab = inflater.inflate(R.layout.custom_tab_icon_and_notification_mark, container, false)
        val mark = tab.findViewById<View>(R.id.custom_tab_notification_mark)
        mark.visibility = View.GONE
        val icon = tab.findViewById<View>(R.id.custom_tab_icon) as ImageView
        when (position) {
            0 -> icon.setImageDrawable(res.getDrawable(R.drawable.ic_home_white_24dp))
            1 -> icon.setImageDrawable(res.getDrawable(R.drawable.ic_search_white_24dp))
            2 -> icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_white_24dp))
            else -> throw IllegalStateException("Invalid position: $position")
        }
        return tab
    }

    companion object {

        private val KEY_DEMO = "demo"

        fun startActivity(context: Context, demo: Demo) {
            val intent = Intent(context, DemoTabWithNotificationMarkActivity::class.java)
            intent.putExtra(KEY_DEMO, demo.name)
            context.startActivity(intent)
        }
    }
}
