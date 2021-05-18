package com.dom_broks.hireme.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dom_broks.hireme.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {
    // TODO: Implement the ViewModel
}