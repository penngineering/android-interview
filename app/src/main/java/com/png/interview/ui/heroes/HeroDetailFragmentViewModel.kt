package com.png.interview.ui.heroes

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HeroDetailFragmentViewModel
@Inject constructor(private val heroesAction: RetrieveHeroesAction) : ViewModel() {
    suspend fun getHeroeDetails(name: String) = heroesAction.getHero(name)
}
