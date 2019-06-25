package com.ogaclejapan.smarttablayout.demo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<View>(R.id.list) as ListView
        listView.onItemClickListener = this

        val demoAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1)

        for (demo in Demo.values()) {
            demoAdapter.add(getString(demo.titleResId))
        }

        listView.adapter = demoAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_github -> {
                openGitHub()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val demo = Demo.values()[position]
        demo.startActivity(this)
    }

    private fun openGitHub() {
        val uri = Uri.parse(getString(R.string.app_github_url))
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}
