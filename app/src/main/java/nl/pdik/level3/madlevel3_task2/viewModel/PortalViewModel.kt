package nl.pdik.level3.madlevel3_task2.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import nl.pdik.level3.madlevel3_task2.model.Portal

class PortalViewModel  : ViewModel() {
    val portals =   mutableStateListOf<Portal>()


    fun addPortal(portal: Portal){
        portals.add(portal);
    }

    fun someValues(){
        portals.add(Portal("http://dlo.mijnhva.nl","dlo"));
        portals.add(Portal("http://mijnhva.nl","Mijn hva"));
        portals.add(Portal("http://rooster.hva.nl","Rooster"));
    }
}