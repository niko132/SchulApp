package de.niko.schulapp.ui.gta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GtaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is GTA Fragment"
    }
    val text: LiveData<String> = _text
}