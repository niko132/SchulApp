package de.niko.schulapp.ui.marks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.niko.schulapp.R

class MarksFragment : Fragment() {

    private lateinit var marksViewModel: MarksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        marksViewModel =
            ViewModelProviders.of(this).get(MarksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_marks, container, false)
        val textView: TextView = root.findViewById(R.id.text_marks)
        marksViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}