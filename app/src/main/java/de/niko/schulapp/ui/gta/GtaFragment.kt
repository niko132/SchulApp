package de.niko.schulapp.ui.gta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.niko.schulapp.R

class GtaFragment : Fragment() {

    private lateinit var gtaViewModel: GtaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gtaViewModel =
            ViewModelProviders.of(this).get(GtaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gta, container, false)
        val textView: TextView = root.findViewById(R.id.text_gta)
        gtaViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}