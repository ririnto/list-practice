package k

class DoubleLinkedList<T> {
    private val head: Node<*> = Node(null)
    private val tail: Node<*> = Node(null)
    var size = 0
        private set

    init {
        this.head.next = this.tail
        this.tail.next = this.head
    }

    fun append(value: T) {
        val newNode: Node<T> = Node(value = value)
        newNode.prev = this.tail.prev
        newNode.next = this.tail
        this.tail.prev!!.next = newNode
        this.tail.prev = newNode

        this.size++
    }

    fun appendLeft(value: T) {
        val newNode: Node<T> = Node(value = value)
        newNode.prev = this.head
        newNode.next = this.head.next
        this.head.next!!.prev = newNode
        this.head.next = newNode

        this.size++
    }

    fun insert(index: Int, value: T) {
        when (index) {
            0 -> {
                this.appendLeft(value = value)
            }

            this.size -> {
                this.append(value = value)
            }

            else -> {
                val nextNode: Node<T> = getNodeAt(index = index)
                val node: Node<T> = Node(value = value)

                nextNode.prev!!.next = node
                node.prev = nextNode.prev
                node.next = nextNode
                nextNode.prev = node

                this.size++
            }
        }
    }

    operator fun get(index: Int): T {
        return this.getNodeAt(index = index).value
    }

    fun removeAt(index: Int): T {
        val node: Node<T> = this.getNodeAt(index = index)
        node.prev!!.next = node.next
        node.next!!.prev = node.prev

        this.size--

        return node.value
    }

    private fun getNodeAt(index: Int): Node<T> {
        this.checkIndex(index = index)

        var node: Node<*>
        if (index < this.size / 2) {
            node = this.head
            for (i in 0 until index + 1) {
                node = node.next!!
            }
        } else {
            node = this.tail
            for (i in 0 until this.size - index) {
                node = node.prev!!
            }
        }

        @Suppress("UNCHECKED_CAST")
        return node as Node<T>
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || this.size <= index) {
            throw IndexOutOfBoundsException(index)
        }
    }

    private class Node<T>(val value: T) {
        var prev: Node<*>? = null
        var next: Node<*>? = null
    }
}
