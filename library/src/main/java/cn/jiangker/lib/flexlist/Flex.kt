package cn.jiangker.lib.flexlist

import java.lang.reflect.ParameterizedType

class Flex(
    val holderClass: Class<out FlexHolder<Any>>
) {

    val dataClass: Class<Any>

    val dataKey: Int
        get() = dataClass.hashCode()

    init {
        val parameterizedType = holderClass.genericSuperclass as ParameterizedType
        val arguments = parameterizedType.actualTypeArguments
        dataClass = arguments[0] as Class<Any>
    }
}