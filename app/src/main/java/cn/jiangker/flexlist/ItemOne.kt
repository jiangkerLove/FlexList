package cn.jiangker.flexlist

import android.view.View
import android.widget.TextView
import cn.jiangker.lib.flexlist.FlexHolder

class ItemOneHolder(itemView: View) : FlexHolder<ItemOne>(itemView) {

    private val tvCenter by lazy(LazyThreadSafetyMode.NONE) { itemView.findViewById<TextView>(R.id.tv_center) }

    override fun layoutParams() = R.layout.item_one

    override fun bindData(data: ItemOne) {
        tvCenter.text = data.item
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