package u3a3;

public class KD {
    public static String tree;
    /**
     * Parse a "Klammerdarstellung" (KD) of a tree.
     * 
     * <ul>
     * <li>An empty tree is encoded as '-'.</li>
     * <li>A node is encoded as an uppercase letter, i.e. everything accepted by {@link Character#isUpperCase(char)}.</li>
     * <li>Children are appended to the father as a ','-separated list of nodes enclosed in round brackets.</li>
     * </ul> 
     * 
     * @param kd Tree encoded in KD
     * @throws ParseException if the given String is not a valid KD of a tree.
     */
    public static void parse(String kd) throws ParseException{
        tree = kd;
        if(tree.length() == 0) throw new ParseException("empty tree", 0);
        if(tree.length() > 1 && tree.charAt(1) != '(') throw new ParseException("no root", 0);
        baum(0);
    }

    public static boolean baum(int index) throws ParseException{
        
        if(tree.charAt(index) == '-')
        {
            if(tree.length() > index+1 && tree.charAt(index+1) == '(')
                throw new ParseException("emptry tree can not have children", index);
            return true;
        }
        
        if(!knoten(index)) throw new ParseException("invalid node", index);
        if(tree.length() > index+1 && knoten(index) && knoten(index+1)) throw new ParseException("two nodes", index);
        
        if(tree.length() > index+1 && tree.charAt(index+1)=='(')
        {
            if(tree.length() < index+4) throw new ParseException("Tree ends unexpectedly", index+3);
            nachfolger(index+2);
        }
        return false;
    }
    
    public static boolean knoten(int index) throws ParseException{
        if (Character.isUpperCase(tree.charAt(index))) return true;
        else return false;
    }

    public static void nachfolger(int index) throws ParseException{
        baum(index);
        if(tree.charAt(index+1) == ',')
        {
            baum(index+2);
        }
    }
}