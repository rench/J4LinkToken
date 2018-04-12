package xin.lowang.token.link.kotlin.web

import java.util.*


data class UserInfo(var id: Long, var name: String, var age: Int)

fun main(args: Array<String>) {
    //test1()
    //test2()
    //test3()
    //test4(1,2)
    //test5()
    //test6()
    //test7()
    //test8()
    //test9()
    test10()
}

fun test1() {
    var a: Byte = 2
    println(a)
    var str = "你好呢"
    println(str)
    var b: String? = null;
    println(b?.length)
    if (b === null) {
        println("b is null")
    }
    println("abc".let { "abc" + "cccc" })
}

fun test2() {
    var u = UserInfo(1, "lowang", 28)
    u.let { println(it) }
    u.age = 12;
    u.also { println(it.age) }
}


fun test3() {
    fun add(a: Int, b: Int) = a + b
    fun sub(a: Int, b: Int): Int {
        return a - b
    }

    fun printStr(a: String, b: String): Unit = println(a + ',' + b)
    add(1, 2).let { println(it) }
    sub(12, 10).let { println(it) }
    printStr("hello", "world")
}

fun test4(vararg numbers: Int) {
    var sumFn: (Int, Int) -> Int = { x, y -> x + y }
    numbers.forEach { sumFn(it, it).let { println(it) } }
}

fun test5() {
    var age: String? = null
    val age1 = age?.toInt()
    println(age1)
    println(age!!.toInt())
}

fun test6() {
    for (i in 1..10) print(i)
    println("------------")
    for (i in 1..20 step 5) print(i)
    println("------------")
    for (i in 10 downTo -10 step 2) print(i)
    println("------------")
    for (i in 2 until 20 step 3) print(i) //不包含20
    println("------------")

    var items = listOf("12", 2, 3, 4, 5, 6, "92", '0')
    items.let { print(it) }
    print('\n')
    for (i in items) {
        println(i)
        if (i is String)
            println("长度" + i.length)

    }
}

fun test7() {
    var map = HashMap<String, String>();
    map.put("a", "A")
    map.put("b", "B")
    map["c"] = "C"

    var map2 = mapOf("d" to "D", "e" to "E") //read only
    map.putAll(map2)
    for ((k, v) in map) {
        println("$k's upper case is $v")
    }

    map.keys.toList().joinToString(",").let { println(it) }

}

fun test8() {
    fun String.haha() {
        println(this)
    }
    "abc".haha()

    fun Any?.toString(): String {
        if (this == null) return "null"
        return toString()
    }

    println(null.toString())
}

fun test9() {
    data class Person(val name: String) {
        var age: Int = 0
    }

    var p1 = Person("张三")
    p1.age = 20
    var p2 = Person(name = "张三")
    p2.age = 21
    println(p1===p2)
    println(p1.equals(p2))

    var n1 = 1
    var n2 = "1"
    var n3 = "1"
    var n4 = arrayOf("1","")
    var n5 = n4.joinToString("")
    println(n1.equals(n2))
    println(n2==n3)
    println(n2===n3)
    println(n2===n5)
}

fun test10(){
    class Box<T>(t: T) {
        var value = t
    }
    var box = Box(1)
    println(box.value)
    var box2 = Box("123123")
    println(box2.value)
    var box3 = Box<Byte>(1)
    println(box3.value)
}
