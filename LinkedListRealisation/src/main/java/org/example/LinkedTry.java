package org.example;

public class LinkedTry {

    Node Head;
    Node Tail;
    LinkedTry()
    {
        this.Head = null;
        this.Tail = null;
    }

    public void add(int i)
    {
        Node tmp = new Node(i);
        if(Head == null)
        {
            Head = tmp;
            Tail = tmp;
        }
        else {
            Tail.next = tmp;
            tmp.prev = Tail;
            Tail = tmp;
        }
    }
    public void print()
    {
        Node tmp1 = Head;
        while(tmp1 != null)
        {
            System.out.println(tmp1.data);
            tmp1 = tmp1.next;
        }
    }

    public void delete(int data)
    {
        Node tmp1 = Head;
        if (tmp1 != null) {
            while(tmp1.data != data)
            {
                tmp1 = tmp1.next;
            }
            if (tmp1 == Head && tmp1 == Tail)
            {
                Head = null;
                Tail = null;
            } else if (tmp1 == Head) {
                Head = tmp1.next;
                Head.prev = null;
            } else if (tmp1 == Tail) {
                Tail = tmp1.prev;
                Tail.next = null;
            } else {
                tmp1.next.prev = tmp1.prev;
                tmp1.prev.next = tmp1.next;
            }
        }
    }

}
