package com.png.interview.ui.maps

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.png.interview.databinding.FragmentMapsBinding
import com.png.interview.extensions.onMain
import com.png.interview.extensions.viewLifecycleScope
import com.png.interview.ui.InjectedFragment
import com.png.interview.ui.heroes.HeroesFragmentViewBinder
import com.png.interview.ui.heroes.HeroesFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapsFragment : InjectedFragment() {

    @Inject
    lateinit var fragmentViewBinder: MapsFragmentViewBinder

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentMapsBinding.inflate(inflater, container, false)
        binding.viewBinder = fragmentViewBinder
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = getViewModel<MapsViewModel>()
        viewLifecycleScope.launch(Dispatchers.IO) {
            viewModel.getMaps().let {
                onMain {
                    fragmentViewBinder.bind(it)
                }
            }
        }

        return binding.root
    }

    companion object {
        fun newInstance() = MapsFragment()
    }

}
