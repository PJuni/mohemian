package app.mohemian.ui.photoDetail

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.mohemian.R
import app.mohemian.databinding.FragmentPhotoDetailBinding
import app.mohemian.extensions.doNothing
import app.mohemian.ui.base.BaseFragment
import app.mohemian.util.FlickrImageSize
import app.mohemian.util.FlickrUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class PhotoDetailFragment : BaseFragment(R.layout.fragment_photo_detail) {

    private val args: PhotoDetailFragmentArgs by navArgs()

    private val viewBinding by viewBinding<FragmentPhotoDetailBinding>()

    override fun bindView(): Unit = with(viewBinding) {
        labelPhotoDetailTitle.text = args.photo.title
        Picasso.with(requireContext()).load(
            FlickrUtil.buildUrl(args.photo, FlickrImageSize.ORIGINAL)
        ).into(imageItemPhoto)

        imagePhotoDetailBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun bindData() = doNothing
}