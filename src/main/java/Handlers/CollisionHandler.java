package Handlers;

import java.awt.Rectangle;

import Entities.Entity;

public class CollisionHandler {
	
	private static void HandleSingleCollision(Entity eater, Entity food){
		
		if(eater != null){
			if(food != null){			
				if(eater.getBoundingBox().intersects(food.getBoundingBox())){
					if(food.isConsumable()){
						System.out.println("Collision");
						if(eater.isAlive()){
							if(eater.canConsume(food)){
								
								eater.consume(food);
							}
						}
					}
				}
			}
		}
	}
	
	public static void HandleCollisions(Entity e1, Entity e2){
		HandleSingleCollision(e1, e2);
		HandleSingleCollision(e2, e1);
	}
	
	public static void HandleCollisions(Entity e1, Entity[] e2){
		for(int i = 0; i < e2.length; i++){
			HandleCollisions(e1, e2[i]);
		}
	}
	
	public static void HandleCollisions(Entity[] e1, Entity[] e2){
		for(int i = 0; i < e1.length; i++){
			HandleCollisions(e1[i], e2);
		}
	}

}
