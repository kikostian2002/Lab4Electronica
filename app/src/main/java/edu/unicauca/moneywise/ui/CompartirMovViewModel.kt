package edu.unicauca.moneywise.ui


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import edu.unicauca.moneywise.ui.Movimiento

class CompartirMovViewModel : ViewModel() {
    private var _selectedMovimiento = MutableStateFlow<Movimiento?>(null)
    var selectedMovimiento: StateFlow<Movimiento?> = _selectedMovimiento

    fun setMovimiento(movimiento: Movimiento?) {
        _selectedMovimiento.value = movimiento
    }

    fun clearMovimiento() {
        _selectedMovimiento.value = null
    }
}