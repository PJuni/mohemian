package app.mohemian.ui.searchPhotos

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import app.mohemian.R
import app.mohemian.databinding.FragmentSearchPhotosBinding
import app.mohemian.ui.base.BaseFragment
import app.mohemian.ui.searchPhotos.adapter.SearchPhotoAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import java.util.Timer
import kotlin.concurrent.schedule

@InternalCoroutinesApi
@AndroidEntryPoint
class SearchPhotosFragment : BaseFragment(R.layout.fragment_search_photos) {

    private var timer = Timer()
    private val delay: Long = 500

    private val searchAdapter = SearchPhotoAdapter { photo ->
        viewModel.triggerCommand(SearchPhotosViewModel.Command.ShowPhotoDetails(photo))
    }

    private val viewBinding by viewBinding<FragmentSearchPhotosBinding>()
    private val viewModel by viewModels<SearchPhotosViewModel>()

    override fun bindView(): Unit = with(viewBinding) {

        lifecycleScope.launchWhenCreated {

            with(recyclerSearchPhotos) {
                addItemDecoration(
                    DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
                )
                addItemDecoration(
                    DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                )
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = searchAdapter
            }

            editSearchPhotos.addTextChangedListener {
                timer.cancel()
                timer = Timer()
                timer.schedule(delay) {
                    viewModel.triggerCommand(SearchPhotosViewModel.Command.FetchPhotos(it?.toString()))
                }
            }
        }
    }

    override fun bindData(): Unit = with(viewLifecycleOwner.lifecycleScope) {
        launchWhenCreated {
            viewModel.triggerCommand(SearchPhotosViewModel.Command.FetchPhotos())
            viewModel.actionStream.getFlow().collect { action ->
                when (action) {
                    is SearchPhotosViewModel.Action.ShowPhotoDetails -> findNavController().navigate(
                        SearchPhotosFragmentDirections.actionFragmentSearchPhotosToFragmentPhotoDetail(
                            action.photo
                        )
                    )
                    is SearchPhotosViewModel.Action.PhotoFetched -> searchAdapter.submitList(action.photos)
                }
            }
        }
    }
}