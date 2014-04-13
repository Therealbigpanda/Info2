package u6a4;

import java.util.EmptyStackException;

public class ChunkedStack implements u6a2.IStack {
	/**
	 * Linked list of chunks
	 */
	private ChunkList chunks;
	
	/**
	 * the number of slots of the last chunk which are used. 
	 */
	private int used;
	
	public String toString()
	{
		if (empty()) return "[]";
		
		// First Chunk, may not be full
		StringBuffer buf = new StringBuffer("[");
		for(int i = used; i>0;i--)
			buf.append(chunks.buffer[i-1]).append(", ");
		
		// Following full Chunks
		for(ChunkList iter = chunks.next; iter != null; iter = iter.next )
		{
			for (int i=ChunkList.chunkSize; i>0; i--) {
				buf.append(iter.buffer[i-1]).append(", ");
			}
		}
		
		buf.delete(buf.length()-2, buf.length());
		buf.append("]");
		return buf.toString();
	}
		
	public ChunkedStack()
	{
		chunks = new ChunkList();
		used = 0;
	}

	public boolean empty() 
	{

	}

	public int peek() throws EmptyStackException 
	{

	}

	public int pop() throws EmptyStackException 
	{

	}

	public void push(int number) 
	{		

	}

	public int size() 
	{

	}
}
