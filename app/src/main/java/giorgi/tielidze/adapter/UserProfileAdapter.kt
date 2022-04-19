package giorgi.tielidze.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import giorgi.tielidze.data.UserProfile
import giorgi.tielidze.databinding.UserProfileItemBinding

class UserProfileAdapter :
    PagingDataAdapter<UserProfile, UserProfileAdapter.UserProfileViewHolder>(UserProfileComparator) {

    inner class UserProfileViewHolder(private val binding: UserProfileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.txtName.text = "Name: ${getItem(absoluteAdapterPosition)?.name}" ?: ""
            binding.txtBloodGroup.text =
                "Blood Group: ${getItem(absoluteAdapterPosition)?.bloodGroup}" ?: ""
            binding.txtMobileNumber.text =
                "Mobile Number: ${getItem(absoluteAdapterPosition)?.mobileNumber}" ?: ""
            binding.txtLocation.text =
                "Location: ${getItem(absoluteAdapterPosition)?.location}" ?: ""
            binding.txtLastDonatedDate.text =
                "Last blood donate date: ${getItem(absoluteAdapterPosition)?.lastDonatedDate}" ?: ""
            binding.txtWeight.text = "Weight: ${getItem(absoluteAdapterPosition)?.weight}" ?: ""
        }
    }

    override fun onBindViewHolder(holder: UserProfileViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {
        return UserProfileViewHolder(
            UserProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    object UserProfileComparator : DiffUtil.ItemCallback<UserProfile>() {
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }
    }
}

