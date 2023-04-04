package uz.gita.contactappwithroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappwithroom.databinding.ListItemBinding
import uz.gita.contactappwithroom.entities.UserData

class MyAdapter :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var list: MutableList<UserData> = ArrayList()

    private var onItemClick: ((UserData) -> Unit)? = null

    fun setOnItemClickListener(listener: (UserData) -> Unit) {
        onItemClick = listener
    }

    fun refreshData(data: List<UserData>) {
        list.clear()
        list.addAll(data)
    }

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }

        fun bind(position: Int) {
            binding.apply {
                txtName.text = list[position].name
                txtNumber.text = list[position].number
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}