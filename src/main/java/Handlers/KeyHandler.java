package Handlers;

import java.awt.event.KeyEvent;

import Enumerations.Key;

public class KeyHandler {
		
	public static Key convertToKey(KeyEvent keyevent){
		int code = keyevent.getKeyCode();
		return convertToKey(code);
	}
	
	public static Key convertToKey(int keycode){
		for(Key k : Key.values()){
			if(keycode == k.getKeyCode()){
				return k;
			}
		}
		return null;
	}
	
}
