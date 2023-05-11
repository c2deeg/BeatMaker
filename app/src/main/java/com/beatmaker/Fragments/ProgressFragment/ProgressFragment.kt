package com.beatmaker.Fragments.ProgressFragment

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.beatmaker.R
import java.util.*


class ProgressFragment : Fragment() {
    lateinit var tv_perfect:TextView
    lateinit var tv_late:TextView
    lateinit var tv_miss:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(com.beatmaker.R.layout.fragment_progress, container, false)
        tv_perfect = view.findViewById(com.beatmaker.R.id.tv_perfect) as TextView
        tv_late = view.findViewById(com.beatmaker.R.id.tv_late) as TextView
        tv_miss = view.findViewById(com.beatmaker.R.id.tv_miss) as TextView

        val paint = tv_perfect.paint
        val width = paint.measureText(tv_perfect.text.toString())
        val textShader: Shader = LinearGradient(0f, 0f, width, tv_perfect.textSize, intArrayOf(
            Color.parseColor("#F5F5F5"),
            Color.parseColor("#ACE38B"),
            Color.parseColor("#52C40C"),
            /*Color.parseColor("#64B678"),
            Color.parseColor("#478AEA"),*/
            Color.parseColor("#8446CC")
        ), null, Shader.TileMode.REPEAT)

        tv_perfect.paint.setShader(textShader)


        val paint2 = tv_late.paint
        val width2 = paint2.measureText(tv_late.text.toString())
        val textShader2: Shader = LinearGradient(0f, 0f, width2, tv_late.textSize, intArrayOf(
            Color.parseColor("#C751FE"),
            Color.parseColor("#FFFFFF"),

            /*Color.parseColor("#64B678"),
            Color.parseColor("#478AEA"),*/
            Color.parseColor("#8446CC")
        ), null, Shader.TileMode.REPEAT)

        tv_late.paint.setShader(textShader2)
        tv_miss.paint.setShader(textShader2)




        return view
    }

}