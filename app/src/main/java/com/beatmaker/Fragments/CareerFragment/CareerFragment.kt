package com.beatmaker.Fragments.CareerFragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.beatmaker.Activities.BeatMakingActivity.BeatMakingActivity
import com.beatmaker.Fragments.CareerFragment.view.CarrerView
import com.beatmaker.R
import com.beatmaker.Utils.Utils

class CareerFragment : Fragment(), View.OnClickListener, CarrerView {
    lateinit var linear_firstTest: LinearLayout
    lateinit var linear_secondTest: LinearLayout
    lateinit var linear_thirdTest: LinearLayout
    lateinit var linear_fourthTest: LinearLayout
    private val carrerView: CarrerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_career2, container, false)
        init(view)
        listeners(view)

        return view
    }


    private fun init(view: View?) {


        linear_firstTest = view?.findViewById(R.id.linear_firstTest)!!
        linear_secondTest = view?.findViewById(R.id.linear_secondTest)!!
        linear_thirdTest = view?.findViewById(R.id.linear_thirdTest)!!
        linear_fourthTest = view?.findViewById(R.id.linear_thirdTest)!!

    }

    private fun listeners(view: View?) {
        linear_firstTest.setOnClickListener(this)
        linear_secondTest.setOnClickListener(this)
        linear_thirdTest.setOnClickListener(this)
        linear_fourthTest.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.linear_firstTest -> {
                showDialog(requireActivity())
                val handler = Handler()
                handler.postDelayed({
                    hideDialog()
                    var intent = Intent(requireActivity(), BeatMakingActivity::class.java)
                    startActivity(intent)
                }, 1500)
            }
            R.id.linear_secondTest -> {
                showDialog(requireActivity())
                val handler = Handler()
                handler.postDelayed({
                    hideDialog()
                    var intent = Intent(requireActivity(), BeatMakingActivity::class.java)
                    startActivity(intent)
                }, 1500)
            }
            R.id.linear_thirdTest -> {
                showDialog(requireActivity())
                val handler = Handler()
                handler.postDelayed({
                    hideDialog()
                    var intent = Intent(requireActivity(), BeatMakingActivity::class.java)
                    startActivity(intent)
                }, 1500)
            }
            R.id.linear_fourthTest-> {
                showDialog(requireActivity())
                val handler = Handler()
                handler.postDelayed({
                    hideDialog()
                    var intent = Intent(requireActivity(), BeatMakingActivity::class.java)
                    startActivity(intent)
                }, 1500)
            }
        }
    }

    override fun showDialog(activity: Activity) {
        Utils.showDialogMethod(activity, activity.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity?, message: String?) {
        Utils.showMessage(activity, message)
    }


}