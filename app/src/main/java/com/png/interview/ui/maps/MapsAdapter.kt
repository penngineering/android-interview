package com.png.interview.ui.maps

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.api.models.heroes.Hero
import com.png.interview.api.models.heroes.HeroRole
import com.png.interview.api.models.maps.HotsMap
import com.png.interview.databinding.DataboundViewHolder
import com.png.interview.databinding.MapListItemBinding
import com.png.interview.databinding.ViewBasicHeroListItemBinding
import com.png.interview.databinding.ViewDetailedHeroListItemBinding
import com.png.interview.extensions.getLayoutInflater
import com.png.interview.ui.heroes.BasicHeroItemViewBinder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import javax.inject.Provider

@FlowPreview
@ExperimentalCoroutinesApi
class MapsAdapter
@Inject constructor(
    private val viewBinders: Provider<MapItemViewBinder>
) : RecyclerView.Adapter<DataboundViewHolder<ViewDataBinding>>() {

    var data = listOf<MapsAdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataboundViewHolder<ViewDataBinding> {
        return DataboundViewHolder(
            MapListItemBinding.inflate(parent.getLayoutInflater(), parent, false).apply {
                viewBinder = viewBinders.get()
            }
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DataboundViewHolder<ViewDataBinding>, position: Int) {
        val binder = (holder.binding as? MapListItemBinding)?.viewBinder
        binder?.bind(data[position].map)
        holder.binding.invalidateAll()
    }

    fun bindData(maps: List<HotsMap>) {
        this.data = maps.map { MapsAdapterItem(it) }
        notifyDataSetChanged()
    }

    data class MapsAdapterItem(val map: HotsMap)
}
