package cn.jay.ch1_base.list;

import java.util.Iterator;

/**
 * 模仿 java.util.LinkedList ， 使用双链表
 */
public class LinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    private class Node<E> {
        E element;
        Node<E> pre;
        Node<E> next;

        Node(E element, Node<E> pre, Node<E> next) {
            this.element = element;
            this.pre = pre;
            this.next = next;
        }
    }

    @Override
    public void add(E element) {
        if (size == 0) {
            head = new Node<E>(element, null, null);
            tail = head;
        } else {
            Node<E> newNode = new Node<E>(element, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        Node<E> resultNode = nodeOf(index);
        return resultNode.element;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> resultNode = nodeOf(index);
        E result = resultNode.element;
        resultNode.element = element;
        return result;
    }

    @Override
    public boolean remove(E e) {
        if (e == null) {
            for (Node<E> node = head; node != null; node = node.next) {
                if (node.element == null) {
                    unlink(node);
                    return true;
                }
            }
        } else {
            for (Node<E> node = head; node != null; node = node.next) {
                if (node.element.equals(e)) {
                    unlink(node);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void remove(int index) {
        checkElementIndex(index);
        unlink(nodeOf(index));
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public int indexOf(E e) {
        int index = 0;
        if (e == null) {
            for (Node<E> headTemp = head; headTemp != null; headTemp = headTemp.next) {
                if (headTemp.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> headTemp = head; headTemp != null; headTemp = headTemp.next) {
                if (headTemp.element.equals(e))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        int index = size-1;
        if (e == null) {
            for (Node<E> tailTemp = tail; tailTemp != null; tailTemp = tailTemp.pre) {
                if (tailTemp.element == null)
                    return index;
                index--;
            }
        } else {
            for (Node<E> tailTemp = tail; tailTemp != null; tailTemp = tailTemp.pre) {
                if (tailTemp.element.equals(e))
                    return index;
                index--;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public List<E> subList(int start, int end) {
        checkElementIndex(start);
        checkElementIndex(end-1);

        List<E> newList = new LinkedList<>();
        for (;start < end; start++){
            newList.add(get(start));
        }

        return newList;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> headTemp = head;

            @Override
            public boolean hasNext() {
                return headTemp != null;
            }

            @Override
            public E next() {
                E element = headTemp.element;
                headTemp = headTemp.next;
                return element;
            }
        };
    }

    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private Node<E> nodeOf(int index) {
        Node<E> result;
        if (index < (size >> 1)) {
            result = head;
            for (int i = 0; i < index; i++)
                result = result.next;
        } else {
            result = tail;
            for (int i = (size - index - 1); i > 0; i--)
                result = result.pre;
        }
        return result;
    }

    private E unlink(Node<E> node) {
        final E element = node.element;
        final Node<E> pre = node.pre;
        final Node<E> next = node.next;

        // 处理pre侧
        if (pre == null) {
            head = next;
        } else {
            pre.next = next;
            node.pre = null;
        }

        // 处理next侧
        if (next == null) {
            tail = pre;
        } else {
            next.pre = pre;
            node.next = null;
        }

        node.element = null;
        size--;
        return element;
    }
}
