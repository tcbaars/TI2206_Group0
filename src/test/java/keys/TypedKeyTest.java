//package keys;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by Dylan 9-10-2015
// * Test method for keys.TypedKey class.
// */
//public class TypedKeyTest {
//    /**
//     * This method tests the return value for getValue.
//     */
//    @Test
//    public void testgetValue() {
//      TypedKey key = 'a';
//      TypedKey mock = mock(TypedKey.class);
//      assertEquals('a', key.getValue());
//      when(mock.getValue()).thenReturn('a');
//      assertEquals('a', mock.getValue());
//    }
//
//    /**
//     * This method tests the method convertToKey.
//     */
//    @Test
//    public void testconvertToKey() {
//      TypedKey typedkey = 'e';
//      TypedKey key = '9';
//
//      assertEquals(null, Key.converToKey(null));
//      assertEquals(null, Key.converToKey(typedkey));
//
//      TypedKey ans = TypedKey.convertToKey(typedkey);
//      TypedKey digitans = TypedKey.convertToKey(key);
//      assertEquals('e' ,ans.getValue());
//      assertEquals('9' ,digitans.getValue());
//    }
//}
