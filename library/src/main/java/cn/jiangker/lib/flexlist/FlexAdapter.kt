package cn.jiangker.lib.flexlist

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView

class FlexAdapter(
    vararg holders: Class<out FlexHolder<*>>
) : RecyclerView.Adapter<FlexHolder<Any>>() {

    private val dataList = mutableListOf<Any>()
    private var flexArray = SparseArray<Flex>()
    private val injectMap = HashMap<Class<Any>, Any>()

    private val injectFunc: ((Class<out Any>) -> Any?) = {
        val any = injectMap[it]
        any
    }

    init {
        holders.forEach { holder ->
            @Suppress("UNCHECKED_CAST")
            val flex = Flex(holder as Class<out FlexHolder<Any>>)
            flexArray[flex.dataKey] = flex
        }
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList[position]
        return data.javaClass.hashCode()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexHolder<Any> {
        val flex = flexArray[viewType]!!
        val constructor = flex.holderClass.getDeclaredConstructor(View::class.java)
        val newInstance =
            UnsafeUtils.getObj(flex.holderClass) ?: constructor.newInstance(View(parent.context))
        val layoutParams = newInstance.layoutParams()
        val view = LayoutInflater.from(parent.context).inflate(layoutParams, parent, false)
        return constructor.newInstance(view)
    }

    override fun onBindViewHolder(holder: FlexHolder<Any>, position: Int) {
        holder.injectFunc = injectFunc
        holder.bindData(data = dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun update(list: List<Any>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    fun injectFunc(service: Any) {
        val first = service.javaClass.interfaces.first()
        injectMap[first as Class<Any>] = service
    }

}