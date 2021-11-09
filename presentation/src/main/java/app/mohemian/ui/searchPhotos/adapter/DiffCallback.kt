package app.mohemian.ui.searchPhotos.adapter

import androidx.recyclerview.widget.DiffUtil
import app.mohemian.domain.entity.Photo

class DiffCallback : DiffUtil.ItemCallback<Photo>() {

  override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
    oldItem.id == newItem.id
            && oldItem.server == newItem.server
            && oldItem.secret == newItem.secret
  override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
    oldItem.id == newItem.id
            && oldItem.server == newItem.server
            && oldItem.secret == newItem.secret
}
