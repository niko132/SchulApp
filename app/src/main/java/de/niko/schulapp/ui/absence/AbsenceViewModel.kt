package de.niko.schulapp.ui.absence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AbsenceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Absence Fragment"
    }
    val text: LiveData<String> = _text
}