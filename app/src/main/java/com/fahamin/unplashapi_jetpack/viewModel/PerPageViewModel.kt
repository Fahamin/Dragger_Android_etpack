package com.fahamin.unplashapi_jetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahamin.unplashapi_jetpack.repository.ImageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PerPageViewModel @Inject constructor(imageRepository: ImageRepository):ViewModel() {

    var  perPageList = imageRepository.imagepageList

    init {
        viewModelScope.launch {
            imageRepository.getImagePageList()
        }
    }
}