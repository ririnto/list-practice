class _Node {
    _value
    _next

    constructor(value) {
        this._value = value
    }
}

export class SingleLinkedList {
    __head = new _Node(null)
    __size = 0

    getSize() {
        return this.__size
    }

    append(value) {
        let node = this.__head
        while (node._next !== this.__tail) {
            node = node._next
        }

        const newNode = new _Node(value)

        newNode._next = node._next
        node._next = newNode

        this.__size++
    }

    appendLeft(value) {
        const node = this.__head

        const newNode = new _Node(value)

        newNode._next = node._next
        node._next = newNode

        this.__size++
    }

    insert(index, value) {
        if (index === 0) {
            this.appendLeft(value)
        }

        if (index === this.__size) {
            this.append(value)
        }

        this._checkIndex(index)

        let node = this.__head
        for (let i = 0; i < index; i++) {
            node = node._next
        }

        const newNode = new _Node(value)

        newNode._next = node._next
        node._next = newNode

        this.__size++
    }

    get(index) {
        this._checkIndex(index)

        let node = this.__head
        for (let i = 0; i < index + 1; i++) {
            node = node._next
        }

        return node._value
    }

    removeAt(index) {
        this._checkIndex(index)

        let pNode = this.__head
        for (let i = 0; i < index; i++) {
            pNode = pNode._next
        }

        const node = pNode._next
        pNode._next = node._next

        this.__size--

        return node._value
    }

    _checkIndex(index) {
        if (index < 0 || this.__size <= index) {
            throw new RangeError()
        }
    }
}
