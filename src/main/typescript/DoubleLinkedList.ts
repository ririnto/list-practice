class _Node<T> {
    _value: T
    _prev: _Node<T>
    _next: _Node<T>

    constructor(value: T) {
        this._value = value
    }
}

export class DoubleLinkedList<T> {
    __head: _Node<T> = new _Node<T>(null)
    __tail: _Node<T> = new _Node<T>(null)
    __size: number = 0

    constructor() {
        this.__head._next = this.__tail
        this.__tail._next = this.__head
    }

    getSize() {
        return this.__size
    }

    append(value: T) {
        const newNode: _Node<T> = new _Node(value)
        newNode._prev = this.__tail._prev
        newNode._next = this.__tail
        this.__tail._prev._next = newNode
        this.__tail._prev = newNode

        this.__size++
    }

    appendLeft(value: T) {
        const newNode: _Node<T> = new _Node(value)
        newNode._prev = this.__head
        newNode._next = this.__head._next
        this.__head._next._prev = newNode
        this.__head._next = newNode

        this.__size++
    }

    insert(index: number, value: T) {
        if (index === 0) {
            this.appendLeft(value)
        } else if (index === this.__size) {
            this.append(value)
        } else {
            const nextNode: _Node<T> = this.__getNodeAt(index)
            const node: _Node<T> = new _Node(value)

            nextNode._prev._next = node
            node._prev = nextNode._prev
            node._next = nextNode
            nextNode._prev = node

            this.__size++
        }
    }

    get(index: number) {
        return this.__getNodeAt(index)._value
    }

    removeAt(index: number) {
        const node: _Node<T> = this.__getNodeAt(index)
        node._prev._next = node._next
        node._next._prev = node._prev

        this.__size--

        return node._value
    }

    __getNodeAt(index: number) {
        this.__checkIndex(index)

        let node: _Node<T>
        if (index < this.__size / 2) {
            node = this.__head
            for (let i = 0; i < index + 1; i++) {
                node = node._next
            }
        } else {
            node = this.__tail
            for (let i = 0; i < this.__size - index; i++) {
                node = node._prev
            }
        }

        return node
    }

    __checkIndex(index: number) {
        if (index < 0 || this.__size <= index) {
            throw new RangeError()
        }
    }
}
