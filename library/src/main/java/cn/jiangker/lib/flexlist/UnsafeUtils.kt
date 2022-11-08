package cn.jiangker.lib.flexlist

import android.annotation.SuppressLint
import java.lang.Exception
import java.lang.reflect.Method

@SuppressLint("DiscouragedPrivateApi")
object UnsafeUtils {

    private var unSafe: Any?
    private var method: Method?

    init {
        try {
            val unsafe = Class.forName("sun.misc.Unsafe")
            val filed = unsafe.getDeclaredField("theUnsafe")
            unSafe = filed.get(null)
            method = unsafe.getDeclaredMethod("allocateInstance", Class::class.java)
        } catch (e: Exception) {
            unSafe = null
            method = null
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getObj(clazz: Class<T>): T? {
        return method?.invoke(unSafe, clazz) as T?
    }

}