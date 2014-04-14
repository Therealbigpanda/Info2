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
    private int sizeoflist = 1;

    @Override
    public String toString() {
        if (empty()) {
            return "[]";
        }

        // First Chunk, may not be full
        StringBuilder buf = new StringBuilder("[");
        for (int i = used; i > 0; i--) {
            buf.append(chunks.buffer[i - 1]).append(", ");
        }

        // Following full Chunks
        for (ChunkList iter = chunks.next; iter != null; iter = iter.next) {
            for (int i = ChunkList.chunkSize; i > 0; i--) {
                buf.append(iter.buffer[i - 1]).append(", ");
            }
        }

        buf.delete(buf.length() - 2, buf.length());
        buf.append("]");
        return buf.toString();
    }

    public ChunkedStack() {
        chunks = new ChunkList();
        used = 0;
    }

    @Override
    public boolean empty() {
        return (used == 0 && sizeoflist == 1);
    }

    @Override
    public int peek() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }
        return chunks.buffer[used - 1];
    }

    @Override
    public int pop() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }
        if (used == 1 && sizeoflist > 1) {
            int i = chunks.buffer[used - 1];
            chunks = chunks.removeChunk();
            --sizeoflist;
            used = chunks.buffer.length;
            return i;
        } else {
            return chunks.buffer[--used];
        }
    }

    @Override
    public void push(int number) {
        if (used < chunks.buffer.length) {
            chunks.buffer[used++] = number;
        } else {
            chunks = chunks.addChunk();
            ++sizeoflist;
            used = 0;
            chunks.buffer[used++] = number;
        }

    }

    @Override
    public int size() {
        return (sizeoflist - 1) * chunks.buffer.length + used;
    }
}
