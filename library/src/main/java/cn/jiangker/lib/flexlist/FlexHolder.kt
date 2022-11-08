package cn.jiangker.lib.flexlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class FlexHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var injectFunc: ((Class<out Any>) -> Any?)? = null

    abstract fun layoutParams(): Int

    abstract fun bindData(data: T)

    fun <T : Any> getInjectObj(clazz: Class<T>): T? {
        return injectFunc?.invoke(clazz) as T?
    }

}