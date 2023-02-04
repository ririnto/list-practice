from typing import Optional


class _Node:
    def __init__(self, value):
        self.value = value
        self.next: Optional[_Node] = None


class SingleLinkedList:
    __head = _Node(value=None)
    __size = 0

    def get_size(self):
        return self.__size

    def append(self, value):
        if self.__size == 0:
            node = self.__head
        else:
            node = self.__get_node_at(index=self.__size - 1)

        node.next = _Node(value=value)

        self.__size += 1

    def append_left(self, value):
        node = _Node(value=value)

        node.next = self.__head.next
        self.__head.next = node

        self.__size += 1

    def insert(self, index, value):
        if index == 0:
            self.append_left(value=value)
        elif index == self.__size:
            self.append(value=value)
        else:
            prev_node = self.__get_node_at(index=index - 1)
            node = _Node(value=value)

            node.next = prev_node.next
            prev_node.next = node

            self.__size += 1

    def get(self, index):
        return self.__get_node_at(index=index).value

    def remove_at(self, index):
        self.__check_index(index=index)

        if index == 0:
            prev_node = self.__head
        else:
            prev_node = self.__get_node_at(index=index - 1)

        node = prev_node.next
        prev_node.next = node.next

        self.__size -= 1

        return node.value

    def __get_node_at(self, index):
        self.__check_index(index=index)

        node = self.__head
        for _ in range(index + 1):
            node = node.next

        return node

    def __check_index(self, index):
        if not (0 <= index < self.__size):
            raise IndexError
