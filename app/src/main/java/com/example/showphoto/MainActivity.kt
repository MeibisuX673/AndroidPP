package com.example.showphoto


import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.showphoto.presenters.MainActivityPresenter
import com.example.showphoto.views.MainActivityView
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import com.example.showphoto.NewPhoto.Cheking



class MainActivity : MainActivityView,Cheking,MvpAppCompatActivity() {

    @InjectPresenter
    lateinit var mMainActivityPresenter: MainActivityPresenter

    var bottomNavigationView: BottomNavigationView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigation)
        swipeRefreshLayout = findViewById(R.id.swipe)
        mMainActivityPresenter.startActivity()

        refrshApp()

        bottomNavigationView?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.newPhoto -> setCurrentFragment(NewPhoto())
                R.id.popularPhoto -> setCurrentFragment(PopularPhoto())
            }
            true
        }

    }

    private fun addFragment() {

        when(bottomNavigationView?.selectedItemId){
            bottomNavigationView?.menu?.findItem(R.id.newPhoto)?.itemId -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.FlFragment, NewPhoto())
                commit()
            }
            bottomNavigationView?.menu?.findItem(R.id.popularPhoto)?.itemId -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.FlFragment, PopularPhoto())
                commit()
            }
        }

    }

    override fun checkInternet(){
        if (!amIConnected()) {
            mMainActivityPresenter.noInternet()
        } else {
            mMainActivityPresenter.internetOn()
        }
    }

    override fun stop() {

        bottomNavigationView?.menu?.findItem(R.id.newPhoto)?.isEnabled = false
        bottomNavigationView?.menu?.findItem(R.id.popularPhoto)?.isEnabled = false

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FlFragment, IsInternet())
            commit()
        }
    }

    override fun start() {
        bottomNavigationView?.menu?.findItem(R.id.newPhoto)?.isEnabled = true
        bottomNavigationView?.menu?.findItem(R.id.popularPhoto)?.isEnabled = true

        addFragment()
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FlFragment, fragment)
            commit()
        }

    }


    private fun getScreenOrientation(): String? {
        return if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) "Портретная ориентация"
        else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) "Альбомная ориентация"
        else ""
    }

    private fun amIConnected(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun refrshApp() {
        swipeRefreshLayout?.setOnRefreshListener {
            if (!amIConnected()) {
                Log.d("internetON", "dsadasdsa")
                mMainActivityPresenter.noInternet()

                swipeRefreshLayout?.isRefreshing = false
            } else {
                Log.d("internetOFF", "dsadasdsa")
                mMainActivityPresenter.internetOn()

                swipeRefreshLayout?.isRefreshing = false

            }
        }

    }


}




