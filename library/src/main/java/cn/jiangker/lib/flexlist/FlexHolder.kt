package cn.jiangker.lib.flexlist

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class FlexHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var injectFunc: ((Class<out Any>) -> Any?)? = null

    abstract fun bindData(data: T)

    fun <T : Any> getInjectObj(clazz: Class<T>): T? {
        return injectFunc?.invoke(clazz) as T?
    }

}