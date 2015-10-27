package uk.co.honda.hi.value.ssdif;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author uinxh
 *
 * Class that holds node information for SSDif XML Creator.
 * SSDif key consists of an order, sequence and name array, plus the actual value of the node.
 * It is used to put a node into a SortedSet where the ordering of the Set is determined by 
 * the order ints and additionally the sequence ints.
 * 
 * Order determines where in the set the Key is placed. It is an int array. e.g. [2][3] Means
 * this key would be in part 2 off the root, and then element number 3 in that section.-
 * 
 * <part1/>
 * <part2>
 * 		<element1/>
 * 		<element2/>
 * 		<element3>this key's value</element3>
 * </part2>
 * 
 * Sequences are used if there are multiple nodes, e.g. 2 x element3's then one would have sequence id 1, 
 * the other sequence id 2. Sequences are an int array -1 means no sequence at a particular level The sequence
 * array is the same size as the order array, hence any level can have a sequence id-
 * for sequence array [-1][1] (i.e. no sequence at the 'part' level, and then the 'element' is sequenced.   
 * 
 * <part1/>
 * <part2>
 * 		<element1/>
 * 		<element2/>
 * 		<element3>this key's 1 value</element3>
 * 		<element3>this key's 2 value</element3> 
 * </part2>
 * 
 * The name array describes the nodes at the various levels and for the above example would contain-
 * 
 * [part2][element3]
 *
 */


public class SSDifKey implements Comparable{

	private int[] order;
	
	public static final int SAMEORDERING=-1;
	
	private String[] names;
	private String value;
	private int hashCode;
	private int[] sequence=null;
	
	/**
	 * If sequences are not to be used, the ordering of the Keys is determined just by 
	 * the order array then use this.
	 * @param order int[] determines order of the elements in any SortedSet.
	 * @param names String[] names the levels of the int array.
	 * @param value the value of this node.
	 */
	public SSDifKey(int[] order, String[] names, String value){
		this.order = order;
		this.names= names;
		this.value = XMLNormaliser.escapeString(value);
		this.sequence=null;
	}

	/**
	 * Use if sequences are required. This allows for multiple nodes at a level. i.e. two keys have the same
	 * ordering, but one would have a differing sequence specifying the actual placement of the keys.
	 * @param order int[] Order holds the ordering of the node, e.g. [5][3] = fifth node off root, 3 third child. 
	 * @param sequence int[] if no sequence is specified at a level then -1 should be specified.
	 * @param names String[] names of the order levels. 
	 * @param value String value of the node
	 */
	public SSDifKey(int[] order, int[] sequence, String[] names, String value){
		this(order, names, value);		
		this.sequence=sequence;
	}
	
	
	public String toString(){
		return value;
	}
	

	public String getValue(){
		return value;
	}
	
	/**
	 * Used in finding the difference in ordering between this key and and a compare key.
	 * Will return the level which the argument differs in ordering, starting from the root (0).
	 * For each matching level in the order array the sequences are checked and any differences would
	 * return the level. 
	 * @param compare SSDifKey
	 * @return  int the index (or level) where the two keys differ either by order or sequence.
	 */
	public int findDifferentOrder(SSDifKey compare){
		
		int localLevel, compareLevel, depth=this.getOrdering().length;
		
		for (int level=0; level<depth;level++){
			localLevel = this.getOrderAt(level);
			compareLevel = compare.getOrderAt(level);
			if (localLevel!=compareLevel){
				return level;
			}
			// same at SSDif level...
			// check sequences...
			if (this.getSequenceAt(level)!=compare.getSequenceAt(level)){
				// point to lower level...
				return (level);
			}
		}
		return SAMEORDERING;
	}
	

	public int getOrderAt(int level){
		if (level<0){
			return -1;
		}
		
		if (level<order.length){
			return order[level];
		}else{
			return -1;
		}
	}
	
	public int[] getOrdering(){
		return order;
	}
	
	/**
	 * Will return a sequence number, if no sequence is
	 * specified then '-1' will be returned.
	 * @param level
	 * @return
	 */
	public int getSequenceAt(int level){
		
		if (sequence==null){
			return -1;
		}
		
		if (level>=sequence.length || level<0){
			return -1;
		}else{
			return sequence[level];
		}
	}
	
	public String getNodeNameAt(int level){
		if (level<0){
			return null;
		}
		
		if (level<names.length){
			return names[level];
		}else{
			return null;
		}		
	}
	
	public int hashCode(){
		if (hashCode==0){
			int multiplier = 1574;
			for (int i=0;i<order.length;i++){
				hashCode+=(order[i]*multiplier);
				multiplier = (multiplier / 100);
				if (multiplier==0){
					multiplier=1;
				}
				if (sequence!=null){
					hashCode+=(sequence[i]);	
				}
			}
		}
		return hashCode;
	}

	public boolean equals(Object o){
		
		if (this.compareTo(o)==0){
			return true;
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o != null) {
			if (o instanceof SSDifKey) {
				SSDifKey tmp = (SSDifKey) o;
				int homeLevel, remoteLevel;

				for (int level = 0; level < this.order.length; level++) {
					homeLevel = this.getOrderAt(level);
					remoteLevel = tmp.getOrderAt(level);
					if (homeLevel != remoteLevel) {
						if (homeLevel > remoteLevel) {
							return 1;
						} else {
							return -1;
						}
					}
					// same object through ordering, need sequence taken into account...	
					homeLevel=this.getSequenceAt(level);
					remoteLevel=tmp.getSequenceAt(level);
					if (homeLevel != remoteLevel) {
						if (homeLevel> remoteLevel){
							return 1;
						}else{
							return -1;
						}
					}
				}				
			}
		}
		// null / not matching object object is always less...
		return -1;
	}
	
	public static void main(String[] args){
		
		int[] order = {1,1,9,9,9,19};
		String[] names={"","",""};
		SSDifKey first = new SSDifKey(order,names, "first");
		
		int[] order1= {3,2};
		SSDifKey last = new SSDifKey(order1, names, "last");
		
		System.out.println("first: "+first.hashCode());
		System.out.println("last: "+last.hashCode());
		System.out.println("first+last= "+first.equals(last));
		System.out.println("last+first= "+last.equals(first));
		
		int[] order2= {2,1,9,9,9,20};
		SSDifKey middle = new SSDifKey(order2, names, "middle");

 		SortedSet data = new TreeSet();
		data.add(last);
		data.add(first);
		data.add(middle);
		 
		
		Iterator it = data.iterator();
		SSDifKey key;		
		while (it.hasNext()){
			key = (SSDifKey)it.next();
			System.out.println(key.getValue());
		}
		
	}
	
}
