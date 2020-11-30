package datastructure.linkedlist.roundsinglelinkedlist;

public class Boy {

    private int id;
    private Boy next;

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int id) {
        this.id = id;
    }
}
