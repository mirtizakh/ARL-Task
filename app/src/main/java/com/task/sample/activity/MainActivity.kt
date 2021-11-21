package com.task.sample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.task.sample.R
import com.task.sample.app.AppController
import com.task.sample.util.LogExceptions
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity() {
    // region VARIABLES
    var navController: NavController? = null
    private lateinit var navHost: NavHostFragment
    private val logException: LogExceptions by AppController.kodein().instance()
    // end region VARIABLES

    // region LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
    }
    // end region LIFECYCLE


    // region PUBLIC methods
    // This function is used to do navigation between screens
    fun navigate(destinationId: Int) {
        try {
            if (navController != null) {
                navController?.navigate(destinationId)
            }
        } catch (e: Exception) {
            var lineNumber = ""
            Thread.currentThread().stackTrace[2]?.let { t ->
                lineNumber = t.lineNumber.toString()
            }
            logException.saveExceptionErrors(e, this.javaClass.simpleName, lineNumber)
            e.printStackTrace()
        }
    }
    // end region PUBLIC methods
}