package Search;

/**
 * Created by js982 on 2017/9/19.
 */
public class Hash {
  private static final int Null = -32768;

  void insert(HashTable hashTable, int key){
    int addr = hashTable.hash(key);
    while(hashTable.getElem()[addr] != Null)
      addr = (addr + 1) % hashTable.getCount();
    hashTable.getElem()[addr] = key;
  }

  public int search(HashTable hashTable, int key){
    int addr = hashTable.hash(key);
    while(hashTable.getElem()[addr] != key){
      addr = (addr + 1) % hashTable.getCount();
      if(hashTable.getElem()[addr] == Null || addr == hashTable.hash(key)){
        return -1;
      }
    }
    return addr;
  }

  public static void main(String[] args){
    int[] list = new int[]{12, 67, 56, 16, 25, 37, 22, 29, 15, 47, 48, 34};
    HashTable hashTable = new HashTable(list.length);
    Hash hash = new Hash();
    for(int i = 0; i < list.length; i++){
      hash.insert(hashTable, list[i]);
    }
    int i = hash.search(hashTable, 22);
  }
}

class HashTable{
  private static final int Null = -32768;

  private int[] elem;
  private int count;

  public HashTable(int length){
    this.count = length;
    this.elem = new int[length];
    for(int i = 0; i < length; i++){
      elem[i] = Null;
    }
  }

  public int hash(int key){
    return key % count;
  }

  public int[] getElem() {
    return elem;
  }

  public void setElem(int[] elem) {
    this.elem = elem;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }


}
