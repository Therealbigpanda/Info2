package u3a3;

/**
 *
 * @author AWilli
 */
public class KD {

    /**
     *
     */
    public static String tree;

    /**
     *
     */
    public int baumlänge = 0;
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
        if(tree.length() == 0) throw new ParseException("empyString", 0);
        if(tree.length() > 1 && tree.charAt(1) != '(') throw new ParseException("noRoot", 0);
        tree(0); 
    }

    /**
     *
     * @param index: where the tree is located in the string
     * @return if tree has one node: "1"; if tree hast more nodes: "3 + next(index+2)"
     * @throws ParseException if there is an error in the tree format
     */
    public static int tree(int index) throws ParseException
    {
        if(tree.charAt(index) == '-')
        {	
            if(tree.length() > index+1 && tree.charAt(index+1) == '(')
                throw new ParseException("noRoot but children", 0);		
            return 1;
        }
        
        if(!Character.isUpperCase(tree.charAt(index))) throw new ParseException("illegal node", index);
        
        if(tree.length() > index+1 && tree.charAt(index+1) == '(')
        {
            if(tree.length() < index+4) throw new ParseException("treeing ends abruptly", index);
            int subelements = next(index+2);
            if(tree.charAt(subelements+index+2) == ')')
                return 3 + subelements;
            else throw new ParseException("no closing braces", index);
        }
        return 1;
    }
    
    /**
     *
     * @param index: where the subtree is located in the string
     * @return
     * @throws ParseException
     */
    public static int next(int index) throws ParseException
    {
        int subelements = tree(index);
        while(tree.charAt(index+subelements) == ',')
        {
            subelements += tree(index+subelements+1) + 1;            
        }
        
        return subelements;
    }
}