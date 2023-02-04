package k

class SingleLinkedList<T> {
    private val head: Node<*> = Node(null)

    var size: Int = 0
        private set

    fun append(value: T) {
        val node: Node<*> = if (this.size == 0) {
            this.head
        } else {
            this.getNodeAt(this.size - 1);
        }

        node.next = Node(value = value)

        this.size++
    }

    fun appendLeft(value: T) {
        val node: Node<T> = Node(value = value)

        node.next = this.head.next
        this.head.next = node

        this.size++
    }

    fun insert(
        index: Int,
        value: T
    ) {
        when (index) {
            0 -> {
                this.appendLeft(value = value)
            }

            this.size -> {
                this.append(value = value)
            }

            else -> {
                val prevNode: Node<*> = this.getNodeAt(index = index - 1)
                val node: Node<T> = Node(value = value)

                node.next = prevNode.next
                prevNode.next = node

                this.size++
            }
        }
    }

    @Throws(IndexOutOfBoundsException::class)
    fun get(index: Int): T {
        return this.getNodeAt(index = index).value
    }

    @Throws(IndexOutOfBoundsException::class)
    fun removeAt(index: Int): T {
        this.checkIndex(index = index)

        val prevNode: Node<*> = if (index == 0) {
            this.head
        } else {
            this.getNodeAt(index = index - 1)
        }

        @Suppress("UNCHECKED_CAST")
        val node: Node<T> = prevNode.next as Node<T>
        prevNode.next = node.next

        this.size--

        return node.value
    }

    private fun getNodeAt(index: Int): Node<T> {
        this.checkIndex(index = index)

        var node: Node<*> = this.head
        (0 until index + 1).forEach { _: Int ->
            node = node.next!!
        }

        @Suppress("UNCHECKED_CAST")
        return node as Node<T>
    }

    @Throws(IndexOutOfBoundsException::class)
    private fun checkIndex(index: Int) {
        assert(value = index in 0 until this.size) {
            throw IndexOutOfBoundsException(index)
        }
    }

    private data class Node<T>(val value: T) {
        var next: Node<*>? = null
    }
}
