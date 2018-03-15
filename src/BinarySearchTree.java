import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class BinarySearchTree {

    public BinarySearchTree( ) {
        root = null;
    }
    public void insert( Comparable x ) {
        root = insert( x, root );
    }
    public void remove( Comparable x ) {
        root = remove( x, root );
    }
    public void removeMin( ) {
        root = removeMin( root );
    }

    public Comparable findMin( ) {
        return elementAt( findMin( root ) );
    }

    public Comparable findMax( ) {
        return elementAt( findMax( root ) );
    }

    public Comparable find( Comparable x ) {
        return elementAt( find( x, root ) );
    }
    public void makeEmpty( ) {
        root = null;
    }
    public boolean isEmpty( ) {
        return root == null;
    }
    private Comparable elementAt( BinaryNode t ) {
        return t == null ? null : t.element;
    }

    protected BinaryNode insert( Comparable x, BinaryNode t ) {
        if( t == null )
            t = new BinaryNode( x );
        else if( x.compareTo( t.element ) < 0 )
            t.left = insert( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = insert( x, t.right );
        else
            throw new DuplicateItemException( x.toString( ) );  // Duplicate
        return t;
    }

    protected BinaryNode remove( Comparable x, BinaryNode t ) {
        if( t == null )
            throw new ItemNotFoundException( x.toString( ) );
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = removeMin( t.right );
        } else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    protected BinaryNode removeMin( BinaryNode t ) {
        if( t == null )
            throw new ItemNotFoundException( );
        else if( t.left != null ) {
            t.left = removeMin( t.left );
            return t;
        } else
            return t.right;
    }

    protected BinaryNode findMin( BinaryNode t ) {
        if( t != null )
            while( t.left != null )
                t = t.left;
        return t;
    }
    private BinaryNode findMax( BinaryNode t ) {
        if( t != null )
            while( t.right != null )
                t = t.right;
        return t;
    }

    private BinaryNode find( Comparable x, BinaryNode t ) {
        while( t != null ) {
            if( x.compareTo( t.element ) < 0 )
                t = t.left;
            else if( x.compareTo( t.element ) > 0 )
                t = t.right;
            else
                return t;    // Match
        }
        return null;         // Not found
    }
    protected BinaryNode root;

    // Test program

    public static void main( String [ ] args ) throws UnsupportedEncodingException {
        BinarySearchTree t = new BinarySearchTree( );
        Scanner scanner = new Scanner(System.in);
        final int NUMS = 4000;
        final int GAP  =   37;
       System.out.println( "Checking... (no more output means success)" );
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( new Integer( i ) );
        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( new Integer( i ) );
        if( ((Integer)(t.findMin( ))).intValue( ) != 2 ||

                ((Integer)(t.findMax( ))).intValue( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );
        for( int i = 2; i < NUMS; i+=2 )
            if( ((Integer)(t.find( new Integer( i ) ))).intValue( ) != i )
                System.out.println( "Find error1!" );
        for( int i = 1; i < NUMS; i+=2 ) {
            if( t.find( new Integer( i ) ) != null )
                System.out.println( "Find error2!" );
        }
        System.out.println(" Imput строку  :");
        String s1 = scanner.next();
        try {
            hashCode(s1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    // хеш коды
    public static void hashCode(String y) throws UnsupportedEncodingException {
        char[] arr = y.toCharArray();
        String Str = new String(arr);
        byte[] bytes = Str.getBytes("ASCII");
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < arr.length; i++) {
            String val = Character.toString(arr[i]);
            System.out.println(" #" + (i + 1) + " Хеш код 1 " + val.hashCode());
            System.out.println("  Хеш код 2 " + (bytes[i] << 2));
            sum1 += val.hashCode();
            sum2 += bytes[i] << 2;
        }
        System.out.println(" Summa 1 " + sum1 +" Summa 2 " + sum2);
    }
}
class BinaryNode {
    // Constructors
    BinaryNode( Comparable theElement ) {
        element = theElement;
        left = right = null;
    }
    // Friendly data; accessible by other package routines
    Comparable element;      // The data in the node
    BinaryNode left;         // Left child
    BinaryNode right;        // Right child
}
class DuplicateItemException extends RuntimeException {
    public DuplicateItemException( ) {
        super( );
    }
    public DuplicateItemException( String message ) {
        super( message );
    }
}
class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException( ) {
        super( );
    }
    public ItemNotFoundException( String message ) {
        super( message );
    }

}
