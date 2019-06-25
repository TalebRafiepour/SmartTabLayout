package com.ogaclejapan.smarttablayout.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.ogaclejapan.smarttablayout.SmartTabLayout

enum class Demo private constructor(val titleResId: Int, val layoutResId: Int) {

    BASIC(R.string.demo_title_basic, R.layout.demo_basic),

    BASIC2(R.string.demo_title_basic2, R.layout.demo_basic_title_offset_auto_center),

    SMART_INDICATOR(R.string.demo_title_smart_indicator, R.layout.demo_smart_indicator),

    DISTRIBUTE_EVENLY(R.string.demo_title_distribute_evenly, R.layout.demo_distribute_evenly) {
        override fun tabs(): IntArray {
            return tab3()
        }
    },

    ALWAYS_IN_CENTER(R.string.demo_title_always_in_center, R.layout.demo_always_in_center),

    CUSTOM_TAB(R.string.demo_title_custom_tab_text, R.layout.demo_custom_tab_text),

    CUSTOM_TAB_COLORS(R.string.demo_title_custom_tab_colors, R.layout.demo_custom_tab_colors),

    CUSTOM_TAB_ICONS1(R.string.demo_title_custom_tab_icons1, R.layout.demo_custom_tab_icons1) {
        override fun tabs(): IntArray {
            return intArrayOf(R.string.demo_tab_no_title, R.string.demo_tab_no_title, R.string.demo_tab_no_title, R.string.demo_tab_no_title)
        }

        override fun setup(layout: SmartTabLayout) {
            super.setup(layout)

            val inflater = LayoutInflater.from(layout.context)

            layout.setCustomTabView(object :SmartTabLayout.TabProvider{
                override fun createTabView(container: ViewGroup, position: Int, adapter: PagerAdapter): View {
                    val icon = inflater.inflate(R.layout.custom_tab_icon2, container,
                            false) as ImageView
                    when (position) {
                        0 -> icon.setImageResource(R.drawable.ic_home_white_24dp)
                        1 -> icon.setImageResource(R.drawable.ic_search_white_24dp)
                        2 -> icon.setImageResource(R.drawable.ic_person_white_24dp)
                        3 -> icon.setImageResource(R.drawable.ic_flash_on_white_24dp)
                        else -> throw IllegalStateException("Invalid position: $position")
                    }
                    return icon
                }
            })
        }
    },

    CUSTOM_TAB_ICONS2(R.string.demo_title_custom_tab_icons2, R.layout.demo_custom_tab_icons2) {
        override fun tabs(): IntArray {
            return intArrayOf(R.string.demo_tab_no_title, R.string.demo_tab_no_title, R.string.demo_tab_no_title, R.string.demo_tab_no_title)
        }

        override fun setup(layout: SmartTabLayout) {
            super.setup(layout)

            val inflater = LayoutInflater.from(layout.context)

            layout.setCustomTabView(object :SmartTabLayout.TabProvider{
                override fun createTabView(container: ViewGroup, position: Int, adapter: PagerAdapter): View {
                    val icon = inflater.inflate(R.layout.custom_tab_icon2, container,
                            false) as ImageView
                    when (position) {
                        0 -> icon.setImageResource(R.drawable.ic_home_white_24dp)
                        1 -> icon.setImageResource(R.drawable.ic_search_white_24dp)
                        2 -> icon.setImageResource(R.drawable.ic_person_white_24dp)
                        3 -> icon.setImageResource(R.drawable.ic_flash_on_white_24dp)
                        else -> throw IllegalStateException("Invalid position: $position")
                    }
                    return icon
                }
            })
        }
    },

    CUSTOM_TAB_ICON_AND_TEXT(R.string.demo_title_custom_tab_icon_and_text,
            R.layout.demo_custom_tab_icon_and_text) {
        override fun tabs(): IntArray {
            return tab3()
        }
    },

    CUSTOM_TAB_ICON_AND_NOTIFICATION_MARK(R.string.demo_title_custom_tab_icon_and_notification_mark,
            R.layout.demo_custom_tab_icon_and_notification_mark) {
        override fun tabs(): IntArray {
            return tab3()
        }

        override fun startActivity(context: Context) {
            DemoTabWithNotificationMarkActivity.startActivity(context, this)
        }
    },

    CUSTOM_TAB_MARGIN(R.string.demo_title_custom_tab_margin, R.layout.demo_custom_tab_margin),

    INDICATOR_TRICK1(R.string.demo_title_indicator_trick1, R.layout.demo_indicator_trick1),

    INDICATOR_TRICK2(R.string.demo_title_indicator_trick2, R.layout.demo_indicator_trick2),

    RIGHT_TO_LEFT(R.string.demo_title_right_to_left, R.layout.demo_rtl) {
        override fun startActivity(context: Context) {
            DemoRtlActivity.startActivity(context, this)
        }
    },

    LIKE_MEDIUM_TAG(R.string.demo_title_advanced_medium, R.layout.demo_like_a_medium_tag) {
        override fun tabs(): IntArray {
            return intArrayOf(R.string.demo_tab_like_a_medium_top, R.string.demo_tab_like_a_medium_latest)
        }

        override fun startActivity(context: Context) {
            DemoLikeMediumActivity.startActivity(context, this)
        }
    };

    open fun startActivity(context: Context) {
        DemoActivity.startActivity(context, this)
    }

    open fun setup(layout: SmartTabLayout) {
        //Do nothing.
    }

    open fun tabs(): IntArray {
        return tab10()
    }

    companion object {

        fun tab10(): IntArray {
            return intArrayOf(R.string.demo_tab_1, R.string.demo_tab_2, R.string.demo_tab_3, R.string.demo_tab_4, R.string.demo_tab_5, R.string.demo_tab_6, R.string.demo_tab_7, R.string.demo_tab_8, R.string.demo_tab_9, R.string.demo_tab_10)
        }

        fun tab3(): IntArray {
            return intArrayOf(R.string.demo_tab_8, R.string.demo_tab_9, R.string.demo_tab_10)
        }
    }

}
