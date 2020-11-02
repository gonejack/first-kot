package main

class May(private var name: String) {
    val isEmpty: Boolean
        get() = name.isEmpty()

    val abc: Int = System.currentTimeMillis().div(1e3).toInt()

    val qq: Boolean
        get() = System.currentTimeMillis().div(1e3).toInt() % 2 == 0
}