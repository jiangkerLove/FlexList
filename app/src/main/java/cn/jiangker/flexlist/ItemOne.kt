package cn.jiangker.flexlist

import cn.jiangker.flexlist.databinding.ItemOneBinding
import cn.jiangker.lib.flexlist.FlexHolder

class ItemOneHolder(private val binding: ItemOneBinding) : FlexHolder<ItemOne>(binding) {

    override fun bindData(data: ItemOne) {
        binding.tvCenter.text = data.item
        itemView.setOnClickListener {
            getInjectObj(ItemOneClick::class.java)?.click(data)
        }
    }

}

class ItemOne(
    val item: String,
)

interface ItemOneClick {
    fun click(itemOne: ItemOne)
}