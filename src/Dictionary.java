/**
 * Dictionary class used for storing duplicate game instances for the AI
 */
public class Dictionary implements DictionaryADT {
    private final int table_size;
    private int size; // number of items in dictionary
    private final Node[] Table;

    /**
     * Constructor initializes the Dictionary and its size
     * @param size size of dictionary
     */
    public Dictionary(int size){
        this.table_size = size;
        Table = new Node[size];
    }

    /**
     * Polynomial hash function used to hash keys
     * @param key key value in the Record object
     * @return hashed value
     */
    private int Hash(String key){
        final int p = 37, m = table_size; // p is a prime number and m is the length of array we mod by.
        int key_length = key.length();
        int hash = key.charAt(key_length-1);
        for (int i = key_length -2; i>=0; i--){
            hash = (hash * p + (int)key.charAt(i)) % m;
        }
        return hash;
    }

    /**
     * Puts an object into the Dictionary
     * @param rec Record to be put
     * @return 1 for collision, 0 for no collision.
     * @throws DuplicatedKeyException duplicate keys
     */
    @Override
    public int put(Record rec) throws DuplicatedKeyException {
        int pos = Hash(rec.getKey());
        Node Head = Table[pos];

        if (Head == null){
            size ++;
            Table[pos] = new Node(rec);
            return 0; // If the head of linked list was null, it means we faced no collision.
        }

        while (Head != null){
            Record Current_Record = (Record) Head.getRec();
            // Check for duplicates
            if (Current_Record.getKey().equals(rec.getKey())) throw new DuplicatedKeyException("Duplicate Key");
            if (Head.getNext() == null) break;
            Head = Head.getNext();
        }
        size ++;
        Head.setNext(new Node<>(rec));
        return 1; // If the head was not null, it means that a collision occurred.
    }

    /**
     * Removes a key from the dictionary
     * @param key key to be removed
     * @throws InexistentKeyException key does not exist
     */
    @Override
    public void remove(String key) throws InexistentKeyException {
        int pos = Hash(key);
        Node Head = Table[pos];
        Node prev = null; // temporary previous node

        if (Head == null) throw new InexistentKeyException("Inexistent key"); // Checks if key exists.

        while(Head != null){
            Record current_record = (Record) Head.getRec();
            if(current_record.getKey().equals(key)){ // Checking if the two keys are equal.
                break;
            }
            prev = Head;
            Head = Head.getNext();
        }
        size --;

        if (prev != null) {prev.setNext(Head.getNext());}  //Cutting connection from the removed node

    }

    /**
     * Gets a desired Record object given its key.
     * @param key key
     * @return Record object
     */
    @Override
    public Record get(String key) {

        int pos = Hash(key);
        Node Head = Table[pos];

        if (Head == null) return null; // First check if the initial head is null

        while(Head != null){
            Record Current_Record = (Record) Head.getRec();
            if(Current_Record.getKey().equals(key)) break;
            Head = Head.getNext(); // iterates through linked list until key is found.
        }
        if (Head == null) return null; // If key isn't found it must be null
        return (Record) Head.getRec();

    }

    /**
     * Function for getting number of elements in dictionary
     * @return size of dictionary
     */
    @Override
    public int numRecords() {
        return this.size;
    }
}
