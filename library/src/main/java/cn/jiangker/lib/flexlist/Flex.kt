package cn.jiangker.lib.flexlist

import android.view.LayoutInflater
import android.view.ViewGroup
import java.lang.reflect.Constructor
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

class Flex(
    private val holderClass: Class<out FlexHolder<Any>>
) {

    private val dataClass: Class<Any>

    private val constructor: Constructor<FlexHolder<Any>>

    private val inflateMethod: Method

    val dataKey: Int
        get() = dataClass.hashCode()

    init {
        val parameterizedType = holderClass.genericSuperclass as ParameterizedType
        val arguments = parameterizedType.actualTypeArguments
        dataClass = arguments[0] as Class<Any>
        constructor = holderClass.declaredConstructors[0] as Constructor<FlexHolder<Any>>
        val params = constructor.parameterTypes
        val clazz = params[0]
        inflateMethod = clazz.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        inflateMethod.isAccessible = true
    }

    fun createViewHolder(parent: ViewGroup): FlexHolder<Any> {
        return constructor.newInstance(
            inflateMethod.invoke(
                null,
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}