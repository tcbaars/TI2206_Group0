//package keys;
//
//import org.junit.Test;
//import static org.mockito.Mockito.*;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//
///**
// * Created by Dylan 9-10-2015
// * Test method for keys.Key class.
// */
//public class KeyTest {
//    /**
//     * This method tests the return value for getKey.
//     */
//    @Test
//    public void testgetKey() {
//      Key key = GameKeys.UP;
//      Key mock = Mockito.mock(Key.class);
//      when(mock.getKey()).thenReturn(GameKeys.UP);
//      assertEquals(GameKeys.UP, key.getKey());
//      assertEquals(GameKeys.UP, mock.getKey());
//    }
//
//    /**
//     * This method tests the method convertToKey.
//     */
//    @Test
//    public void testconvertToKey() {
//      TypedKey typedkey = 'e';
//      Key key = GameKeys.UP;
//      assertEquals(null, Key.converToKey(null));
//      assertEquals(null, Key.converToKey(typedkey));
//      Key ans = Key.convertToKey(key);
//      assertEquals(GameKeys.UP ,ans.getKey());
//    }
//}
