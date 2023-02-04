class _Node<T> {
    _value: T
    _next: _Node<T>

    constructor(value: T) {
        this._value = value
    }
}

export class SingleLinkedList<T> {
    __head: _Node<T> = new _Node<T>(null)
    __size: number = 0

    getSize() {
        return this.__size
    }

    append(value: T) {
        let node: _Node<T>
        if (this.__size === 0) {
            node = this.__head
        } else {
            node = this.__getNodeAt(this.__size - 1)
        }

        node._next = new _Node(value)

        this.__size++
    }

    appendLeft(value: T) {
        const node: _Node<T> = new _Node<T>(value)

        node._next = this.__head._next
        this.__head._next = node

        this.__size++
    }

    insert(index: number, value: T) {
        if (index === 0) {
            this.appendLeft(value)
        } else if (index === this.__size) {
            this.append(value)
        } else {
            const prevNode: _Node<T> = this.__getNodeAt(index - 1)
            const node: _Node<T> = new _Node(value)

            node._next = prevNode._next
            prevNode._next = node

            this.__size++
        }
    }

    get(index: number) {
        return this.__getNodeAt(index)._value
    }

    removeAt(index: number) {
        this.__checkIndex(index)

        let prevNode: _Node<T>
        if (index === 0) {
            prevNode = this.__head
        } else {
            this.__getNodeAt(index - 1)
        }

        const node = prevNode._next
        prevNode._next = node._next

        this.__size--

        return node._value
    }

    __getNodeAt(index: number) {
        this.__checkIndex(index)

        let node: _Node<T> = this.__head
        for (let i = 0; i < index + 1; i++) {
            node = node._next
        }

        return node
    }

    __checkIndex(index: number) {
        if (index < 0 || this.__size <= index) {
            throw new RangeError()
        }
    }
}
