from typing import Optional


class _Node:
    def __init__(self, value):
        self.value = value
        self.prev: Optional[_Node] = None
        self.next: Optional[_Node] = None


class SingleLinkedList:
    __head = _Node(value=None)
    __tail = _Node(value=None)
    __size = 0

    def __init__(self):
        self.__head.next = self.__tail
        self.__tail.prev = self.__head

    def get_size(self):
        return self.__size

    def append(self, value):
        new_node = _Node(value=value)
        new_node.prev = self.__tail.prev
        new_node.next = self.__tail
        self.__tail.prev.next = new_node
        self.__tail.prev = new_node

        self.__size += 1

    def append_left(self, value):
        new_node = _Node(value=value)
        new_node.prev = self.__head
        new_node.next = self.__head.next
        self.__head.next.prev = new_node
        self.__head.next = new_node

        self.__size += 1

    def insert(self, index, value):
        if index == 0:
            self.append_left(value=value)
        elif index == self.__size:
            self.append(value=value)
        else:
            next_node = self.__get_node_at(index=index)
            node = _Node(value=value)

            next_node.prev.next = node
            node.prev = next_node.prev
            node.next = next_node
            next_node.prev = node

            self.__size += 1

    def get(self, index):
        return self.__get_node_at(index=index).value

    def remove_at(self, index):
        node = self.__get_node_at(index=index)
        node.prev.next = node.next
        node.next.prev = node.prev

        self.__size -= 1

        return node.value

    def __get_node_at(self, index) -> _Node:
        self.__check_index(index=index)

        if index < self.__size / 2:
            node = self.__head
            for _ in range(index + 1):
                node = node.next
        else:
            node = self.__tail
            for _ in range(self.__size - index):
                node = node.prev

        return node

    def __check_index(self, index):
        if not (0 <= index < self.__size):
            raise IndexError
