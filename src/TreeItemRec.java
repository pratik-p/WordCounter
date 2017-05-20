public class TreeItemRec extends KeyedItem<String> {
	private Integer count;

	public TreeItemRec(Integer key, String c) {
		super(c);
		count = key;
	}

	// public String toString(){
	// return getKey() + " # " + ch;
	// }

	// public String getString(){
	// return ch;
	// }

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer newCount) {
		count = newCount;
	}

	// public void setChar(String c){
	// ch = c;
	// }
}
