package de.niko.schulapp.ui.substitution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.niko.schulapp.R

class SubstitutionPlanFragment : Fragment() {

    private lateinit var substitutionPlanViewModel: SubstitutionPlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        substitutionPlanViewModel =
            ViewModelProviders.of(this).get(SubstitutionPlanViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_substitution_plan, container, false)
        val textView: TextView = root.findViewById(R.id.text_substitution_plan)
        substitutionPlanViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}