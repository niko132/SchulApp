package de.niko.schulapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private var lastSelectedItemId: Int = -1

    private val menuData: Array<Array<Int>> = arrayOf(
        arrayOf(
            R.id.navigation_substitution_plan,
            R.drawable.ic_message_black_24dp,
            R.string.title_substitution_plan
        ),
        arrayOf(
            R.id.navigation_timetable,
            R.drawable.ic_dashboard_black_24dp,
            R.string.title_timetable
        ),
        arrayOf(
            R.id.navigation_marks,
            R.drawable.ic_format_list_numbered_black_24dp,
            R.string.title_marks
        ),
        arrayOf(
            R.id.navigation_news,
            R.drawable.ic_notifications_black_24dp,
            R.string.title_news
        ),
        arrayOf(
            R.id.navigation_gta,
            R.drawable.ic_directions_bike_black_24dp,
            R.string.title_gta
        ),
        arrayOf(
            R.id.navigation_absence,
            R.drawable.ic_pan_tool_black_24dp,
            R.string.title_absence
        ),
        arrayOf(
            R.id.navigation_settings,
            R.drawable.ic_notifications_black_24dp,
            R.string.title_settings
        ),
        arrayOf(
            R.id.navigation_more,
            R.drawable.ic_more_horiz_black_24dp,
            R.string.title_more
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val sideNavView: NavigationView = findViewById(R.id.side_nav_view)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_substitution_plan,
                R.id.navigation_timetable,
                R.id.navigation_marks,
                R.id.navigation_news,
                R.id.navigation_gta,
                R.id.navigation_absence
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)


        val navMenuData =
            createNavigationMenuData(arrayOf(0, 1, 2, 3, 4, 5, 6), navView.maxItemCount)

        navMenuData[0].forEach {
            val item = navView.menu.add(Menu.NONE, it[0], Menu.NONE, it[2])
            item.icon = resources.getDrawable(it[1], theme)
        }
        navMenuData[1].forEach {
            val item = sideNavView.menu.add(Menu.NONE, it[0], Menu.NONE, it[2])
            item.icon = resources.getDrawable(it[1], theme)
        }


        navView.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.navigation_more) {
                drawerLayout.openDrawer(GravityCompat.END)
            } else {
                drawerLayout.closeDrawer(GravityCompat.END)
                NavigationUI.onNavDestinationSelected(it, navController)
                lastSelectedItemId = it.itemId
            }

            true
        }
        navView.setOnNavigationItemReselectedListener {
            if (it.itemId == R.id.navigation_more) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END))
                    drawerLayout.closeDrawer(GravityCompat.END)
                else
                    drawerLayout.openDrawer(GravityCompat.END)
            }
        }
        lastSelectedItemId = navView.selectedItemId

        sideNavView.setupWithNavController(navController)
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {

            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END) && slideOffset < 0.5)
                    navView.selectedItemId = lastSelectedItemId
            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerOpened(drawerView: View) {

            }
        })

        sideNavView.setNavigationItemSelectedListener {
            if (it.itemId != R.id.navigation_settings) {
                sideNavView.menu.forEach { menuItem ->
                    menuItem.isChecked = menuItem.itemId == it.itemId
                }

                lastSelectedItemId = R.id.navigation_more
            }

            drawerLayout.closeDrawer(GravityCompat.END)

            NavigationUI.onNavDestinationSelected(it, navController)

            true
        }
    }

    private fun createNavigationMenuData(
        order: Array<Int>,
        maxItems: Int
    ): Array<Array<Array<Int>>> {
        val navMenuData = menuData.filterIndexed { index, _ ->
            order.contains(index)
        }

        val orderedNavMenuData: MutableList<Array<Int>> = mutableListOf()
        for (index in order) {
            orderedNavMenuData.add(navMenuData[index])
        }

        val firstList: MutableList<Array<Int>> = mutableListOf()
        val secondList: MutableList<Array<Int>> = mutableListOf()

        if (navMenuData.size <= maxItems) {
            firstList.addAll(orderedNavMenuData)
        } else {
            firstList.addAll(orderedNavMenuData.subList(0, maxItems - 1))
            firstList.add(menuData[menuData.size - 1])

            secondList.addAll(orderedNavMenuData.subList(maxItems - 1, orderedNavMenuData.size))
        }

        return arrayOf(
            firstList.toTypedArray(),
            secondList.toTypedArray()
        )
    }
}
