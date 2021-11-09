package app.mohemian.ui.searchPhotos.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.mohemian.R
import app.mohemian.databinding.ItemPhotoBinding
import app.mohemian.domain.entity.Photo
import app.mohemian.extensions.inflateView
import app.mohemian.util.FlickrImageSize
import app.mohemian.util.FlickrUtil
import com.squareup.picasso.Picasso

class SearchPhotoAdapter(private val itemClicked: (Photo) -> Unit) :
    ListAdapter<Photo, SearchPhotoViewHolder>(AsyncDifferConfig.Builder(DiffCallback()).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPhotoViewHolder =
        SearchPhotoViewHolder(parent.inflateView(R.layout.item_photo), itemClicked)

    override fun onBindViewHolder(holder: SearchPhotoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}

class SearchPhotoViewHolder(itemView: View, private val itemClicked: (Photo) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPhotoBinding.bind(itemView)
    fun bind(photo: Photo) = with(binding) {
        Picasso.with(itemView.context).load(
            FlickrUtil.buildUrl(photo, FlickrImageSize.SMALL)
        ).into(imageItemPhoto)

        itemView.setOnClickListener { itemClicked.invoke(photo) }
    }
}