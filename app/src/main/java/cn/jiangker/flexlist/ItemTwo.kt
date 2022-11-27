package cn.jiangker.flexlist

import cn.jiangker.flexlist.databinding.ItemTwoBinding
import cn.jiangker.lib.flexlist.FlexHolder

class ItemTwoHolder(
    private val binding: ItemTwoBinding
) : FlexHolder<ItemTwo>(binding) {

    override fun bindData(data: ItemTwo) {
        binding.tvLeft.text = data.item1
        binding.tvRight.text = data.item2
        itemView.setOnClickListener {
            getInjectObj(ItemTwoClick::class.java)?.click(data)
        }
    }

}

class ItemTwo(
    val item1: String,
    val item2: String
)

fun interface ItemTwoClick {
    fun click(itemTwo: ItemTwo)
}