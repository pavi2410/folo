package me.pavi2410.folo.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import me.pavi2410.folo.data.FoloRepo
import me.pavi2410.folo.models.FoloPlatform
import me.pavi2410.folo.models.FoloProfile
import org.koin.compose.koinInject

class MainScreenViewModel(
    private val foloRepo: FoloRepo
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _profiles = MutableStateFlow<List<FoloProfile>>(emptyList())
    val profiles = _profiles.asStateFlow()

    init {
        viewModelScope.launch {
            Firebase.auth.signInAnonymously().await()
            withContext(Dispatchers.IO) {
                _profiles.value = foloRepo.getAllProfiles()
                _isLoading.value = false
            }
        }
    }

    fun deleteProfile(profileId: String) {
        viewModelScope.launch {
            foloRepo.deleteProfile(profileId)
            _isLoading.value = true
            _profiles.value = foloRepo.getAllProfiles()
            _isLoading.value = false
        }
    }
}