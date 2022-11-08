package cn.jiangker.flexlist

import android.view.View
import android.widget.TextView
import cn.jiangker.lib.flexlist.FlexHolder

class ItemTwoHolder(itemView: View) : FlexHolder<ItemTwo>(itemView) {

    private val tvLeft by lazy(LazyThreadSafetyMode.NONE) { itemView.findViewById<TextView>(R.id.tv_left) }
    private val tvRight by lazy(LazyThreadSafetyMode.NONE) { itemView.findViewById<TextView>(R.id.tv_right) }

    override fun layoutParams() = R.layout.item_two

    override fun bindData(data: ItemTwo) {
        tvLeft.text = data.item1
        tvRight.text = data.item2
        itemView.setOnClickListener {
            getInjectObj(ItemTwoClick::class.java)?.click(data)
        }
    }

}

class ItemTwo(
    val item1: String,
    val item2: String
)

interface ItemTwoClick {
    fun click(itemTwo: ItemTwo)
}