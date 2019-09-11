package de.niko.schulapp.ui.absence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.niko.schulapp.R

class AbsenceFragment : Fragment() {

    private lateinit var absenceViewModel: AbsenceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        absenceViewModel =
            ViewModelProviders.of(this).get(AbsenceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_absence, container, false)
        val textView: TextView = root.findViewById(R.id.text_absence)
        absenceViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}