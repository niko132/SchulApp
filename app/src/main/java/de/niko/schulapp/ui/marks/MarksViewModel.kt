package de.niko.schulapp.ui.marks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Marks Fragment"
    }
    val text: LiveData<String> = _text
}