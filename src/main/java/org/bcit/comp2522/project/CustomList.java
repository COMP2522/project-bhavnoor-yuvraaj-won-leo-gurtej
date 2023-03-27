package org.bcit.comp2522.project;

public class CustomList<K, V> {
    public Node<K, V> head;


    void add(K key, V value) {
        Node<K, V> temp = new Node<K, V>(key, value);
        if (head == null) {
            head = temp;
        } else {
            Node<K, V> curr = head;
            while(curr.next != null) {
                curr = curr.next;
            }
            curr.next = temp;
        }
    }

    void add(Node<K, V> node){
        //todo: complete over loaded methods
        // this does same thing as above, but user is able to pass in a node obj
        // need to MAKE SURE that node obj isn't already in hashmap, so utilize get method to see if same obj in here
    }

    V get(K key) {
        if (head == null){
            return null;
        } else {
            Node<K, V> curr = head;
            if (curr.key == key){
                return curr.value;
            }
            while (curr.next != null){
                if (curr.key == key){
                    return curr.value;
                }
                curr = curr.next;
            }
        }
        return null;
    }


    K getKey(V value){
        //todo get key from value
        // see above: get val from key if you need help
        return head.key;//this is incorrect just left it to avoid ide errors
    }

    public Node<K,V> getHead() {
         return head;
    }
}
