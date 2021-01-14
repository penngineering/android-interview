package com.png.interview.ui.heroes

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.api.models.heroes.Hero
import com.png.interview.api.models.heroes.HeroRole
import com.png.interview.databinding.DataboundViewHolder
import com.png.interview.databinding.ViewBasicHeroListItemBinding
import com.png.interview.databinding.ViewDetailedHeroListItemBinding
import com.png.interview.extensions.getLayoutInflater
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import javax.inject.Provider

@FlowPreview
@ExperimentalCoroutinesApi
class HeroesAdapter
@Inject constructor(
    private val viewBindersBasic: Provider<BasicHeroItemViewBinder>,
    private val viewBindersDetailed: Provider<DetailedHeroItemViewBinder>
) : RecyclerView.Adapter<DataboundViewHolder<ViewDataBinding>>() {

    val HERO_OTHER_TYPE = 0
    val HERO_SUPPORT_TYPE = 1
    val HERO_WARRIOR_TYPE = 2

    var data = listOf<HeroAdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataboundViewHolder<ViewDataBinding> {
        return DataboundViewHolder(
                when (viewType) {
                    HERO_SUPPORT_TYPE, HERO_WARRIOR_TYPE -> {
                        ViewDetailedHeroListItemBinding.inflate(parent.getLayoutInflater(), parent, false).apply {
                            viewBinder = viewBindersDetailed.get()
                            setClickListener { view ->
                                viewBinder?.name?.let { heroName ->
                                    val direction = HeroesFragmentDirections.actionHeroesFragmentToHeroesDetailFragment(heroName)
                                    view.findNavController().navigate(direction)
                                }
                            }
                        }
                    }
                    else -> {
                        ViewBasicHeroListItemBinding.inflate(parent.getLayoutInflater(), parent, false).apply {
                            viewBinder = viewBindersBasic.get()
                        }
                    }
                }

        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DataboundViewHolder<ViewDataBinding>, position: Int) {
        when (holder.binding) {
            is ViewBasicHeroListItemBinding -> {
                (data[position] as? HeroAdapterItem.HeroItem)?.hero.let {
                    holder.binding.viewBinder?.bind(it)
                }
            }
            is ViewDetailedHeroListItemBinding -> {
                (data[position] as? HeroAdapterItem.HeroItem)?.hero.let {
                    holder.binding.viewBinder?.bind(it)
                }
            }
        }
        holder.binding.invalidateAll()
    }

    override fun getItemViewType(position: Int): Int {
        when(data[position]) {
            is HeroAdapterItem.Header -> {
                return HERO_OTHER_TYPE
            }
            is HeroAdapterItem.Footer -> {
                return HERO_OTHER_TYPE
            }
            is HeroAdapterItem.HeroItem -> {
                val heroItem = data[position] as HeroAdapterItem.HeroItem
                when(heroItem.hero.role) {
                    HeroRole.SUPPORT -> {
                        return HERO_SUPPORT_TYPE
                    }
                    HeroRole.WARRIOR -> {
                        return HERO_WARRIOR_TYPE
                    }
                    else -> {
                        return HERO_OTHER_TYPE
                    }
                }
            }
        }
    }

    fun bindData(heroes: List<Hero>) {
        this.data = heroes.map { HeroAdapterItem.HeroItem(it) }
        notifyDataSetChanged()
    }

    sealed class HeroAdapterItem {
        data class HeroItem(val hero: Hero) : HeroAdapterItem()
        object Header : HeroAdapterItem()
        object Footer : HeroAdapterItem()
    }
}
