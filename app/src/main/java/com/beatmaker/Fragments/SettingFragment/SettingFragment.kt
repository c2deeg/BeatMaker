package com.beatmaker.Fragments.SettingFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.beatmaker.R


class SettingFragment : Fragment(), View.OnClickListener {
    private var img_instagram: ImageView? = null
    private var img_facebook: ImageView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_setting, container, false)
        init(view)
        listners(view)
        return view
    }


    private fun init(view: View?) {
        img_instagram = view?.findViewById(R.id.img_instagram)
        img_facebook = view?.findViewById(R.id.img_facebook)

    }

    private fun listners(view: View?) {
        img_instagram?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.img_facebook -> {
                val uri: Uri =
                    Uri.parse("https://www.facebook.com/") // missing 'http://' will cause crashed

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            R.id.img_instagram->{
                val uri: Uri =
                    Uri.parse("https://www.instagram.com/") // missing 'http://' will cause crashed

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
    }

}